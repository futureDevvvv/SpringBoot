package net.softsociety.issho.chat.service;

import java.util.List;
import java.util.Map;

import net.softsociety.issho.chat.domain.ChatMsg;
import net.softsociety.issho.chat.domain.Chatroom;
import net.softsociety.issho.member.domain.Members;

public interface ChatService {

	List<Chatroom> chatList(Map<String, String> map);

	void addChatMem(String roomid, String id);

	List<Members> chatMemList(String roomid);

	List<ChatMsg> chatMsgs(String roomid);

	void insertMsg(ChatMsg msg);

}
