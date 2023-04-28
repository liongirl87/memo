<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center mt-3 mb-3">
	<div class="w-50">
		<h1>글 목록</h1>
		
		<table class="table text-center table-bordered table-hover">
			<thead>
				<tr class="border rounded bg-light">
					<th>No.</th>
					<th>제목</th>
					<th>작성날짜</th>
					<th>수정날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${postList}" var="post">
				<tr>
					<td>${post.id}</td>
					<td><a href="/post/post_detail_view?postPage=${postPaging.nowPageNum}&postId=${post.id}">${post.subject}</a></td>
					<td>
					<fmt:formatDate value="${post.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>
					<fmt:formatDate value="${post.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 페이징 -->
		<div class="d-flex justify-content-center">
			<c:if test="${prevId ne 0}">
			<a href="/post/post_list_view?prevId=${prevId}" class="mr-5">&lt;&lt; 이전</a>
			</c:if>
			<c:if test="${nextId ne 0}">
			<a href="/post/post_list_view?nextId=${nextId}">다음 &gt;&gt;</a>
			</c:if>
		</div>
		
		<!-- 페이징 버튼 TEST -->
		<div class="list_number">
    		<div>
	        	<p>
	        		<div class="list_n_menu">
	        			<a href="/post/post_list_view?postPage=${postPaging.nowPageNum - 1}" class="${postPaging.nowPageNum == 1 ? "disabled":"" }"><  이전</a>
		        		<c:forEach begin="${postPaging.startPage}" end="${postPaging.endPage}" var="num">
		        		<a href="/post/post_list_view?postPage=${num }" class="${postPaging.nowPageNum == num ? "current":"" }">${num}</a>
		        		</c:forEach>
		        		<a href="/post/post_list_view?postPage=${postPaging.nowPageNum + 1}" class="${postPaging.nowPageNum == postPaging.totalPages ? "disabled":"" }">다음  >
		        		</a>
	        		</div>
	        	</p>
    		</div>
		</div>
		<!--  페이징 버튼 TEST 끝 -->
		
		<div class="d-flex justify-content-end">
			<a href="/post/post_create_view" class="btn btn-warning">글쓰기</a>
		
		</div>
	</div>
</div>