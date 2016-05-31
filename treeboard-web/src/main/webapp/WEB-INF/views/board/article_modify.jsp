<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript">
	function login() {
		document.login_form.submit();
	}
</script>
<style type="text/css">
input[type="text"]#name {
	border: 0;
	background: transparent;
}

input[type="text"] {
	border-radius: 5px;
	border: 1px solid #999999;
	width: 90%;
	background: transparent;
}

textarea {
	border: 1px solid #999999;
	background: transparent;
	border-radius: 5px;
	padding: 5px;
	resize: none;
	width: 100%;
	height: 350px;
	float: left;
}
</style>
<title>글쓰기</title>
</head>
<body>
	<!-- Main Navigation ========================================================================================== -->
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>
	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="page-header"></div>
		<div class="row">
			<div class="col-lg-8">
				<form
					action="${ctx}/board/article/modify?board_seq=${board_seq}&article_seq=${article.articleSeq}"
					method="post">
					<table class="table table-striped table-condensed">
						<tr>
							<td>글제목</td>
							<td><input type="text" value="${article.title}" name="title"></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td><input type="text" value="${article.author.nickname}"
								readonly="readonly" id="name"></td>
						</tr>
						<tr>
							<td colspan="2"><textarea cols="100" rows="20"
									name="content">${article.content}</textarea></td>
						</tr>
					</table>
					<button class="btn btn-default btn-sm" type="submit">등록</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>