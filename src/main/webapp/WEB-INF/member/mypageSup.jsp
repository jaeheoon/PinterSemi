<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
{
    "mypagelist": [
        <c:forEach var="mypagelist" items="${mypagelist}" varStatus="status">
            {
                "seq_board" : "${mypagelist.seq_board }",
                "seq_member" : "${ mypagelist.seq_member}",
                "image": "${mypagelist.image}",
                "imageSubject" : "${mypagelist.imageSubject }",
                "imageContent" : "${mypagelist.imageContent }",
                "hit":"${mypagelist.hit }",
                "logtime": "<fmt:formatDate value='${ mypagelist.logtime }' pattern='yyyy.MM.dd hh:mm a'/>",
                "name" : "${mypagelist.name }"
            }
             <c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ]
}

