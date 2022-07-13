package net.softsociety.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring2.vo.Member;

@Slf4j
@Controller
public class lombokController {
	
	@GetMapping("log/lombok")
	public String lombok() {
		
		Member m = new Member();
		m.Test();
		
		return "redirect:/";
		
	}
	
	@GetMapping("log/logger")
	public String logger () {
		
		System.out.println("System.out.println()으로 출력");
		
		//프로젝트가 실행 안 될만한 에러
		log.error("error()로 출력");
		
		//당장은 아니지만 문제가 생길 수 있는 경우
		log.warn("warn()로 출력");
		
		//기록을 남길 떄
		log.info("info()로 출력");
		
		
		log.debug("debug()로 출력");
		log.trace("trace()로 출력");
		
		return "redirect:/";
		
	}

}
