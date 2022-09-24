package net.softsociety.issho.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/task")
public class DashboardController {
	
	/**
	 * @brief 칸반보드 이동
	 * @return 칸반보드 html
	 */
	@GetMapping("kanban")
	public String kanban() {
		
		return "taskView/task_kanban";
	}
	
	

}
