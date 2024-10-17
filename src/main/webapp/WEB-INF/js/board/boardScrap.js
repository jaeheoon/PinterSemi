//이미지 스크랩
$(function(){
	$('#scrapBtn').click(function(){
		$.ajax({
			type : 'post',
			url : '/Inbeomstagram/board/scarp',
			data : {'seq_board' : $('#seq_board').val()},
			success : function(){
				console.log('스크랩완료');
			},
			error : function(e){
				console.log(e);
			}
		});
	});
});