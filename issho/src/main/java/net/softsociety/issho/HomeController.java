package net.softsociety.issho;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	/**
	 * @brief 메인화면 이동
	 * @author 윤영혜
	 */
	@GetMapping({"", "/"})
	public String home() {
		return "home";
	}
	
	@GetMapping("sidebar")
	public String footer() {
		return "fragments/sidebar";
	}
}
