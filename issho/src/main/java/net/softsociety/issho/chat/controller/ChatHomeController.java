package net.softsociety.issho.chat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.chat.domain.Chatroom;
import net.softsociety.issho.chat.service.ChatService;
import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.member.service.MemberService;
import net.softsociety.issho.project.domain.Projects;
import net.softsociety.issho.project.service.ProjectService;

@Slf4j
@Controller
@RequestMapping("**/multiRoom")
public class ChatHomeController {

	@Autowired
	ProjectService pjservice;

	@Autowired
	ChatService chatservice;

	@Autowired
	MemberService memservice;

	/**
	 * chatHome 메소드
	 * 
	 * @param model   모델 객체
	 * @param request
	 * @param 멤버      아이디
	 * @param 프로젝트    도메인
	 * @return 프로젝트 도메인과 아이디 일치하는 채팅방 목록을 불러와 모델에 담은 뒤 chat_home.html로 리턴
	 */
	@GetMapping("/chatHome")
	public String chatHome(Model model, @AuthenticationPrincipal UserDetails user, String domain) {

		String id = user.getUsername();

		Map<String, String> map = new HashMap<String, String>();

		map.put("chat_member", id);
		map.put("prj_domain", domain);

		List<Chatroom> list = chatservice.chatList(map);

		/*
		 * Collection<ChatRoom> chatRooms = ChatRoomRepository.chatRooms;
		 * 
		 * model.addAttribute("collection", chatRooms);
		 */

		Projects project = pjservice.searchOne(domain);

		model.addAttribute("project", project);
		model.addAttribute("list", list);

		return "chat/chat_home";
	}

	/**
	 * 새로운 채팅방 생성 폼 이동
	 * 
	 */

	@GetMapping("/newChat")
	public String newChat(Model model, String domain, @AuthenticationPrincipal UserDetails user) {

		String id = user.getUsername();
		/*
		 * Map<String, String> map = new HashMap<String, String>();
		 * map.put("chat_member", id); map.put("prj_domain", domain);
		 */
		Projects project = pjservice.searchOne(domain);
		ArrayList<Members> pjMemList = memservice.searchPjMem(domain);

		model.addAttribute("project", project);
		model.addAttribute("list", pjMemList);

		return "chat/chat_new";
	}

}
