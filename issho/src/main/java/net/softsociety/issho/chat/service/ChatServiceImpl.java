package net.softsociety.issho.chat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.softsociety.issho.chat.dao.ChatDAO;
import net.softsociety.issho.chat.domain.ChatMsg;
import net.softsociety.issho.chat.domain.Chatroom;
import net.softsociety.issho.member.domain.Members;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	ChatDAO chatDao;

	@Override
	public List<Chatroom> chatList(Map<String, String> map) {

		List<Chatroom> list = chatDao.chatList(map);
		
		return list;
	}

	@Override
	public void addChatMem(String roomid, String id) {
		
		Chatroom chatroom = new Chatroom(roomid, id);
		
		chatDao.addChatMem(chatroom);
		
	}

	@Override
	public List<Members> chatMemList(String roomid) {
		
		List<Members> chatMemList = chatDao.chatMemList(roomid);
		
		return null;
	}

	@Override
	public List<ChatMsg> chatMsgs(String roomid) {

		 List<ChatMsg> chatMsgs = chatDao.chatMsgs(roomid);
		
		return chatMsgs;
	}

	@Override
	public void insertMsg(ChatMsg msg) {
		
		chatDao.insertMsg(msg);
		
	}

}
