<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript">
	function login() {
		document.login_form.submit();
	}
</script>
<title>커뮤니티 생성</title>
</head>
<body>
	<!-- Main Navigation ========================================================================================== -->
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>
	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="page-header"></div>
		<div class="well">
			<p>커뮤니티 이름을 입력하세요.</p>
			<form class="form-horizontal" action="${ctx}/board/create"
				method="post">
				<fieldset>
					<div class="form-group">
						<label class="col-lg-2 control-label">커뮤니티 이름</label>
						<div class="col-lg-6">
							<input type="text" name="board_name" class="form-control"
								placeholder="아이디" required />
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-6 col-lg-offset-2">
							<button class="btn btn-default btn-sm" type="submit">생성</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>