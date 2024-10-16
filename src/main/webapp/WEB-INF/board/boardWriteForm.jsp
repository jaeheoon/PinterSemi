<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>핀 만들기</title>
<link rel="stylesheet" type="text/css" href="../css/boardWriteForm.css">
</head>
<body>
	<jsp:include page="../component/header.jsp" />
	<div class="container">
		<div class="card">
			<!-- 헤더 -->
			<div class="card-header">
				<h2>핀 만들기</h2>
				<span class="closeBtn">&times;</span>
			</div>
			<!-- 폼 시작 -->
			<form id="boardWriteForm">
			<!-- member 시퀀스 가져오기 -->
				<input type="hidden" id="seq_member" name="seq_member" value="${sessionScope.memDTO.seq_member}"/>	
				<input type="hidden" id="name" name="name" value = "${memDTO.name }"/>
					<div class="card-content">
					
					<label class="image-upload"> 이미지 업로드 
					<input type="file" name="image" id="image"/>
					</label>
					<div id="imageDiv"></div>
				
					<div class="form">
						<div class="form-group">
							<label>제목</label> 
							<input type="text" id="imageSubject" name="imageSubject"/>
							<div id="imageSubjectDiv"></div>
						</div>
						<div class="form-group">
							<label>내용</label>
							<textarea id="imageContent" name="imageContent"></textarea>
							<div id="imageContentDiv"></div>
						</div>
						<button type="submit" class="boardWriteBtn">핀 만들기</button>
					</div>
				</div>
			</form>
			<!-- 폼 끝 -->
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="../js/board/boardWrite.js"></script>
</body>
</html>