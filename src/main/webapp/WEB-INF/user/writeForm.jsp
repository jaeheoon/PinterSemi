<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<link rel="stylesheet" href="../css/write.css">
</head>
<body>
	<div id="container-header">
		<a href="/pin/">
			<img id="mang" alt="망그러진곰" src="../image/mangom3.png">
		</a>
	</div>
	<div id="container">
		<div id="edit-header">회원가입</div>
		<form name="writeForm" id="writeForm">
			<table>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" placeholder="이름 입력" name="name" id="name">
						<div id="nameDiv"></div>
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" placeholder="아이디 입력" name="id" id="id">
						<input type="hidden" id="check" value="">
						<div id="idDiv"></div>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" placeholder="비밀번호 입력" name="pwd" id="pwd">
						<div id="pwdDiv"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" id="joinBtn" value="회원가입">
						<input type="reset" id="resetBtn" value="다시입력">
						<input type="button" value="뒤로" onclick="history.go(-1);">
					</td>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/write.js"></script>
</body>
</html>