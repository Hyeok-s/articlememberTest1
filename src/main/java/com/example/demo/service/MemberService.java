package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.dto.Member;

@Service
public class MemberService {
	MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public Member findMemberByEmail(String email) {
		return memberDao.findMemberByEmail(email);
	}

	public void insertMember(String email, String pw, String name) {
		memberDao.insertMember(email, pw, name);
		
	}

	public Member findMemberByEmailAndPassword(String email, String pw) {
		return memberDao.findMemberByEmailAndPassword(email, pw);
	}

	public Member findMemberByIdAndPassword(int loginId, String pw) {
		return memberDao.findMemberByIdAndPassword(loginId, pw);
	}

	public Member findMemberById(int id) {
		return memberDao.findMemberById(id);
	}

	public void updateMember(Member afterMember) {
		memberDao.updateMember(afterMember);
		
	}
}
