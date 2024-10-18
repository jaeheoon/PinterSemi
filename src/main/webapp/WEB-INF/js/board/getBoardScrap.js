$('a[href="/Inbeomstagram/member/mypageForm"]').click(function(){
	$.ajax({
		type : 'post',
		url : '/Inbeomstagram/boardscrap/getBoardScrap',
		data : { 'seq_member' : seq_member },
		success : function(){
			console.log('데이터 조회 성공');
		},
		error : function(){
			console.log('데이터 조회 실패');
		}
	});
});