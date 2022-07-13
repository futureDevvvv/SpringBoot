package net.softsociety.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.softsociety.spring2.vo.Person;

@Controller
public class ModelController {

	@GetMapping("model/model1")
	public String model1(Model model) {

		String str = "문자열";
		int num = 12345;
		Person person = new Person("홍길동", "KT", "010-2616-6111");

		model.addAttribute("string", str);
		model.addAttribute("number", num);
		model.addAttribute("person", person);

		return "model1";

	}
}
