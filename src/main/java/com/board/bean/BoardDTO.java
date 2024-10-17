package com.board.bean;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {
	private long seq_board;
	private long seq_member;
	private String imageFileName; // UUID
	private String imageOriginalFileName;
	private String name;
	private String imageSubject;
	private String imageContent;
	private int hit;
	private Date logtime;
}
