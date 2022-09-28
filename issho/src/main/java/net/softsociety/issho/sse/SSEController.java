package net.softsociety.issho.sse;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("**/push")
public class SSEController {
	
	@GetMapping(value="/push", produces="text/event-stream;charset=utf-8")
	@ResponseBody
	public String push() {
		
		Random r = new Random();
		
		try {
			Thread.sleep(5000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		return "data : Testing 1, 2, 3" + r.nextInt()+"";
		
	}

	 @RequestMapping(value = "/sseTest", method = RequestMethod.GET)
	    public String getSSEView () {
	        return "sseTest";
	    }
}

