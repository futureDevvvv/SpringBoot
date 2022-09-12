package net.softsociety.issho.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.softsociety.issho.chat.dao.ChatDAO;
import net.softsociety.issho.chat.domain.Chatroom;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	ChatDAO chatDao;

	@Override
	public List<Chatroom> chatList(String id) {

		List<Chatroom> list = chatDao.chatList(id);
		
		return list;
	}

}
