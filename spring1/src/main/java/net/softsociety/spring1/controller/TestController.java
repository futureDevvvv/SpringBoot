package net.softsociety.spring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	/*실제 주소*/
	@GetMapping({"/test"}) /*해당 값이 다른 값을 가리키도록 하는 것. 주소를 간결하게 할 수 있다.*/
	public String test() {
		/*html 파일*/
		return "/test";
	}
}
