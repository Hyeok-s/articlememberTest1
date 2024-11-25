<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- JQuery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<title>sign up</title>
</head>
<body>
	<div>
		<form action="/member/signUp" method="post" onsubmit="return checkMember(this)">
			이메일: <input type="email" name="email" id="email" maxlength="25" required/>
			<button type="button" onClick="checkEmail()">중복확인</button>
			비밀번호: <input type="password" name="pw" maxlength="17" required/>
			비밀번호 확인: <input type="password" name="pwChk" maxlength="17" required/>
			이름: <input type="text" name="name" maxlength="5" required/>
			<button type="submit">가입하기</button>				
		</form>
	</div>
	
<script type="text/javascript">

	let checkEmailNum = -1;
	
	function checkEmail(){
		const email = $("#email").val()
		
		if(email.trim().length === 0){
			alert("이메일은 공백이 될 수 없습니다.");
			return;
		}
		
		if (!validateEmail(email)) {
			alert("올바른 이메일 형식이 아닙니다.");
			return;
		}
		
		$.ajax({
			url: "/member/checkEmail",
			type: "GET",
			data: {email: email},
			dataType: "json",
			success: function(data) {
				if (data == 0) {
					checkEmailNum = 0;
					alert("중복된 이메일입니다.");
				} else {
					checkEmailNum = 1;
					alert("사용 가능한 이메일입니다.");
				}
			},
			error: function(error) {
				alert("중복 확인 중 오류가 발생했습니다: " + error);
			}
		});
	}
		
	$("#email").on("input", function() {
		checkEmailNum = -1;
	});

	
	function checkMember(form) {
		if (checkEmailNum == -1) {
			alert("이메일 중복확인을 실시해주세요.");
			return false;
		}
		
		if (checkEmailNum == 0) {
			alert("중복된 이메일입니다.");
			return false;
		}

		if (!validatePassword(form.pw.value)) {
			alert("비밀번호는 8자 이상이어야 하며, 영문 대소문자 및 숫자를 포함해야 합니다.");
			return false;
		}
		
		if (form.pw.value !== form.pwChk.value) {
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
		
		if (form.name.value.trim() !== form.name.value) {
			alert("이름 양끝에는 공백이 들어갈 수 없습니다.");
			return false;
		}
		
		if (form.name.value === null || form.name.value === "") {
			alert("이름은 공백이 될 수 없습니다.");
			return false;
		}
		return true;
	}

	function validateEmail(str) {
		const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		return emailRegex.test(str);
	}

	function validatePassword(str) {
		const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
		return passwordRegex.test(str);
	}

</script>
</body>
</html>