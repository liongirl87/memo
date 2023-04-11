package com.memo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class memoController {
	
	@GetMapping("/user/sign_in_view")
	public String signInView() {
		return "memo/signInView";
	}
}
