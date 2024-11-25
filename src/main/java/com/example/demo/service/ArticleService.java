package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dto.Article;

@Service
public class ArticleService {
	
	ArticleDao articleDao;
	
	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public List<Article> findAllArticle() {
		return articleDao.findAllArticle();
	}

	public void insertArticle(String title, String body, int loginMemberId) {
		articleDao.insertArticle(title, body, loginMemberId);
	}

	public Article findArticleByIdAndWriterName(int id) {
		return articleDao.findArticleByIdAndWriterName(id);
	}
	
}
