<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
{
    "commentList": [
        <c:forEach var="comment" items="${commentList}" varStatus="status">
            {
                "seq_comment": "${comment.seq_comment}",
                "seq_board" : "${comment.seq_board }",
                "name" : "${comment.name }",
                "commentContent":"${comment.commentContent }",
                "logtime": "<fmt:formatDate value='${ comment.logtime }' pattern='yyyy.MM.dd hh:mm a'/>"
            }
             <c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ]
}