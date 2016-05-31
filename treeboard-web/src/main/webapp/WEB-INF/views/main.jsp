<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript">
	window.onload = function() {
		//	document.getElementById("home_navi").className = "active";
		document.getElementById("home_navi").setAttribute("class", "active");
	};
	/* $(document).ready(function() {
	 $("#home_navi").addClass("active");
	 }); */
	function login() {
		document.login_form.submit();
	}
</script>
<title>회원 목록</title>
</head>
<body>
	<!-- Main Navigation ========================================================================================== -->
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>
	<!-- Header ========================================================================================== -->
	<header> </header>
	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="page-header"></div>
		<div class="row">
			<div class="col-lg-12">
				<button type="button" class="btn btn-default btn-sm"
					onclick="location.href='${ctx}/board/list'">게시판 목록</button>
				<button type="button" class="btn btn-default btn-sm"
					onclick="location.href='${ctx}/board/create'">게시판 생성</button>
				<button type="button" class="btn btn-default btn-sm"
					onclick="location.href='${ctx}/user/list'">사용자 목록</button>
				<c:if test="${login_user eq null}">
					<button type="button" class="btn btn-default btn-sm"
						onclick="location.href='${ctx}/user/join'">회원가입</button>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
