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
<title>게시판 목록</title>
</head>
<body>
	<!-- Main Navigation ========================================================================================== -->
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>
	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="page-header"></div>
		<div class="row">
			<div class="col-lg-12">
				<c:forEach var="board" items="${board_list}">
					<%-- <a href="${ctx}/board/articleList.do?board_no=${board.boardNo}" >${board.boardNo}.${board.name}</a><br /> --%>
					<button type="button" class="btn btn-default btn-sm"
						onclick="location.href='${ctx}/board/article/list?board_seq=${board.boardSeq}'">
						${board.boardSeq}.${board.boardName}</button>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>