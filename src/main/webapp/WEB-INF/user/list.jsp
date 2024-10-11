<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/list.css">
</head>
<body>
	<div id="container-header">
		<a href="/pin/">
			<img id="mang" alt="망그러진곰" src="../image/mangom3.png">
		</a>
	</div>
	<div id="container">
		<div id="edit-header">회원정보 리스트</div>
        <table>
            <thead>
                <tr>
                    <th class="id">아이디</th>
                    <th class="name">이름</th>
                    <th class="pwd">비밀번호</th>
                </tr>
            </thead>
            <tbody>
                <!-- 목록 -->
                <c:forEach var="user" items="${ list }">
                	<tr>
                     <td>${ user.id }</td>
                     <td>${ user.name }</td>
                     <td>${ user.pwd }</td>
                 </tr>
                </c:forEach>
            </tbody>
        </table>
   	</div><!-- //div#container -->
    <div id="paging">
    </div>
<script type="text/javascript" src="../js/list.js"></script>
</body>
</html>