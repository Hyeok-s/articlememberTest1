package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/member/signUpForm")
	public String signUpForm(HttpSession session) {
		if(session.getAttribute("loginMemberId") != null) {
			return "redirect:/home/message?num=1";
		}
		return "signUp";
	}
	
	@GetMapping("member/checkEmail")
	@ResponseBody
	public int checkEmail(@RequestParam("email") String email) {
		Member member = memberService.findMemberByEmail(email);
		if(member != null) {
			return 0;
		}
		return 1;
	}
	
	@PostMapping("/member/signUp")
	public String signUp(@RequestParam("email") String email, @RequestParam("pw") String pw, 
			@RequestParam("name") String name, HttpSession session) {
		if(session.getAttribute("loginMemberId") != null) {
			return "redirect:/home/message?num=1";
		}
		memberService.insertMember(email, pw, name);
		
		return "redirect:/";
	}
	
	
	@GetMapping("/member/loginForm")
	public String loginForm(HttpSession session) {
		if(session.getAttribute("loginMemberId") != null) {
			return "redirect:/home/message?num=1";
		}
		return "login";
	}
	
	@PostMapping("/member/login")
	public String login(@RequestParam("email") String email, @RequestParam("pw") String pw, HttpSession session) {
		if(session.getAttribute("loginMemberId") != null) {
			return "redirect:/home/message?num=1";
		}
		Member member = memberService.findMemberByEmailAndPassword(email, pw);
		if(member == null) {
			return "redirect:/home/message?num=3";
		}
		session.setAttribute("loginMemberId", member.getId());
		return "redirect:/";
	}
	
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		if(session.getAttribute("loginMemberId") == null) {
			return "redirect:/home/message?num=2";
		}
		session.removeAttribute("loginMemberId");
		return "redirect:/home/message?num=4";
	}
	
	@GetMapping("/member/editMemberForm")
	public String editMember(HttpSession session){
		if(session.getAttribute("loginMemberId") == null) {
			return "redirect:/home/message?num=2";
		}
		return "editMember";
	}
	
	@PostMapping("/member/checkPw")
	@ResponseBody
	public Map<String, Object> checkPassword(@RequestParam("pw") String pw, HttpSession session) {
	    Map<String, Object> response = new HashMap<>();

	    if (session.getAttribute("loginMemberId") == null) {
	        response.put("status", "redirect");
	        response.put("message", "/home/message?num=2");
	        return response;
	    }

	    int loginId = (int) session.getAttribute("loginMemberId");
	    Member member = memberService.findMemberByIdAndPassword(loginId, pw);

	    if (member != null) {
	        response.put("status", "success");
	        response.put("member", member);
	    } else {
	        response.put("status", "false");
	    }

	    return response;
	}

	@PostMapping("/member/editMember")
	public String editMember(@RequestParam("email") String email, @RequestParam("newPw") String pw, @RequestParam("name") String name, HttpSession session) {
		if(session.getAttribute("loginMemberId") == null) {
			return "redirect:/home/message?num=2";
		}
		int id = (int)session.getAttribute("loginMemberId");
		
		Member beforeMember = memberService.findMemberById(id);
	    Member afterMember = new Member();
	    afterMember.setId(id);
	    
	    if (!email.equals(beforeMember.getEmail())) {
	    	afterMember.setEmail(email);
	    }
	    if (!pw.equals(beforeMember.getPassword())) {
	    	afterMember.setPassword(pw);
	    }
	    if (!name.equals(beforeMember.getName())) {
	    	afterMember.setName(name);
	    }
	    memberService.updateMember(afterMember);
	    	    
		return "redirect:/home/message?num=8";
	}
	

}
