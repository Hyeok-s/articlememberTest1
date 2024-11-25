package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.CommitService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommitController {
	CommitService commitService;
	
	public CommitController(CommitService commitService) {
		this.commitService = commitService;
	}

	@PostMapping("/commit/write")
	public String writeCommit(@RequestParam("content") String content, @RequestParam("articleId") int articleId, HttpSession session) {
		
		if(session.getAttribute("loginMemberId") == null) {
			return "redirect:/home/message?num=2";
		}
		
		commitService.insertCommit(content, articleId, (int)session.getAttribute("loginMemberId"));
		
		return "redirect:/home/message?num=9";
		
	}
	
	
	
}
