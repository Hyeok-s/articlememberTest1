<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail</title>
</head>
<body>
상세정보
	<div>
		제목: ${article.title}
		내용: ${article.body}
		작성자: ${article.writerName}
		작성일: ${article.regDate}
	</div>
	
댓글정보
	<div>
		<c:if test="${not empty commits}">
				<table>
					<thead>
						<tr>
							<th>댓글</th>
							<th>작성자</th>
							<th>작성시간</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="commit" items="${commits}">
							<tr>
								<td>${commit.content}</td>
								<td>${commit.writerName}</td>
								<td>${commit.regDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</c:if>
		<c:if test="${empty commits}">
			현재 댓글이 없습니다.
		</c:if>
	</div>


	<div>
	<form action="/commit/write?articleId=${article.id}" method="post" onsubmit="return checkContent(this)">
		댓글 작성
		<textarea name="content" required/></textarea>
		<button type="submit">작성완료</button>
		</form>
	</div>
	<script>
	function checkContent(form) {
		if (form.content.value.trim() !== form.content.value) {
			alert("댓글 양끝에는 공백이 들어갈 수 없습니다.");
			return false;
		}
		
		if (form.content.value === null || form.content.value === "") {
			alert("댓글은 공백이 될 수 없습니다.");
			return false;
		}
		return true;
	}
	</script>
</body>
</html>