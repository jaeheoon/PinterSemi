$(function(){
	// 유효성 검사 함수
	function validateForm() {
		// 오류 메시지 초기화 및 숨김
		$('#nameDiv').html('');
		$('#idDiv').html('');
		$('#pwdDiv').html('');
		
		var isValid = true;
		
		// 비밀번호 입력 검사
		if ($('#pwd').val() === ''){
		   $('#pwdDiv').html('비밀번호를 입력해주세요'); 
		   $('#pwd').focus();
		   isValid = false;
		}
		
		// 아이디 입력 검사
		if ($('#id').val() === ''){
		   $('#idDiv').html('아이디를 입력해주세요');
		   $('#id').focus();
		   isValid = false;
		}
		
		// 이름 입력 검사
		if ($('#name').val() === ''){
		   $('#nameDiv').html('이름을 입력해주세요'); // 오류 메시지 표시
		   $('#name').focus();
		   isValid = false;
		}
		
		// 중복체크 (joinBtn 클릭 시만 검사)
		if ($('#joinBtn').length && $('#id').val() !== $('#check').val()){
		   $('#idDiv').html('중복체크를 해주세요'); 
		   $('#id').focus();
		   isValid = false;
		}
		
		return isValid;
	}
	
	// 회원가입 버튼 클릭 시 유효성 검사
	$('#joinBtn').click(function(event){
		if (!validateForm()) {
			event.preventDefault(); // 폼 제출 방지
		} else {
			//$('form').submit();
			$.ajax({
				url: '/pin/user/write',
				type: 'POST',
				data: $('#writeForm').serialize(),
				success: function() {
					alert('회원가입 완료');
					location.href = '/pin/user/list';
				},
				error: function(e) {
					console.log(e);
				}
			});
		}
	});

	// ID 중복체크
	$('#id').on('focusout', function() {
		checkId();
	});
	
	// 초기화 버튼 클릭 시 입력 필드 및 오류 메시지 초기화
	$('#resetBtn').click(function(){
	    $('#nameDiv').html('');
	    $('#idDiv').html('');
	    $('#pwdDiv').html('');
	    $('#writeForm')[0].reset();
	});
});

// ID 중복체크 - focusout일때 사용할 function
function checkId() {
	let id = $('#id').val();
	if(id !== '') {
		$.ajax({
			url: '/pin/user/checkId',
			type: 'GET',
			data: { id: id },
			success: function(data) {
				console.log(data); // 응답 값 확인을 위해 로그 출력
				if(data.trim() === 'nonExist') {
					$('#idDiv').html('사용 가능한 아이디입니다.').css('color', 'blue');
					$('#check').val(id); // 중복 체크 성공 시, 숨겨진 필드에 아이디 저장
				} else {
					$('#idDiv').html('이미 사용 중인 아이디입니다.').css('color', 'red');
					$('#check').val(''); // 중복 체크 실패 시, 숨겨진 필드 초기화
				}
			},
			error: function() {
				$('#idDiv').html('아이디 중복 체크 중 오류가 발생했습니다.').css('color', 'red');
			}
		});
	} else {
		$('#idDiv').html('아이디를 입력해주세요.').css('color', 'red');
	}
}

