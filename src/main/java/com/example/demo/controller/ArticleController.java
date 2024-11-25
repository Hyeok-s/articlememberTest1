package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.Article;
import com.example.demo.dto.Commit;
import com.example.demo.service.ArticleService;
import com.example.demo.service.CommitService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ArticleController {
	ArticleService articleService;
	CommitService commitService;
	
	public ArticleController(ArticleService articleService, CommitService commitService) {
		this.articleService = articleService;
		this.commitService = commitService;
	}
	
	@GetMapping("article/writeForm")
	public String writeForm(HttpSession session, Model model) {
		
		if(session.getAttribute("loginMemberId") == null) {
			return "redirect:/home/message?num=2";
		}
		model.addAttribute("loginMemberId", session.getAttribute("loginMemberId"));
		return "writeArticle";
	}
	
	@PostMapping("article/write")
	public String write(@RequestParam("title") String title, @RequestParam("body") String body, @RequestParam("loginMemberId") int loginMemberId, HttpSession session) {
		if(session.getAttribute("loginMemberId") == null) {
			return "redirect:/home/message?num=2";
		}
		
		else if((int) session.getAttribute("loginMemberId") != loginMemberId) {
			return "redirect:/home/message?num=5";
		}
		
		articleService.insertArticle(title, body, loginMemberId);
		
		return "redirect:/home/message?num=6";
	}
	
	@GetMapping("/article/detailForm")
	public String ArticleDetailForm(@RequestParam("id") int id, Model model) {
		Article article = articleService.findArticleByIdAndWriterName(id);
		if(article == null) {
			return "redirect:/home/message?num=7";
		}
		List<Commit> commits = commitService.findAllCommitAndWriterNameByArticleId(id);
		model.addAttribute("article", article);
		model.addAttribute("commits", commits);
		return "detailArticle";
	}
}
