package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.Article;

@Mapper
public interface ArticleDao {

	@Select("SELECT * FROM article")
	List<Article> findAllArticle();

	@Insert("INSERT INTO article(title, body, writer, regDate) VALUE(#{title}, #{body}, #{loginMemberId}, NOW())")
	void insertArticle(String title, String body, int loginMemberId);

	@Select("SELECT a.*, m.`name` AS writerName FROM article a INNER JOIN `member` m ON m.id = a.writer WHERE a.id = #{id}")
	Article findArticleByIdAndWriterName(int id);

}
