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
<title>${board.boardName}</title>
</head>
<body>
	<!-- Main Navigation ========================================================================================== -->
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>
	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="page-header"></div>
		<div class="row">
			<div class="col-lg-8">
				<table class="table table-striped table-hover table-condensed">
					<tr>
						<td>글번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일시</td>
					</tr>
					<c:choose>
						<c:when test="${empty article_list}">
							<tr>
								<td colspan="4">작성한 글이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="article" items="${article_list}">
								<%--<c:if test="${companies.size() > 0}">--%>
								<tr>
									<td>${article.articleSeq}</td>
									<td><a
										href="${ctx}/board/article/content?board_seq=${param.board_seq}&article_seq=${article.articleSeq}">${article.title}</a></td>
									<td>${article.author.nickname}</td>
									<fmt:formatDate value="${article.regDate}" var="date"
										type="date" pattern="yyyy-MM-dd" />
									<td>${date}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>

			</div>
		</div>
		<c:if test="${login_user ne null}">
			<div class="row">
				<div class="col-lg-8">
					<button class="btn btn-default btn-sm"
						onclick="location.href='${ctx}/board/article/write?board_seq=${board.boardSeq}'">글쓰기</button>
				</div>
			</div>
		</c:if>
	</div>

	<%-- <form action="${ctx}/user/login.do" method="post">
			<input type="hidden" id="url" name="url"/>
			<c:choose> 
				<c:when test="${login_user eq null}">
					<input type="submit" onclick="return login()" value="로그인" />
					<input type="button" value="회원가입" onclick="location.href='${ctx}/user/join.do'" />
				</c:when>
				<c:when test="${login_user ne null}">
					<input type="button" onclick="location.href='${ctx}/board/article_write.do?board_no=${board.boardNo}'" value="글쓰기" />
				</c:when>
			</c:choose>
		</form> --%>
</body>
</html>