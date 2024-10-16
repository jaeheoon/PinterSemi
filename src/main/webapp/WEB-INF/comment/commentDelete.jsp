<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 삭제</title>
</head>
<body>
<script type="text/javascript">
// seq_board를 request에서 가져옵니다.
var seq_board = '${seq_board}';
if(seq_board) {
    location.href='${pageContext.request.contextPath}/board/boardView.do?seq_board=' + seq_board;
} else {
    alert("게시글 정보를 찾을 수 없습니다.");
    history.back();
}
</script>
</body>
</html>