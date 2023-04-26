<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex h-100">
	<div class="col-6 d-flex align-items-center h-100">
		<h1>MEMO 게시판</h1>
	</div>
	<div class="col-6 d-flex justify-content-end align-items-end mb-2">
		<c:if test="${not empty userId}">
			<span class="mr-3">${userName}님 안녕하세요</span>
			<a href="/user/sign_out" class="font-weight-bold mr-3">로그아웃</a>
		</c:if>	
		<c:if test="${empty userId }">
			<a href="/user/sign_in_view" class="font-weight-bold">로그인</a>
		</c:if>
	</div>


</div>
