package net.softsociety.issho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.domain.Member;

@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {

	@GetMapping("loginForm") 
	public String loginForm() {
	log.debug("----- POST : /member/register");
	  
	log.debug("----- 호출 : /memberView/login");
	return "/memberView/login";
	}

	/**
	 * @brief	로그인 처리
	 * @param	memb_mail
	 * @param	memb_pwd 
	 * @return	가입 폼 전달
	 */
/*
	@GetMapping("login")
	public String projectLogin(@PathVariable String prj_domain, Model model) {
		log.debug("----- GET : {}/member/login", prj_domain);
		model.addAttribute("prj_domain", prj_domain);
		
		log.debug("----- 호출 : /memberView/login");
		return "/memberView/login";
	}
*/
	/**
	 * @brief	요청받은 가입화면 전달
	 * @param	없음
	 * @return	가입 폼 전달
	 */
	@GetMapping("register")
	public String register() {
		log.debug("----- GET : register");
		//CODE HERE

		log.debug("----- 호출 : /memberView/register");
		return "/memberView/register";
	}
	
	/**
	 * @brief	요청받은 가입화면 전달
	 * @param	없음
	 * @return	가입 폼 전달
	 */
	@PostMapping("register") 
	public String register(Member member) {
	log.debug("----- POST : /member/register");
	log.debug("----- PARAM : member {}", member);
	  
	log.debug("----- 호출 : /memberView/register"); return "/memberView/register";
	}
	 
	/**
	 * @brief	로그인 처리
	 * @param	memb_mail
	 * @param	memb_pwd 
	 * @return	가입 폼 전달
	 */
	@GetMapping("login")
	public String login() {
		log.debug("----- POST : login");
		//CODE HERE
//		log.debug("가입정보 : {}, {}", memb_mail, memb_pwd);
		
		log.debug("----- 호출 : /project/view/mainHome");
		return "/memberView/login";
	}
	
}
