package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.Article;
import com.example.demo.service.ArticleService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	ArticleService articleService;

	public HomeController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@GetMapping("/")
	public String home(@RequestParam(value = "page", defaultValue = "1") int page, HttpSession session, Model model) {
		if (session.getAttribute("loginMemberId") != null) {
			model.addAttribute("loginMemberId", (int) session.getAttribute("loginMemberId"));
		}
		List<Article> articles = articleService.findAllArticle();
		if (articles != null || !articles.isEmpty()) {
			int pageSize = 3;
			int start = (page - 1) * pageSize;
			int end = Math.min(start + pageSize, articles.size());

			List<Article> pagedArticles = articles.subList(start, end);

			model.addAttribute("articles", pagedArticles);
			
			int pageCnt = (int) Math.ceil((double) articles.size() / pageSize);
			model.addAttribute("page", page);
		    model.addAttribute("pageCnt", pageCnt);
		}
		return "home";
	}

	@GetMapping("/home/message")
	public String message(@RequestParam("num") int num, Model model) {

		String message;
		String url;

		switch (num) {
		case 1:
			message = "로그아웃 후 이용해주세요.";
			url = "/";
			break;
		case 2:
			message = "로그인 후 이용해주세요.";
			url = "/member/loginForm";
			break;
		case 3:
			message = "아이디와 비밀번호를 잘못입력하셨습니다.";
			url = "/member/loginForm";
			break;
		case 4:
			message = "로그아웃되었습니다.";
			url = "/";
			break;

		case 5:
			message = "로그인 아이디와 작성자가 달라 로그아웃진행합니다.";
			url = "/member/logout";
			break;

		case 6:
			message = "게시물 작성이 완료되었습니다.";
			url = "/";
			break;

		case 7:
			message = "존재하지 않는 게시물 입니다.";
			url = "/";
			break;
			
		case 8:
			message = "정상적으로 변경이 완료되었습니다.";
			url = "/";
			break;
		case 9:
			message = "게시물 작성이 완료되었습니다.";
			url = "/";
			break;
		default:
			message = "알 수 없는 오류가 발생했습니다.";
			url = "/";
			break;
		}
		model.addAttribute("message", message);
		model.addAttribute("url", url);

		return "message";
	}
}
