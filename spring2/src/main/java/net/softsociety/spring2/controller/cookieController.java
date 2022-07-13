package net.softsociety.spring2.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class cookieController {

	@GetMapping("ck/cookie1")
	public String cookie1(HttpServletResponse response) {
		// 쿠키 생성 (이름 = 값)
		Cookie c1 = new Cookie("str", "abcde");
		Cookie c2 = new Cookie("num", "0");

		// 쿠키 종료 시점 지정
		c1.setMaxAge(3 * 24 * 60 * 60);
		c2.setMaxAge(3 * 24 * 60 * 60);

		// 클라이언트로 쿠키 보내서 저장
		response.addCookie(c1);
		response.addCookie(c2);

		return "redirect:/";
	}

	@GetMapping("ck/cookie2")
	public String cookie2(HttpServletResponse response) {

		/*
		 * 이미 저장된 크키와 같은 이름을 가진 쿠키 생성 쿠키의 수명을 0초로 지정 쿠키를 클라이언트로 보냄.
		 */

		// 쿠키 생성 (이름 = 값)
		Cookie c1 = new Cookie("str", "abcde");
		Cookie c2 = new Cookie("num", "0");

		// 쿠키 종료 시점 지정
		c1.setMaxAge(0);
		c2.setMaxAge(0);

		// 클라이언트로 쿠키 보내서 저장
		response.addCookie(c1);
		response.addCookie(c2);

		return "redirect:/";
	}

	@GetMapping("ck/cookie3")
	public String cookie3(HttpServletResponse response, @CookieValue(name = "str", defaultValue = "없음") String str,
			@CookieValue(name = "num", defaultValue = "0") int num, Model model) {

		String msg = "";

		if (str.equals("없음")) {
			msg = "첫 방문을 환영합니다.";
			
			Cookie c1 = new Cookie("str", "abcde");
			Cookie c2 = new Cookie("num", "0");

			// 쿠키 종료 시점 지정
			c1.setMaxAge(3 * 24 * 60 * 60);
			c2.setMaxAge(3 * 24 * 60 * 60);

			// 클라이언트로 쿠키 보내서 저장
			response.addCookie(c1);
			response.addCookie(c2);

		} else {
			int newNum = ++num;

			Cookie c2 = new Cookie("num", Integer.toString(newNum));
			response.addCookie(c2);

			msg = num + "번 방문했습니다";
		}

		model.addAttribute("msg", msg);

		return "cookie";
	}

}
