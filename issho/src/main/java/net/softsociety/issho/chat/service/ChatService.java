package net.softsociety.issho.chat.service;

import java.util.List;

import net.softsociety.issho.chat.domain.Chatroom;

public interface ChatService {

	List<Chatroom> chatList(String id);

}
