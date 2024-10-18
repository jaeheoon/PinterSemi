//이미지 스크랩
$(function(){
	$('#scrapBtn').click(function(){
	let seq_member = $('#data').data('seq-member');
		$.ajax({
			type : 'post',
			url : '/Inbeomstagram/boardScrap/scrap',
			data : {'seq_board' : $('#seq_board').val(),
					'seq_member' : $('#seq_member').val()},
			success : function(){
				console.log('스크랩완료');
			},
			error : function(e){
				console.log(e);
			}
		});
	});
});