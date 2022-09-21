package net.softsociety.issho.task.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.member.dao.MemberDAO;
import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.member.service.MemberService;
import net.softsociety.issho.task.domain.Task;

@Controller
@Slf4j
@RequestMapping("task")
public class TaskController {
	
	@Autowired
	MemberService memservice;
	
	@Autowired
	MemberDAO memDao;
	
	@GetMapping("/taskList")
	public String taskList(@AuthenticationPrincipal UserDetails user, Model model, String domain) {
		
		String id = user.getUsername();
		
		//회원 정보 조회
		Members member = memDao.getUserById(id);
		ArrayList<Members> pjMemList = memservice.searchPjMem("scit112");
		
		model.addAttribute("member", member);
		model.addAttribute("list", pjMemList);
		
		return "taskView/task_List";
	}
	
	@PostMapping("/newProject")
	public void newProject(@AuthenticationPrincipal UserDetails user, Task task, String staffs) {
	
		String id = user.getUsername();
		
		task.setTask_sender(id);
		
	}

}
