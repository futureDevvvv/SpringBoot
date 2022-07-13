package net.softsociety.spring2.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.softsociety.spring2.vo.Member;

@RequestMapping("/th")
@Controller
public class ThymeController {

	@GetMapping("thymeleaf1")
	public String thyme1(Model model) {

		String str = "문자열";
		int num = 100;
		Member member = new Member("aaa", "111", "홍길동", "서울시");
		String tag = "<marquee>태그</marquee>";
		String url = "https://google.com";
		
		int n1 = 1000000;
		double n2 = 123.456778;
		Date d = new Date();

		model.addAttribute("str", str);
		model.addAttribute("num", 100);
		model.addAttribute("member", member);
		model.addAttribute("tag", tag);
		model.addAttribute("url", url);
		model.addAttribute("inum", n1);
		model.addAttribute("dnum", n2);
		model.addAttribute("date", d);
		
		return "th/thyme";
	}
	
	@GetMapping("thymeleaf2")
	public String thyme2(Model model) {
		String str = "문자열";
		String values = "Java,HTML,CSS";
		int num = 1;
		ArrayList<String> list = new ArrayList<>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "키보드");
		map.put("price", 10000);
		
		model.addAttribute("str", str);
		model.addAttribute("num", num);
		model.addAttribute("values", values);
		model.addAttribute("list", list);
		model.addAttribute("map", map);
		
		return "th/thyme2";
	}
}
