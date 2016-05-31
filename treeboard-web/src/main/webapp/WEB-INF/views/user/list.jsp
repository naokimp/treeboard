<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/views/common/head.jsp"%>
<style type="text/css">
table#table {
	width: 50%;
}
</style>
<script type="text/javascript">
	function login() {
		document.login_form.submit();
	}
</script>
<title>회원 목록</title>
</head>
<body>
	<!-- Main Navigation ========================================================================================== -->
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>
	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="page-header"></div>
		<div class="row">
			<div class="col-lg-12">
				<div class="table-responsive">
					<table class="table table-striped table-hover table-condensed"
						id="table">
						<tr>
							<td>아이디</td>
							<td>이름</td>
							<td>이메일</td>
							<td>상세정보</td>
						</tr>
						<c:forEach var="user" items="${userlist}">
							<tr>
								<td>${user.userId}</td>
								<td>${user.name}</td>
								<td>${user.email}</td>
								<td><button type="button" class="btn btn-default btn-sm"
										onclick="location.href='${ctx}/user/info?userId=${user.userId}'">상세정보</button></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>