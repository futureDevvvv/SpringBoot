package net.softsociety.issho.chat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.issho.chat.domain.Chatroom;

@Mapper
public interface ChatDAO {

	List<Chatroom> chatList(String id);


}
