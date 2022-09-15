package net.softsociety.issho.chat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @brief 채팅방 입장, 메시지 전송, 채팅방 삭제 로직. chatRoomRepository 클래스의 chatRoomMap 변수를 채우기
 *        위한 클래스.
 * @author 윤영혜
 *
 */

public class ChatRoom {

	// 채팅방 아이디
	String id;
	// 채팅방 참가자 세션을 모아둘 set.
	Set<WebSocketSession> sessions = new HashSet<>();

	public ChatRoom(String room_id) {
		this.id = room_id;
	}

	public String getId() {
		return id;
	}

	public Set<WebSocketSession> getSessions() {
		return sessions;
	}

	///

	public void handleMessage(WebSocketSession session, ChatMessage chatMessage, ObjectMapper objectMapper)
			throws JsonProcessingException {
		System.out.println("handlemsg 실행됨!!");
		if (chatMessage.getType().equals("JOIN")) {
			join(session);
			System.out.println("join 실행됨 : " + session);
		} else {
			send(chatMessage, objectMapper);
			System.out.println("send 실행됨 : " + chatMessage);
		}
	}

	private void join(WebSocketSession session) {

		System.out.println("Join 실행됨");

		sessions.add(session);

		System.out.println(sessions);
	}

	private <T> void send(T messageObject, ObjectMapper objectMapper) throws JsonProcessingException {
		TextMessage message = new TextMessage(objectMapper.writeValueAsBytes(messageObject));

		System.out.println("send 실행됨");

		// parallelStream : 프로세서의 다중 코어를 활용하기 위한 자바 8 문법.
		sessions.parallelStream().forEach(session -> {
			try {
				session.sendMessage(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public void remove(WebSocketSession target) {
		String targetId = target.getId();
		sessions.removeIf(session -> session.getId().equals(targetId));
	}
}
