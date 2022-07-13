package net.softsociety.spring2.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 세션을 처리하기 위한 컨트롤러 클래스
 * 
 * @author user
 *
 */

@Controller
public class SessionController {

	/**
	 * 세션에 값 저장
	 * 
	 * @param session 세션 객체
	 * @return 메인 화면으로 리다이렉트
	 */
	@GetMapping("ss/session1")
	public String session1(HttpSession session) {

		session.setAttribute("id", "abc");
		return "redirect:/";
	}

	@GetMapping("ss/session2")
	public String session2() {

		return "session";
	}

	@GetMapping("ss/session3")
	public String session3(HttpSession session) {

		session.removeAttribute("id");

		return "redirect:/";
	}

	@GetMapping("ss/session4")
	public String session4(HttpSession session, HttpServletResponse response) throws IOException {
		if(session.getAttribute("id") == null) {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('세션 정보가 없습니다.');</script>");
			out.flush();
			
			return "redirect:/";
		} else {
			return "temp";
		}	
	}
}
