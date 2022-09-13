package net.softsociety.issho.chat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import groovy.util.logging.Slf4j;

@Slf4j
@Controller
@RequestMapping("/multiRoom")
public class ChatRoomController {

	/**
	 * 채팅방 입장.
	 * @param model
	 * @param request
	 * @return
	 */
	
	@GetMapping("/room")
	public String roomController(Model model, HttpServletRequest request) {
		
		String roomId = request.getParameter("id");
		
		//참여자 목록
		
		//기존 채팅 내역
		
		model.addAttribute("roomId", roomId);
		return "chat/chat_room";
	}
	
	

}
