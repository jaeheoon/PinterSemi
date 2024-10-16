<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
	aria-labelledby="loginModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="loginModalLabel">로그인</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form id="loginForm" onsubmit="handleLogin(event)">
					<div class="form-group">
						<label for="loginId">아이디</label> <input type="text"
							class="form-control" id="loginId" name="loginId" placeholder="아이디 입력">
						<div id="loginIdDiv" style="color: red;"></div>
					</div>
					<div class="form-group">
						<label for="loginPassword">비밀번호</label> <input type="password"
							class="form-control" id="loginPassword" name="loginPassword	"
							placeholder="비밀번호 입력">
						<div id="loginPwdDiv" style="color: red;"></div>
					</div>
					<div class="modal-footer">
						<div id="loginResult"></div>
						<button type="submit" class="btn btn-primary" id="loginBtn"
							onclick="handleLogin()">로그인</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script src="${pageContext.request.contextPath}/js/member/login.js"></script>