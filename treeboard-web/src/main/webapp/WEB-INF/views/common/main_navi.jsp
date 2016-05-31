<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a href="${ctx}/main" class="navbar-brand">나무보드</a>
		</div>
		<div class="navbar-collapse collapse navbar-responsive-collapse">
			<ul class="nav navbar-nav">
				<li id="home_navi"><a href="${ctx}/main">홈</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">게시판</a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/board/list">목록</a></li>
						<li><a href="${ctx}/board/create">생성</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">사용자</a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/user/list">목록</a></li>
						<c:if test="${login_user eq null}">
							<li><a href="${ctx}/user/join">가입</a></li>
						</c:if>
					</ul></li>
			</ul>
			<form action="${ctx}/user/login" method="post" name="login_form">
				<input type="hidden" id="url" name="url" />
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${login_user ne null}">
						<li><a>${login_user.name}님 로그인 중</a></li>
						<li><a href="${ctx}/user/logout">로그아웃</a></li>
					</c:if>
					<c:if test="${login_user eq null}">
						<li><a href="${ctx}/user/join">회원가입</a></li>
						<li><a href="javascript:login()">로그인</a></li>
					</c:if>
				</ul>
			</form>
		</div>
	</div>
</div>