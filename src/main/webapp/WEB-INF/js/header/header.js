$('#searchKeyword').on('keydown', function(event) {
    if (event.key === 'Enter') {  // Enter 키가 눌렸을 때
        event.preventDefault();  // 기본 동작 방지 (Enter로 인한 폼 제출 방지)
        let keyword = $('#searchKeyword').val();

        // URL에 keyword를 쿼리 파라미터로 추가하여 페이지 이동
        location.href = '/Inbeomstagram/searchPage/subjectSearchPage.do?keyword=' + encodeURIComponent(keyword);
    }
});