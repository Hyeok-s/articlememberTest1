<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/article/write?loginMemberId=${loginMemberId}" method="post" onsubmit="return checkWrite(this)">
	제목 <input type="text" name="title" required/>
	내용 <textarea name="body" required/></textarea>
	<button type="submit">작성완료</button>
</form>

<script type="text/javascript">
	
	function checkWrite(form) {

		if (form.title.value.trim() === null || form.title.value.trim() === "") {
			alert("제목이 작성되지 않았습니다.");
			return false;
		}

		if (form.body.value.trim() === null || form.body.value.trim() === "") {
			alert("내용이 작성되지 않았습니다.");
			return false;
		}

		return true;
	}
</script>
</body>
</html>