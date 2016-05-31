<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript">
	function login() {
		document.login_form.submit();
	}
</script>
<title>상세정보</title>
</head>
<body>

	<!-- Main Navigation ========================================================================================== -->
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>
	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="page-header"></div>
		<div class="row">
			<div class="col-lg-4">
				<table class="table table-striped table-hover table-condensed">
					<tr>
						<td>아이디</td>
						<td>${userinfo.userId}</td>
					</tr>
					<tr>
						<td>이름</td>
						<td>${userinfo.name}</td>
					</tr>
					<tr>
						<td>이메일</td>
						<td>${userinfo.email}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>