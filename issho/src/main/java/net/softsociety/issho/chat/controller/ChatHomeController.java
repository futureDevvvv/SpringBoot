package net.softsociety.issho.chat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/multiRoom")
public class ChatHomeController {

	/**
	 * chatHome 메소드
	 * @param model 모델 객체
	 * @param request 
	 * @param 멤버 아이디
	 * @param 프로젝트 도메인
	 * @return 프로젝트 도메인과 아이디 일치하는 채팅방 목록을 불러와 모델에 담은 뒤 chat_home.html로 리턴
	 */	
	@GetMapping("/chatHome")
	public String chatHome(Model model, HttpServletRequest request) {
		
		/*
		 * Collection<ChatRoom> chatRooms = ChatRoomRepository.chatRooms;
		 * 
		 * model.addAttribute("collection", chatRooms);
		 */
		
		//ArrayList<ChatRoom> list
		//model.addAttribute("list", list);
		
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
