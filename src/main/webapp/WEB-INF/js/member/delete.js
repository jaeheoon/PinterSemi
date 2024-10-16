$('#memberDelete').on('click', function() {
	if (confirm("정말로 탈퇴하시겠습니까?")) {
	        $.ajax({
	            url: `/Inbeomstagram/member/delete.do`,  // 탈퇴 처리 URL
	            type: 'POST',  // DELETE method가 아니라 POST method 사용
	            success: function(response) {
	                alert('회원 탈퇴가 완료되었습니다.');
	                window.location.href = `/Inbeomstagram/index.jsp`;  // 탈퇴 후 메인 페이지로 리다이렉트
	            },
	            error: function(xhr, status, error) {
	                alert('회원 탈퇴 처리 중 오류가 발생했습니다. 다시 시도해 주세요.');
	            }
	        });
	    }
});