package net.softsociety.issho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {

	/**
	 * @brief	요청받은 가입화면 전달
	 * @param	없음
	 * @return	가입 폼 전달
	 */
/*
	@GetMapping("register")
	public String register() {
		log.debug("----- GET : {\"\", \"/\"}");
		//CODE HERE

		log.debug("----- 호출 : /memberView/register");
		return "/memberView/register";
	}
*/	
	/**
	 * @brief	요청받은 가입화면 전달
	 * @param	없음
	 * @return	가입 폼 전달
	 */
	/*
	 * @PostMapping("register") public String register() {
	 * log.debug("----- GET : {\"\", \"/\"}"); //CODE HERE
	 * 
	 * log.debug("----- 호출 : /memberView/register"); return "/memberView/register";
	 * }
	 */
	
	/**
	 * @brief	로그인 처리
	 * @param	memb_mail
	 * @param	memb_pwd 
	 * @return	가입 폼 전달
	 */
	@PostMapping("login")
	public String login(String memb_mail, String memb_pwd) {
		log.debug("----- POST : login");
		//CODE HERE
		log.debug("가입정보 : {}, {}", memb_mail, memb_pwd);
		
		log.debug("----- 호출 : /project/view/mainHome");
		return "/projectView/mainHome";
	}

}
