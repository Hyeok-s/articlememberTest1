package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.Article;
import com.example.demo.dto.Commit;

@Mapper
public interface CommitDao {

	@Insert("INSERT INTO commit(content, articleId, writer, regDate) VALUE(#{content}, #{articleId}, #{writer}, NOW())")
	void insertCommit(String content, int articleId, int writer);

	
	@Select("SELECT c.*, m.`name` AS writerName FROM `commit` c INNER JOIN `member` m ON m.id = c.writer WHERE c.articleId = #{articleId}")
	List<Commit> findAllCommitAndWriterNameByArticleId(int articleId);

}
