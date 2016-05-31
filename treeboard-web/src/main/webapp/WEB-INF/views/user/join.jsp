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
input[type=text] {
	width: 60%;
}

input[type=password] {
	width: 60%;
}
</style>
<title>회원 가입</title>
</head>
<body>
	<!-- Main Navigation ========================================================================================== -->
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>
	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12">

				<div class="page-header">
					<h2 id="container">회원 가입하기</h2>
				</div>

				<div class="well">
					<c:choose>
						<c:when test="${param.success eq null}">
							<p>회원가입을 위해 아래 내용들을 작성해 주세요.</p>
							<form class="form-horizontal" action="${ctx}/user/join"
								method="post">
								<fieldset>
									<div class="form-group">

										<label class="col-lg-2 control-label">아이디</label>
										<div class="col-lg-10">
											<input type="text" name="userId" class="form-control"
												placeholder="아이디" required />
											<!-- <input type="button" value="중복확인" /> -->
										</div>
										<!-- TODO: 중복확인 버튼 구현. 가능하면 입력하고 포커스가 벗어나면 자동으로 중복확인을 하고 메시지를 출력하는 스크립트 작성 -->
										<label class="col-lg-2 control-label">비밀번호</label>
										<div class="col-lg-10">
											<input type="password" name="password" class="form-control"
												placeholder="비밀번호" required />
										</div>
										<label class="col-lg-2 control-label">비밀번호 확인</label>
										<!--TODO: 확인란에 입력이 끝나고 포커스가 벗어나면 자동으로 위의 비밀번호 입력과 비교해서 메시지 출력하는 스크립트 작성-->
										<div class="col-lg-10">
											<input type="password" class="form-control"
												placeholder="비밀번호 확인" required />
										</div>
										<label class="col-lg-2 control-label">이름</label>
										<div class="col-lg-10">
											<input type="text" name="name" class="form-control"
												placeholder="이름" required />
										</div>
										<label class="col-lg-2 control-label">닉네임</label>
										<div class="col-lg-10">
											<input type="text" name="nickname" class="form-control"
												placeholder="이름" required />
										</div>
										<label class="col-lg-2 control-label">이메일</label>
										<div class="col-lg-10">
											<input type="text" name="email" class="form-control"
												placeholder="이메일" required />
										</div>
									</div>
									<div class="form-group">
										<div class="col-lg-10 col-lg-offset-2">

											<button type="submit" class="btn btn-primary">가입</button>
											<button class="btn btn-default">다시입력</button>
										</div>
									</div>
								</fieldset>
							</form>
						</c:when>
						<c:when test="${param.success eq 'success'}">
							<label class="col-lg-2 control-label">가입 성공</label>
							<br />
							<br />
							<br />
							<br />
							<button class="btn btn-default"
								onclick="location.href='${ctx}/main'">메인페이지로</button>
						</c:when>
						<c:when test="${param.success eq 'fail'}">
							가입이 실패하였습니다
						</c:when>
					</c:choose>

				</div>
			</div>
		</div>
	</div>

</body>
</html>