<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<div>
		<form action="/member/login" method="post">
			이메일: <input type="email" name="email" id="email" maxlength="25" required/>
			비밀번호: <input type="password" name="pw" maxlength="17" required/>
			<button type="submit">로그인</button>				
		</form>
	</div>
</body>
</html>