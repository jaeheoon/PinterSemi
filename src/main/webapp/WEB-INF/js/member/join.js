// 아이디와 비밀번호 검증 정규식
var idRegex = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{1,50}$/;
var pwdRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]{8,100}$/; // 비밀번호는 8자 이상
// 아이디 중복 체크
$('#id').focusout(function() {
    $('#idDiv').empty();
    var idValue = $('#id').val();
    
    if (idValue == '') {
        $('#idDiv').html('먼저 아이디 입력');
    } else if (!idRegex.test(idValue)) {
        $('#idDiv').html('아이디는 영문, 숫자 조합으로 50자 이내로 입력하세요.');
        return;
    } else {
        $.ajax({
            type: 'post',
            url: '/Inbeomstagram/member/checkId.do',
            data: 'id=' + idValue,
            dataType: 'text',
            success: function(data) {
                if (data.trim() == 'exist') {
                    $('#idDiv').html('이미 사용중인 아이디입니다.').css('color', 'red');
                } else {
                    $('#idDiv').html('사용 가능한 아이디입니다.').css('color', 'blue');
                    $('#check').val(idValue);
                }
            },
            error: function(e) {
                console.log(e);
            }
        });
    }
});

// 비밀번호 검증
$('#password').focusout(function() {
    var pwdValue = $('#pwd').val();
    $('#pwdDiv').empty();
    
    if (!pwdRegex.test(pwdValue)) {
        $('#pwdDiv').html('비밀번호는 영문, 숫자, 특수문자 포함 8자 이상, 100자 이내로 입력하세요.').css('color', 'red');
    } else {
        $('#pwdDiv').html('사용 가능한 비밀번호입니다.').css('color', 'blue');
    }
});

//이메일
function change() {
	document.getElementById("email2").value = document.getElementById("email3").value;
}



// 회원가입
function checkJoin() {
    document.getElementById("nameDiv").innerHTML = "";
    document.getElementById("idDiv").innerHTML = "";
    document.getElementById("pwdDiv").innerHTML = "";
    document.getElementById("emailDiv").innerHTML = "";
    document.getElementById("telDiv").innerHTML = "";
    document.getElementById("addrDiv").innerHTML = "";

    var name = document.getElementById("name").value;
    var idValue = document.getElementById("id").value;
    var pwdValue = document.getElementById("password").value;
    var repwdValue = document.getElementById("repwd").value;
    var email1 = document.getElementsByName("email1")[0].value;
    var email2 = document.getElementById("email2").value;
    var tel2 = document.getElementsByName("tel2")[0].value;
    var tel3 = document.getElementsByName("tel3")[0].value;
    var zipcode = document.getElementById("zipcode").value;
    var addr1 = document.getElementById("addr1").value;
    var addr2 = document.getElementById("addr2").value;

    // 이름 확인
    if (name == "") {
        document.getElementById("nameDiv").innerHTML = "이름을 입력하세요.";
        document.getElementById("name").focus(); // Focus on name input
        return;
    }

    // 아이디 확인
    if (idValue == "") {
        document.getElementById("idDiv").innerHTML = "아이디를 입력하세요.";
        document.getElementById("id").focus(); // Focus on id input
        return;
    } else if (!idRegex.test(idValue)) {
        document.getElementById("idDiv").innerHTML = "아이디는 영문, 숫자 조합으로 50자 이내로 입력하세요.";
        document.getElementById("id").focus(); // Focus on id input
        return;
    }

    // 비밀번호 확인
    if (pwdValue == "") {
        document.getElementById("pwdDiv").innerHTML = "비밀번호를 입력하세요.";
        document.getElementById("password").focus(); // Focus on password input
        return;
    } else if (!pwdRegex.test(pwdValue)) {
        document.getElementById("pwdDiv").innerHTML = "비밀번호는 영문, 숫자, 특수문자 포함 8자 이상, 100자 이내로 입력하세요.";
        document.getElementById("password").focus(); // Focus on password input
        return;
    }

    // 비밀번호 재확인
    if (pwdValue != repwdValue) {
        document.getElementById("pwdDiv").innerHTML = "비밀번호가 맞지 않습니다.";
        return;
    }

    // 아이디 중복 체크 여부 확인
    if (idValue != document.getElementById("check").value) {
        document.getElementById("idDiv").innerHTML = "중복체크를 하세요.";
        document.getElementById("id").focus(); // Focus on id input
        return;
    }

    // 이메일 확인
    if (email1 == "" || email2 == "") {
        document.getElementById("emailDiv").innerHTML = "이메일을 입력하세요.";
        document.getElementsByName("email1")[0].focus(); // Focus on email1 input
        return;
    }

    // 전화번호 확인
    if (tel2 == "" || tel3 == "") {
        document.getElementById("telDiv").innerHTML = "휴대전화 번호를 정확히 입력하세요.";
        document.getElementsByName("tel2")[0].focus(); // Focus on tel2 input
        return;
    }

    // 주소 확인
    if (zipcode == "" || addr1 == "" || addr2 == "") {
        document.getElementById("addrDiv").innerHTML = "주소를 모두 입력하세요.";
        document.getElementById("zipcode").focus(); // Focus on zipcode input
        return;
    }

    // 모든 조건이 통과하면 서버로 데이터 전송
    $.ajax({
        type: 'post',
        url: '/Inbeomstagram/member/join.do',
        data: $('#joinForm').serialize(),
        success: function() {
            alert('회원가입을 축하합니다.');
            location.href = '/Inbeomstagram/index.jsp';
        },
        error: function(e) {
            console.log(e);
        }
    });
}



//우편번호 - Daum API
function checkPost() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipcode').value = data.zonecode;
            document.getElementById("addr1").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addr2").focus();
        }
    }).open();
}

