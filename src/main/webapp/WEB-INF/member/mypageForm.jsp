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
			<input type="submit" value="H" class="submitprofile"/>
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
			<a href="${pageContext.request.contextPath}/member/updateForm.do"><input type="button" value="프로필 수정"/></a>
		</div>
		<div class="save">
			생성됨
			<hr>
		</div>
		<!-- 작성한 이미지 출력 -->
		<div id="boardbody">
        	<div id="board-list">

        	</div>
        </div>
	</div>
</form>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    let seq_member = '${memDTO.seq_member}';
    $.ajax({
        type: "POST",
        url: "/Inbeomstagram/member/mypageSup.do",
        data: { 'seq_member': seq_member },
        dataType: 'json',
        success: function(data) {
            let boardList = $("#board-list");
            boardList.empty();

            if (data.mypagelist && data.mypagelist.length > 0) {
                data.mypagelist.forEach(function(item) {
                    let imageUrl = "http://localhost:8080/Inbeomstagram/storage/" + item.image;
                    let postUrl = "${pageContext.request.contextPath}/board/boardView.do?seq_board=" + item.seq_board;
                    
                    let imgElement = $("<img>").attr("src", imageUrl).addClass("mypage-image");
                    let linkElement = $("<a>").attr("href", postUrl).append(imgElement);
                    
                    boardList.append(linkElement);
                });
            } else {
                boardList.append("<p class='no-data-message'>아직 저장된 핀이 없네요! 저장하고 싶은 핀들을 저장하세요!</p>");

                let createPinButton = $("<button type='button'>").text("핀 만들기").addClass("create-pin-button");
                createPinButton.on("click", function() {
                    window.location.href = "${pageContext.request.contextPath}/board/boardWriteForm.do";
                });

                boardList.append(createPinButton);  // 버튼을 메시지 아래에 추가
            }
        },
        error: function(e) {
            console.log('실패', e);
        }
    });
});
</script>
</body>
</html>