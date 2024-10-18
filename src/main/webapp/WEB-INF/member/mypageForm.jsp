<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InbeomStarGram</title>
<link rel="stylesheet" href="../css/mypage.css">
</head>
<body onload="onLoadmypage()">
<jsp:include page="../component/header.jsp" />
<form id="mypage">
	<div class="mypage">
		<div class="profile">
			<c:if test="${ memDTO.kakaoCheck == 'F' }">
				<c:choose>
					<c:when test="${not empty memDTO.userProfile }">
						<a href="/Inbeomstagram/member/updateForm" >
							<img src="https://kr.object.ncloudstorage.com/bitcamp-9th-pinter/storage/${ memDTO.userProfile }" alt="${memDTO.userOriginalProfile }" />
						</a>
					</c:when>
					<c:when test="${empty memDTO.userProfile }">
						<input type="button" value="H" class="submitprofile" onclick="location.href='/Inbeomstagram/member/updateForm'"/>
					</c:when>
				</c:choose>
			</c:if>
			<c:if test="${ memDTO.kakaoCheck == 'T' }">
				<c:choose>
					<c:when test="${not empty memDTO.kakaoProfile }">
						<a href="/Inbeomstagram/member/updateForm" >
							<img src="${ memDTO.kakaoProfile }" alt="카카오 사진" />
						</a>
					</c:when>
					<c:when test="${empty memDTO.kakaoProfile }">
						<input type="button" value="H" class="submitprofile" onclick="location.href='/Inbeomstagram/member/updateForm'"/>
					</c:when>
				</c:choose>
			</c:if>
		</div>
		<div class="username">
			${memDTO.name }
		</div>
		<div class="userid">
			<div class="icondiv">
				<img src="../img/icon.jpg">
			</div>
			<div class="iddiv">
				${memDTO.id }
			</div>
		</div>
		<div class="update">
			<a href="/Inbeomstagram/member/updateForm"><input type="button" value="프로필 수정"/></a>
		</div>
		<div class="save">
			생성됨
			<hr>
		</div>
		<!-- 작성한 이미지 출력 -->
		<div id="boardbody">
        	<div id="board-list">
        		<%-- <c:forEach var="dto" items="${list }">
        			<div class="grid-item">
						<a href="/Inbeomstagram/board/boardView?seq_board=${dto.seq_board}">
							<img src="https://kr.object.ncloudstorage.com/bitcamp-9th-pinter/storage/${dto.imageFileName}"
								alt="${dto.imageSubject}" />
						</a>
					</div>
        		</c:forEach> --%>
        	</div>
        </div>
	</div>
</form>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    let seq_member = '${memDTO.seq_member}';
    $.ajax({
    	type : 'post',
		url : '/Inbeomstagram/boardScrap/getBoardScrap',
		data : { 'seq_member' : seq_member },
		dataType: 'json',
        success: function(data) {
            let boardList = $("#board-list");
            boardList.empty();

            if (data.mypagelist && data.mypagelist.length > 0) {
                data.mypagelist.forEach(function(item) {
                    let imageUrl = "https://kr.object.ncloudstorage.com/bitcamp-9th-pinter/storage/" + item.image;
                    let postUrl = "/Inbeomstagram/board/boardView?seq_board=" + item.seq_board;
                    
                    let imgElement = $("<img>").attr("src", imageUrl).addClass("mypage-image");
                    let linkElement = $("<a>").attr("href", postUrl).append(imgElement);
                    
                    boardList.append(linkElement);
                });
            } else {
                boardList.append("<p class='no-data-message'>아직 저장된 핀이 없네요! 저장하고 싶은 핀들을 저장하세요!</p>");

                let createPinButton = $("<button type='button'>").text("핀 만들기").addClass("create-pin-button");
                createPinButton.on("click", function() {
                    window.location.href = "/Inbeomstagram/board/writeForm";
                });

                boardList.append(createPinButton);  // 버튼을 메시지 아래에 추가
            }
        },
        error: function(e) {
            console.log('실패 ', e);
        }
    });
});
</script>
</body>
</html>