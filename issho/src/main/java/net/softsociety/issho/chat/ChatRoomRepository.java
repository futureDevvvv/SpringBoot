package net.softsociety.issho.chat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.socket.WebSocketSession;

import net.softsociety.issho.chat.domain.Chatroom;

/**
 * @brief 다중 채팅방이므로 (여러) 채팅방을 관리해줄 자바 클래스.
 * @author 윤영혜
 *
 */

public class ChatRoomRepository {

	//다중 채팅방 구현을 위한 map. 채팅방 고유번호(난수)와 chatroom 정보를 저장한다.
	Map<String, ChatRoom> chatRoomMap = new HashMap<String, ChatRoom>();

	// static : home컨트롤러에서 model로 사용하기 위함.
	public static Collection<ChatRoom> chatRooms;

	/**
	 * 새로운 채팅방 오픈
	 * 
	 */
	public void newChatRoom(String roomName) {
		// 고유한 난수를 생성한다. hashmap의 key 역할을 해줄 것임.
		String uuid = UUID.randomUUID().toString();
		//
		ChatRoom chatRoom = new ChatRoom(uuid);
		// 채팅방 목록에 추가
		chatRoomMap.put(chatRoom.getId(), chatRoom);
		
		//chatroom DB에 insert
		//Chatroom chroom = new Chatroom(uuid, roomName);
		//dao 호출
		
		//chatRooms = chatRoomMap.values();
	}
	
	/**
	 * 기존 채팅방 들어가기
	 * 
	 * uuid 받아와서 새로운 채팅방 생성한 뒤 현 채팅방 목록에 추가
	 * @param id
	 * @return
	 */
	
	public void enterChatRoom(String roomId) {
		
		ChatRoom chatRoom = new ChatRoom(roomId);
		// 채팅방 목록에 추가
		chatRoomMap.put(roomId, chatRoom);
		
		//chatRooms = chatRoomMap.values();
	}
	
	public ChatRoom getChatRoom(String id) {
		// TODO Auto-generated method stub
		return chatRoomMap.get(id);
	}

	public Map<String, ChatRoom> getChatRooms() {
		return chatRoomMap;
	}

	public void remove(WebSocketSession session) {
		this.chatRooms.parallelStream().forEach(chatRoom -> chatRoom.remove(session));
	}

}
