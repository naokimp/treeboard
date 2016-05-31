<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript">
	window.onload = function() {
		var url = document.referrer;
		document.getElementById("url").value = url;
	}
</script>
<style type="text/css">
body {
	padding-top: 100px;
	padding-bottom: 40px;
	background-color: #ecf0f1;
}

.login-header {
	max-width: 400px;
	padding: 15px 29px 25px;
	margin: 0 auto;
	background-color: #2c3e50;
	color: white;
	text-align: center;
	-webkit-border-radius: 15px 15px 0px 0px;
	-moz-border-radius: 15px 15px 0px 0px;
	border-radius: 15px 15px 0px 0px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.login-footer {
	max-width: 400px;
	margin: 0 auto 20px;
	padding-left: 10px;
}

.form-signin {
	max-width: 400px;
	padding: 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	-webkit-border-radius: 0px 0px 15px 15px;
	-moz-border-radius: 0px 0px 15px 15px;
	border-radius: 0px 0px 15px 15px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 15px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}

.form-btn {
	text-align: center;
	padding-top: 20px;
}

.form-fail {
	font-size: 20px;
	height: auto;
	padding-top: 20px;
	text-align: center;
}
</style>
<title>로그인</title>
</head>
<body>

	<header></header>

	<!-- Container ======================================================================================= -->
	<div class="container">

		<div class="login-header">
			<h2 class="form-signin-heading">나무보드</h2>
		</div>

		<form class="form-signin" action="${ctx}/user/login.do" method="post">
			<input type="text" name="userId" class="form-control"
				placeholder="아이디" required> <input type="password"
				name="password" class="form-control" placeholder="패스워드" required>
			<input type="hidden" id="url" name="url" value="${url}" />
			<div class="row form-btn">
				<button type="submit" class="btn btn-success">로그인</button>
				<button class="btn btn-primary"
					onclick="location.href='${ctx}/user/join'">회원가입</button>
				<button class="btn btn-default" onclick="history.back()">이전으로</button>
			</div>
			<c:if test="${param.success eq 'false'}">
				<div class="row form-fail">
					<span class="label label-warning">로그인이 실패했습니다.</span>
				</div>
			</c:if>
		</form>
	</div>

</body>
</html>