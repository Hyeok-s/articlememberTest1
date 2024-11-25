package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.CommitDao;
import com.example.demo.dto.Commit;



@Service
public class CommitService {

	CommitDao commitDao;

	
	public CommitService(CommitDao commitDao) {
		this.commitDao = commitDao;
	}
	
	public void insertCommit(String content, int articleId, int writer) {
		commitDao.insertCommit(content, articleId, writer);
	}
	
	public List<Commit> findAllCommitAndWriterNameByArticleId(int articleId){
		return commitDao.findAllCommitAndWriterNameByArticleId(articleId);
	};

}
