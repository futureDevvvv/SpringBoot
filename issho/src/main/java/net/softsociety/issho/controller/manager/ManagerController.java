package net.softsociety.issho.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("manager")
public class ManagerController {
	
	
	@GetMapping("/member")
	public String member() {
		return "managerView/managerMember";
	}
	
	@GetMapping("/invitation")
	public String invitation() {
		
		return "managerView/managerInvitation";
	}
	
	@GetMapping("/project")
	public String project() {
		
		log.debug("project Controller 도달!!");
		
		return "managerView/managerProjects";
	}
}
