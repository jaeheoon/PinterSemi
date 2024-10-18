package com.member.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	private Long seq_member;
	private String name;
	private String id;
	private String password;
	private String gender;
	private String email; 		 // email1 + email2
	private String phoneNumber;  // tel1+tel2+tel3
	private String zipcode;
	private String address; 	 // add1 + add2
	private String admin;
	private Date logtime;
	private String userProfile; // 사용자 프로필사진 값
	private String userOriginalProfile; // 사용자 프로필사진 이름
	private String kakaoProfile; 		// 사용자 카카오 프로필사진
	private String kakaoCheck; 			// 카카오 여부
	
	@Override
	public String toString() {
		return "이름 : " + name
			 + " 아이디 : " + id
			 + " 비밀번호 : " + password
			 + " 성별 : " + gender
			 + " 이메일 : " + email
			 + " 핸드폰 : " + phoneNumber
			 + " 코드 : " + zipcode
			 + " 주소 : " + address;
	}
}
