<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
{
    "mypagelist": [
        <c:forEach var="mypage" items="${mypagelist}" varStatus="status">
            {
                "seq_board" : "${ mypage.seq_board }",
                "seq_member" : "${ mypage.seq_member}",
                "image": "${ mypage.image}",
                "imageSubject" : "${ mypage.imageSubject }",
                "imageContent" : "${ mypage.imageContent }",
                "hit":"${ mypage.hit }",
                "logtime": "<fmt:formatDate value='${ mypage.logtime }' pattern='yyyy.MM.dd hh:mm a'/>",
                "name" : "${ mypage.name }"
            }
             <c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ]
}

