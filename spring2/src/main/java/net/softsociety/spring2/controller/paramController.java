package net.softsociety.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.softsociety.spring2.vo.Person;

@Controller
public class paramController {

	// param1.html로 이동
	@GetMapping({ "/param/param1" })
	public String param1() {
		// 포워딩. 기존 정보를 유지하면서 이동하는 것.
		return "param1";
	}

	// param1.html에서 전달되는 parameter 받기
	@GetMapping("/param/input1")
	public String input1(String name, String telecom, String phonenum) {
		System.out.println("전달된 이름 : " + name);
		System.out.println("통신사 : " + telecom);
		System.out.println("폰번호 : " + phonenum);

		// redirect : 요청 정보 종료.
		// redirect:/ 클라이언트로부터 들어온 요청 정보는 끝남. 메인화면으로 돌려보냄.
		return "redirect:/";
	}

	@GetMapping({ "/param/param2" })
	public String param2() {
		// 포워딩. 기존 정보를 유지하면서 이동하는 것.
		return "param2";
	}

	@PostMapping("/param/input2")
	public String input2(String name, String telecom, String phonenum) {
		System.out.println("전달된 이름 : " + name);
		System.out.println("통신사 : " + telecom);
		System.out.println("폰번호 : " + phonenum);

		// redirect : 요청 정보 종료.
		// redirect:/ 클라이언트로부터 들어온 요청 정보는 끝남. 메인화면으로 돌려보냄.
		return "redirect:/";
	}

	@GetMapping({ "/param/param3" })
	public String param3() {
		// 포워딩. 기존 정보를 유지하면서 이동하는 것.
		return "param3";
	}

	@GetMapping("/param/input3")
	public String input3(Person p) {
		System.out.println(p);

		// redirect : 요청 정보 종료.
		// redirect:/ 클라이언트로부터 들어온 요청 정보는 끝남. 메인화면으로 돌려보냄.
		return "redirect:/";
	}

	@GetMapping({ "/param/param4" })
	public String param4(@RequestParam(name = "name", defaultValue = "기본값") String name,
			@RequestParam(name = "age", defaultValue = "0") String age) {

		if (age.length() == 0 || !(isStringInteger(age, 100))) {
			System.out.println("잘못된 값이 입력되었습니다.");
		} else {
			System.out.println("이름 " + name + "나이 " + age);
		}
		return "redirect:/";
	}

	public static boolean isStringInteger(String stringToCheck, int radix) {
		if (stringToCheck.isEmpty())
			return false; // Check if the string is empty
		for (int i = 0; i < stringToCheck.length(); i++) {
			if (i == 0 && stringToCheck.charAt(i) == '-') { // Check for negative Integers
				if (stringToCheck.length() == 1)
					return false;
				else
					continue;
			}
			if (Character.digit(stringToCheck.charAt(i), radix) < 0)
				return false;
		}
		return true;
	}
}
