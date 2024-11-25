package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.Member;

@Mapper
public interface MemberDao {

	@Select("SELECT * FROM member WHERE email=#{email}")
	Member findMemberByEmail(String email);

	@Insert("INSERT INTO member(email, password, name, regDate, updateDate) VALUE(#{email}, #{pw}, #{name}, NOW(), NOW())")
	void insertMember(String email, String pw, String name);

	@Select("SELECT * FROM member WHERE email=#{email} AND password=#{pw}")
	Member findMemberByEmailAndPassword(String email, String pw);

	@Select("SELECT * FROM member WHERE id = #{loginId} AND password = #{pw}")
	Member findMemberByIdAndPassword(int loginId, String pw);
	
	@Update("""
			<script>
			    UPDATE member
			    <set>
			        <if test="email != null">email = #{email},</if>
			        <if test="password != null">password = #{password},</if>
			        <if test="name != null">name = #{name},</if>
			        updateDate = NOW()
			    </set>
			    WHERE id = #{id}
			</script>
		""")
		void updateMember(Member member);

	@Select("SELECT * FROM member WHERE id = #{id}")
	Member findMemberById(int id);
}
