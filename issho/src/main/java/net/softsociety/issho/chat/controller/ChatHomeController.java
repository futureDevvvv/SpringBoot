package net.softsociety.issho.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.softsociety.issho.chat.domain.Chatroom;
import net.softsociety.issho.chat.service.ChatService;

@Controller
@RequestMapping("**/multiRoom")
public class ChatHomeController {
	
	//URL에 있는 파라미터 값 어떻게 가져올지??
	
	@Autowired
	ChatService chatservice;
	
	/**
	 * chatHome 메소드
	 * @param model 모델 객체
	 * @param request 
	 * @param 멤버 아이디
	 * @param 프로젝트 도메인
	 * @return 프로젝트 도메인과 아이디 일치하는 채팅방 목록을 불러와 모델에 담은 뒤 chat_home.html로 리턴
	 */	
	@GetMapping("/chatHome")
	public String chatHome(Model model, @AuthenticationPrincipal UserDetails user) {
		
		String id = user.getUsername();
		
		List<Chatroom> list = chatservice.chatList(id);
		
		/*
		 * Collection<ChatRoom> chatRooms = ChatRoomRepository.chatRooms;
		 * 
		 * model.addAttribute("collection", chatRooms);
		 */
		
		model.addAttribute("list", list);
		
		return "chat/chat_home";
	}

	/**
	 * 새로운 채팅방 생성 폼 이동
	 * 
	 */
	
	@GetMapping("/newChat")
	public String newChat() {
		
		return "chat/chat_new";
	}
	
	/**
	 * 채팅방 참여자 목록, 채팅방명 받아와서 새로운 채팅방 생성
	 * @param 참여자 list, 채팅방명
	 * @return /room으로 redirect
	 */
	@PostMapping("/openNewChat")
	public String openNewChat() {
		
		//새로운 채팅방 생성
		
		//채팅방 id과 참여자 연결		
		
		return null;
	}
	
}
