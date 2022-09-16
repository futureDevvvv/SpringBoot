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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.softsociety.issho.chat.ChatRoomRepository;
import net.softsociety.issho.chat.domain.ChatMsg;
import net.softsociety.issho.chat.service.ChatService;
import net.softsociety.issho.member.domain.Members;

@lombok.extern.slf4j.Slf4j
@Controller
@RequestMapping("**/multiRoom")
public class ChatRoomController {

	@Autowired
	ChatRoomRepository repo;

	@Autowired
	ChatService chatService;

	/**
	 * 
	 * @param user
	 * @param chatroom_name
	 * @param memList2
	 * @param prj_domain
	 * @param model
	 * @return
	 */
	@PostMapping("/openNewChat")
	public String openNewChat(@AuthenticationPrincipal UserDetails user, String chatroom_name, String memList2,
			String prj_domain, Model model) {

		String id = user.getUsername();

		String[] list = memList2.split(",");

		// 새로운 채팅방 생성
		String roomid = repo.newChatRoom(chatroom_name, prj_domain);

		// 채팅방 id과 참여자 연결
		chatService.addChatMem(roomid, id);

		for (int i = 0; i < list.length; i++) {
			chatService.addChatMem(roomid, list[i]);
		}

		model.addAttribute("list", list);
		model.addAttribute("roomId", roomid);

		return "chat/chat_room";
	}

	/**
	 * 채팅방 입장.
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@GetMapping("/room")
	public String roomController(Model model, String roomid) {

		// String roomId = request.getParameter("id");

		//현재 repository에 등록된 chatroom인지 여부
		if(repo.chatRoomMap.containsKey(roomid)) {
			repo.enterOpenedChatRoom(roomid);
		} else {
			repo.enterChatRoom(roomid);
		}
		
		// 참여자 목록 받아오기
		List<Members> chatMems = chatService.chatMemList(roomid);
		
		// 기존 채팅 내역 받아오기
		List<ChatMsg> chatMsgs = chatService.chatMsgs(roomid);
		
		model.addAttribute("chatMsgs", chatMsgs);
		model.addAttribute("chatMems", chatMems);
		model.addAttribute("roomId", roomid);
		return "chat/chat_room";
	}
	
	@ResponseBody
	@RequestMapping(value="/insertMsg", method = {RequestMethod.POST})
	public void insertMsg(@AuthenticationPrincipal UserDetails user, ChatMsg msg) {
		
		String id = user.getUsername();
		msg.setChatmsg_recipient(id);
		
		log.debug("msg : {}", msg);
		
		chatService.insertMsg(msg);
	}

}
