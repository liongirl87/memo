<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<h1>글상세</h1>
		<input type="text" id="subject" class="form-control" placeholder="제목을 입력하세요." value="${post.subject}">
		<textarea rows="10" class="form-control" id="content" placeholder="글 내용을 입력하세요.">${post.content}</textarea>
		
		<%-- 이미지가 있을 떄만 이미지 영역 추가 --%>
		<!-- not empty 비어있거나 Null이거나 둘다 해당 -->
		<c:if test="${not empty post.imagePath }">	
			<div class="mt-3">
				<img src="${post.imagePath}" alt="업로드 된 이미지" width="300">
			</div>			
		</c:if>
		
		<div class="d-flex justify-content-end my-4">
			<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif">
		</div>
		<div class="d-flex justify-content-between">
			<button type="button" id="postDeleteBtn" class="btn btn-secondary" data-post-id="${post.id}">삭제</button>
			
			<div>
				<a href="/post/post_list_view" class="btn btn-dark">목록</a> 
				<button type="button" id="saveBtn" class="btn btn-warning" data-post-id="${post.id}">수정</button>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	// 수정 버튼 클릭
	$('#saveBtn').on('click',function(){
		let subject = $("#subject").val().trim();
		let content = $("#content").val().trim();
		let file = $('#file').val(); //C:\fakepath\rough-collie-5778136_960_720.jpg
		let postId = $(this).data("post-id");
		
		if (!subject) {
			alert("제목을 입력하세요");
			return;
		}
		console.log(subject);
		console.log(content);
		console.log(file);
		console.log(postId);
		
		// 파일이 업로드 된 경우 확장자 체크 (file)로 해도 똑같음
		if (file != ''){
			let ext = file.split(".").pop().toLowerCase();
			// 배열안의 글자가 없으면 결과값이 -1이 나옴
			if ($.inArray(ext, ['jpg', 'jpeg', 'gif', 'png']) == -1) {
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$('#file').val(''); // 파일을 비운다
				return;
			}
		}
		// 폼태그를 자바스크립트에서 만든다(이미지 때문에)
		let formData = new FormData();
		formData.append("postId", postId);
		formData.append("subject", subject);
		formData.append("content", content);
		formData.append("file", $('#file')[0].files[0]);
		
		//AJAX
		$.ajax({
			//request
			type:"PUT"
			, url:"/post/update"
			, data:formData
			, encType:"multipart/form-data"  // 파일 업로드를 위한 필수 설정
			, processData:false // 파일 업로드를 위한 필수 설정
			, contentType:false // 파일 업로드를 위한 필수 설정
			// response
			
			//response
			 , success:function(data) {
				 if(data.code == 1) {
					 alert("메모가 수정되었습니다.");
					 location.reload(true);
				 } else {
					 alert(data.errorMessage);
				 }
			 }
			, error:function(request, status, error) {
				alert("메모 수정이 실패했습니다.");
			}
		});
		
		
	});
	// 삭제
	$('#postDeleteBtn').on('click', function(){
		let postId = $(this).data('post-id');
		//alert(postId);
		
		$.ajax({
			type:"DELETE"
			, url:"/post/delete"
			, data:{"postId":postId}
			, success:function(data){
				if (data.code == 1) {
					alert("삭제 되었습니다.");
					location.href = "/post/post_list_view"
				} else {
					alert(data.errorMessage);
				}
			}
			, error:function(request, status, error) {
				alert("메모를 삭제하는데 실패했습니다.")
			}
		});
	});
	
	
});

</script>