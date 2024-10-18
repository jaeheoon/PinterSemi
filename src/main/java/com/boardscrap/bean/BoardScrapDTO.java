package com.boardscrap.bean;

import java.util.Date;

import lombok.Data;

@Data
public class BoardScrapDTO {
	private long seq_board;
	private long seq_member;
	private String imageFileName; // UUID
	private String imageOriginalFileName;
	private String name;
	private String imageSubject;
	private Date logtime;
}