<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<div>
		<c:if test="${empty loginMemberId}">
			<button type="button" onclick="location.href='/member/signUpForm'">회원가입</button>
			<button type="button" onclick="location.href='/member/loginForm'">로그인</button>
		</c:if>
		<c:if test="${not empty loginMemberId}">
			<button type="button" onclick="location.href='/member/logout'">로그아웃</button>
			<button type="button" onclick="location.href='/member/editMemberForm'">정보수정</button>
		</c:if>
	</div>
	<div>
		<c:if test="${not empty articles}">
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles}">
							<tr>
								<td>${article.id}</td>
								<td>${article.title}</td>
								<td><button type="button" onclick="location.href='/article/detailForm?id=${article.id}'">상세보기</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</c:if>
		<c:if test="${empty articles}">
			현재 게시물이 없습니다.
		</c:if>
	</div>
	<div>
        <c:if test="${page > 1}">
            <button type="button" onclick="location.href='/?page=${page - 1}'">이전</button>
        </c:if>

        <span>페이지 ${page} / ${pageCnt}</span>

        <c:if test="${page < pageCnt}">
            <button type="button" onclick="location.href='/?page=${page + 1}'">다음</button>
        </c:if>
    </div>
	<div>
		<button type="button" onclick="location.href='/article/writeForm'">글쓰기</button>
	</div>
</body>
</html>