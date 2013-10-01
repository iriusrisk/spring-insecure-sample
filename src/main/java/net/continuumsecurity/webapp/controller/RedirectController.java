package net.continuumsecurity.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/redirect")
public class RedirectController {
	
	@RequestMapping("/redirect")
	protected String redirect(@RequestParam("url") String redirectUrl) 
	{
	    return "redirect:" + redirectUrl;
	}
}
