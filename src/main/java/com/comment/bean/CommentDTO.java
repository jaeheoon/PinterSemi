package com.comment.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
	private long seq_comment;
	private long seq_board; // 보드 시퀀스 세션으로 받아올예정
	private String name; // 작성자 이름 세션으로 받아올거임
	private String commentContent;
	private Date logtime;

}
