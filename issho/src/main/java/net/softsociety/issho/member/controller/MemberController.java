package net.softsociety.issho.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.member.service.MemberService;

@lombok.extern.slf4j.Slf4j
@Controller
@RequestMapping("/member")
/**
 * @brief 멤버 관련 컨트롤러 : 회원가입 
 * @author 윤영혜
 *
 */

public class MemberController {

	@Autowired
	MemberService memService;
	
	/**
	 * 
	 * 로그인 폼 이동
	 */
	@GetMapping("/join")
	public String join() {
		
		return "member/member_join";
	}
	
	/**
	 * 입력값 받아서 회원가입 로직 수행
	 * @return 
	 */
	@PostMapping("/join")
	public String join(Members members) {
		
		log.debug("members : {}", members);
		
		
		return "redirect:/";
	}
	
	@ResponseBody
	@PostMapping("/idCheck")
	public int idCheck(String memb_mail) {
		
		int result = memService.idSearchOne(memb_mail);
		
		return result;
	}
	
	
}
