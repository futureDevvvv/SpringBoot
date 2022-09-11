package net.softsociety.issho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	/**
	 * @brief 메인화면 이동
	 * @author 윤영혜
	 */
	@GetMapping({"", "/"})
	public String home() {
		log.debug("----- GET : {\"\", \"/\"}");
		
		log.debug("----- 호출 : /index");
		return "index";
	}
	
	/**
	 * @brief	도매인만 치고 들어 올 떄 로그인으로 연결
	 * @param	prj_domain
	 * @return	로그인 폼 전달
	 */
/*
	@GetMapping("{prj_domain}")
	public String etcHome(@PathVariable String prj_domain) {
		log.debug("----- GET : @PathVariable {}", prj_domain);
		
		String destinationUrl  = prj_domain + "/member/login";
		String redirectUrl  = "redirect:/" + destinationUrl;
		
		log.debug("----- 호출 : /{}", redirectUrl);
//		return redirectUrl;
		return "memberView/login";
	}
*/
/*	
	@GetMapping("{prj_domain}")
	public String etcHome(@PathVariable String prj_domain) {
		log.debug("----- GET : @PathVariable {}", prj_domain);
		
		String destinationUrl  = prj_domain + "/member/login";
		String redirectUrl  = "redirect:/" + destinationUrl;
		
		log.debug("----- 호출 : /{}", redirectUrl);
		return redirectUrl;
	}
*/
	@GetMapping("sidebar")
	public String footer() {
		return "/fragments/sidebar";
	}
	
}
