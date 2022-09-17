package net.softsociety.issho;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("plans")
@Controller
public class PlansController {
	
	/**
	 * 가입 폼으로 이동
	 */
	@GetMapping("plan")
	public String join() {
		return "/plansView/main";
	}
}
