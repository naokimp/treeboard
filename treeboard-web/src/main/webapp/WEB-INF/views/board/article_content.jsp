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
function replyModify(num) {
	document.getElementById("reply_modify"+num).style.display = "none";
	document.getElementById("reply_modify_confirm"+num).style.display = "inline";
	document.getElementById("reply_content"+num).style.display = "none";
	document.getElementById("reply_modify_box"+num).style.display = "inline";
	document.getElementById("reply_delete"+num).style.display = "none";
}
</script>
<style type="text/css">
input[type="text"] {
	border: 0;
	background: transparent;
}

textarea {
	border: 1px solid #999999;
	background: transparent;
	border-radius: 5px;
	padding: 5px;
	resize: none;
}

textarea.article_content {
	width: 100%;
	height: 350px;
	float: left;
}

textarea.reply_write {
	width: 80%;
	height: 120px;
	float: left;
}

textarea.reply_content {
	width: 80%;
	height: 120px;
	float: left;
}
</style>
<title>${board.boardName}</title>
</head>
<body>
	<!-- Main Navigation ========================================================================================== -->
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>
	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="page-header"></div>
		<fmt:formatDate value="${article.regDate}" var="date" type="date"
			pattern="yyyy년 M월 d일" />
		<div class="row">
			<div class="col-lg-8">
				<table class="table table-striped table-condensed">
					<tr>
						<td>글번호</td>
						<td><input type="text" value="${article.articleSeq}"
							readonly="readonly"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" value="${article.title}"
							readonly="readonly"></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td><a
							href="${ctx}/user/info?userId=${article.author.userId}"><input
								type="text" value="${article.author.nickname}"
								readonly="readonly"></a></td>
					</tr>
					<tr>
						<td>작성일시</td>
						<td><input type="text" value="${date}" readonly="readonly"></td>
					</tr>
					<tr>
						<td colspan="2"><textarea class="article_content"
								readonly="readonly" style="padding: 20px;">${article.content}</textarea></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8">
				<input type="hidden" id="url" name="url" />
				<c:if test="${login_user ne null}">
					<button class="btn btn-default btn-sm"
						onclick="location.href='${ctx}/board/article/write?board_seq=${board_seq}'">글쓰기</button>
				</c:if>
				<c:if test="${article.author.userId eq login_user.userId}">
					<button class="btn btn-default btn-sm"
						onclick="location.href='${ctx}/board/article/modify?article_seq=${article.articleSeq}'">수정</button>
					<button class="btn btn-default btn-sm"
						onclick="location.href='${ctx}/board/article/delete?board_seq=${param.board_seq}&article_seq=${article.articleSeq}'">삭제</button>
				</c:if>
				<button class="btn btn-default btn-sm"
					onclick="location.href='${ctx}/board/article/list?board_seq=${board_seq}'">목록보기</button>
			</div>
		</div>
		<c:if test="${login_user ne null}">
			<div class="row">
				<div class="col-lg-7">
					<form
						action="${ctx}/board/reply/write?board_seq=${param.board_seq}&article_seq=${article.articleSeq}"
						method="post">
						<table class="table table-striped table-condensed">
							<tr>
								<td>${login_user.name}</td>
							</tr>
							<tr>
								<td><textarea name=content class="reply_write"></textarea>
									<button class="btn btn-default btn-sm" style="margin: 5px;"
										type="submit">작성</button></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</c:if>
		<div class="row">
			<div class="col-lg-7">
				<c:forEach var="reply" items="${replies}">
					<form
						action="${ctx}/board/reply/modify?board_seq=${board_seq}&article_seq=${article.articleSeq}&reply_seq=${reply.replySeq}"
						method="post">
						<table class="table table-striped table-condensed">
							<tr>
								<fmt:formatDate value="${reply.regDate}" var="date" type="date"
									pattern="yyyy-MM-dd" />
								<td>${reply.replySeq}.${reply.author.nickname}(${date})</td>
							</tr>
							<tr>
								<td><textarea readonly="readonly" class="reply_content"
										id="reply_content${reply.replySeq}">${reply.content}</textarea>
									<textarea style="display: none;" class="reply_content"
										id="reply_modify_box${reply.replySeq}" name="content">${reply.content}</textarea>
									<input type="hidden" name="author_id"
									value="${reply.author.userId}"> <c:if
										test="${reply.author.userId eq login_user.userId}">
										<div style="float: left; width: 10%;">
											<button type="button" class="btn btn-default btn-sm"
												style="margin: 5px;" id="reply_modify${reply.replySeq}"
												onclick="replyModify(${reply.replySeq})">수정</button>
											<button type="submit" class="btn btn-default btn-sm"
												style="margin: 5px; display: none;"
												id="reply_modify_confirm${reply.replySeq}">완료</button>
											<button type="button" class="btn btn-default btn-sm"
												style="margin: 5px;"
												id="reply_delete${reply.replySeq}"
												onclick="location.href='${ctx}/board/reply/delete?board_seq=${board_seq}&article_seq=${article.articleSeq}&reply_seq=${reply.replySeq}'">삭제</button>
										</div>
									</c:if></td>
							</tr>
						</table>
					</form>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>