<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>이미지 게시글 폼</title>
<link rel="stylesheet" type="text/css" href="../css/boardView.css">
<style>
#comment-list {
    max-height: 300px;
    overflow-y: auto;
    padding-right: 10px;
}
.comment-content {
    max-height: 20px;
    overflow: hidden;
    position: relative;
    margin-bottom: 10px;
}
.comment-full {
    max-height: none;
}
.more-btn, .less-btn {
    position: absolute;
    bottom: 0;
    right: 0;
    background: white;
    cursor: pointer;
    color: blue;
}
</style>
</head>
<body onload="onLoadpage()">
	<jsp:include page="../component/header.jsp" />

	<form id="container" method="POST"
		action="${pageContext.request.contextPath}/board/boardUpdateForm.do">
		<span class="closeBtn" onclick="closePage()">&times;</span> <input
			type="hidden" id="name" name="name" value="${memDTO.name}"> <input
			type="hidden" id="seq_board" name="seq_board"
			value="${boardDTO.seq_board }"> <input type="hidden"
			id="image" name="image" value="${boardDTO.image}"> <input
			type="hidden" id="imageSubject" name="imageSubject"
			value="${boardDTO.imageSubject}"> <input type="hidden"
			id="imageContent" name="imageContent"
			value="${boardDTO.imageContent}"> <input type="hidden"
			id="password" name="password" value="${memDTO.password}">
		<div id="image">
			<img
				src="http://localhost:8080/Inbeomstagram/storage/${boardDTO.image }" />
		</div>
		<div id="des">
			<!-- 제목 -->
			<div id="subject">
				<h2>${boardDTO.imageSubject }</h2>
			</div>
			<!-- 내용 -->
			<div id="content">
				<p>${boardDTO.imageContent }</p>
			</div>
			<!-- 작성자 -->
			<div id="userName">
				<h4>작성자 : ${boardDTO.name }</h4>
			</div>
			<!-- 댓글 영역 -->
			<div id="comment-box">
				<!-- 댓글 수 -->
				<div id="comment-num">댓글 2개</div>


				<!-- 댓글 내용 -->
				<div id="comment-list">
					<c:forEach var="comment" items="${commentList}">
						<input type="text" value="${memDTO.name }" />
						<div class="comment-content">

							<strong>${comment.name}</strong> : ${comment.commentContent}
							(${comment.logtime})
							<!-- 로그인한 사용자와 댓글 작성자가 같을 때만 버튼을 표시 -->
							<button class="options-btn" data-seq="${comment.seq_comment}">⋯</button>
						</div>
					</c:forEach>
				</div>

				<!-- 댓글 작성 -->
				<div id="comment-input">
					<div id="commentDiv"></div>
					<textarea id="commentContent" name="commentContent"></textarea>
					<button type="button" id="commentBtn">작성</button>
				</div>

				<div id="pin-buttons">
					<c:if test="${memDTO.seq_member == boardDTO.seq_member}">
						<input type="submit" id="updateBtn" value="수정" />
						<input type="button" id="deleteBtn" value="삭제" />
					</c:if>
				</div>

			</div>
		</div>
	</form>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
	// 댓글 개수 함수
	function updateCommentCount(count) {
	    $('#comment-num').text('댓글 ' + count + '개');
	}

	$('#deleteBtn').click(function() {
	    var confirmation = confirm("정말 삭제하시겠습니까?");
	    if (confirmation) {
	        $('#container').attr('action', '${pageContext.request.contextPath}/board/boardDelete.do');
	        $('#container').submit();
	    } else {
	        console.log("삭제가 취소되었습니다.");
	    }
	});




	function onLoadpage(){
		let seq_Board = '${boardDTO.seq_board}';
		console.log('seq : ', seq_board);
		
		 // 게시글 조회수 증가
		 $.ajax({
		        type: 'post',
		        url: '/Inbeomstagram/comment/commentHit.do', // 조회수 증가를 위한 URL
		        data: { 'seq_board': seq_Board },
		        dataType: 'json',
		        success: function(response) {
		            console.log('조회수 증가 성공: ', response);
		        },
		        error: function(e) {
		            console.log("조회수 증가 AJAX 실패: ", e);
		        }
		    });
		
		// 댓글목록 뿌리기
		$.ajax({
			type:'post',
			url:'/Inbeomstagram/comment/commentView.do',
			data: { 'seq_board': seq_Board },
			dataType:'json',
			success : function(data){
				console.log('데이터 받아왔다 목록 : ', data)
				updateCommentList(data.commentList);
				
				// 댓글 개수 업데이트
	            updateCommentCount(data.commentList.length);
				
			},
			error : function(e){
				console.log("ajax 실패 : ", e);
			}
		});
		 
	}

	function closePage() {
	   window.history.back(); // 이전 페이지로 이동
	}
	   
	$(function(){
		var name = '${sessionScope.memDTO.name}';
		$('#commentBtn').click(function(){
			event.preventDefault();
			if($('#commentContent').val() == ''){
				$('#commentDiv').html('작성하실 댓글을 입력하세요').css('color','red');
			}else{
				$.ajax({
					type:'post',
					url : '/Inbeomstagram/comment/commentWrite.do',
					data : {
						'commentContent' : $('#commentContent').val(),
						'name': name,
						'seq_board' : $('#seq_board').val()
					},
					dataType:'json',
					success:function(data){
						alert("댓글 등록 완료");		
						console.log("AJAX성공")
						$('#commentContent').val('');
						updateCommentList(data.commentList);
						
						updateCommentCount(data.commentList.length);  // 댓글 개수 업데이트
						
					},
					error:function(e){
						console.log("AJAX 실패 : ",e);
					}
					
				});
			}
			
		});
	});

	function updateCommentList(commentList) {
		var name = '${sessionScope.memDTO.name}';
	    $('#comment-list').empty(); // 기존 댓글 목록을 지우고 새로 추가
	    commentList.forEach(function(comment) {
	        var commentHtml = 
	            '<div id="comment-content">' +
	            '<strong>' + comment.name + ' : </strong>' +
	            comment.commentContent + '<br>(' + comment.logtime + ')';

	        if (comment.name === name) {
	            commentHtml += 
	                '<button class="options-btn" data-seq="' + comment.seq_comment + '">⋯</button>';
	        }

	        commentHtml += '</div>';

	        $('#comment-list').append(commentHtml);
	    });
	    
	    // 댓글 개수 업데이트
	    updateCommentCount(commentList.length);

	    // 댓글이 5개 이상일 때 스크롤 추가
	    if (commentList.length > 5) {
	        $('#comment-list').css('overflow-y', 'scroll');
	    } else {
	        $('#comment-list').css('overflow-y', 'visible');
	    }
	}

	$(document).on('click', '.options-btn', function(event) {
	    event.preventDefault();
	    var $commentContent = $(this).parent();
	    
	    var seqComment = $(this).data('seq');  
	    var $editDeleteDiv = $commentContent.find('.edit-delete');
	    
	    // 수정/삭제 버튼이 있는지 확인
	    if ($editDeleteDiv.length > 0) {
	        $editDeleteDiv.slideToggle(200);
	    } else {
	        // 수정/삭제 버튼을 처음으로 추가
	        var editDeleteHtml = 
	            '<div class="edit-delete" style="margin-top: 5px; display: none;">' +
	            '<input type="button" id="edit-btn" class="edit-btn" data-seq="' + seqComment + '" value="수정">' +
	            '<input type="button" class="delete-btn" data-seq="' + seqComment + '" value="삭제">' + // 클래스 변경
	            '</div>';
	            
	        $commentContent.append(editDeleteHtml);
	        $commentContent.find('.edit-delete').slideDown(200);
	    }
	});

	// 삭제 버튼 클릭 이벤트
	$(document).on('click', '.delete-btn', function(event) {
	    event.preventDefault();
	    var seqComment = $(this).data('seq');
	    console.log("너는 값이뭐니? ", seqComment);  // seqComment 확인
	    var seqBoard = $('#seq_board').val(); 
	    var confirmation = confirm("댓글을 삭제할까요?");
	    if (confirmation) {
	        $.ajax({
	            type: 'POST',
	            url: '${pageContext.request.contextPath}/comment/commentDelete.do',
	            data: { 
	                'seq_comment': seqComment,
	                'seq_board': seqBoard  
	            },
	            success: function(response) {
	                alert("댓글이 삭제되었습니다.");
	                onLoadpage();
	            },
	            error: function(error) {
	                console.log("댓글 삭제 실패: ", error);
	            }
	        });
	    } else {
	        console.log("삭제가 취소되었습니다.");
	    }
	});


	// 수정 버튼 클릭 이벤트
	$(document).on('click', '#edit-btn', function(event) {
	    event.preventDefault();
	    
	    // 클릭된 댓글 영역
	    var $commentContent = $(this).closest('#comment-content');  
	    
	    // seq_comment 가져오기
	    var seqComment = $(this).data('seq');
	    
	    console.log("seq :" ,seqComment);
	    
	    // 댓글 내용을 포함하는 태그에서 텍스트를 가져오기
		 var currentComment = $commentContent.clone()    // 요소 복제
							  .children().remove().end()    // 모든 자식 요소 제거
							  .text().trim()    // 남은 텍스트 가져오기
							  .replace(/\s*\([^)]*\)\s*$/, '')  // 마지막 괄호 안의 내용 제거
							  .trim();  // 앞뒤 공백 제거
							  
	    console.log("currentComment :" ,currentComment);
	    
		var editHtml = 
	    '<textarea id="edit-comment">' + currentComment + '</textarea>' +
	    '<button id="save-edit-btn" data-seq="' + seqComment + '">저장</button>' +
	    '<button id="cancel-edit-btn">취소</button>';
	            
	    // 기존 댓글 내용을 수정 창으로 교체
	    $commentContent.html(editHtml);
	});



	// 수정 저장 버튼 클릭 이벤트
	$(document).on('click', '#save-edit-btn', function(event) {
	    event.preventDefault();
	    
	    var seqComment = $(this).data('seq');
	    var updatedComment = $('#edit-comment').val();  // 수정된 댓글 내용
	    var seqBoard = $('#seq_board').val();  // 게시글 고유번호
	    
	    
	    
	    $.ajax({
	        type: 'POST',
	        url: '${pageContext.request.contextPath}/comment/commentUpdate.do',  // 수정 처리 URL
	        data: { 
	            'seq_comment': seqComment,
	            'seq_board': seqBoard,
	            'commentContent': updatedComment  // 수정된 댓글 내용
	        },
	        success: function(response) {
	            alert("댓글이 수정되었습니다.");
	            onLoadpage();  // 페이지 새로고침 또는 댓글 목록 업데이트
	        },
	        error: function(error) {
	            console.log("댓글 수정 실패: ", error);
	            console.log("seqComment : ", seqComment);
	            console.log("updatedComment : ", updatedComment);
	            console.log("seqBoard : ", seqBoard);
	            
	        }
	    });
	});

	// 수정 취소 버튼 클릭 이벤트
	$(document).on('click', '#cancel-edit-btn', function(event) {
	    event.preventDefault();
	    
	    // 수정 취소 시, 기존 댓글 내용으로 돌아가도록 처리
	    onLoadpage();  // 페이지 새로고침 또는 댓글 목록 업데이트
	});

	</script>
</body>
</html>