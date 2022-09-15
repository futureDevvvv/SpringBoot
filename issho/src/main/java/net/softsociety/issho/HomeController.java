package net.softsociety.issho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.project.service.ProjectService;

@Slf4j
@Controller
public class HomeController {
	
	@Autowired
	ProjectService pjservice;
	
	// 123
	/**
	 * @brief 메인화면 이동
	 * @author 윤영혜
	 */
	@GetMapping({ "", "/" })
	public String home() {
		return "home";
	}

	@GetMapping("sidebar")
	public String footer() {
		return "fragments/sidebar";
	}

	/**
	 * 로그인 폼 이동
	 * 
	 * @return
	 */
	@GetMapping("/loginForm")
	public String loginForm(/*@PathVariable String prj_domain*/) {
		return "member/member_login";
	}

	/**
	 * 도메인 중복 여부 체크 ajax
	 * 
	 * @param prj_domain
	 * @return
	 */

	@ResponseBody
	@PostMapping("/domainCheck")
	public int domainCheck(String prj_domain) {

		int result = pjservice.domainCheck(prj_domain);

		log.debug("도메인 : {}", prj_domain);

		log.debug("결과 : {}", result);

		return result;
	}

}
