function logout() {
    $.ajax({
        type: 'GET',
        url: '/Inbeomstagram/member/logout.do',
        success: function() {
            // 로그아웃 성공 시 index.jsp로 리다이렉트
            window.location.href = '/Inbeomstagram/index.jsp';
        },
        error: function(xhr, status, error) {
            console.error('로그아웃 실패:', error);
        }
    });
}
