package com.example.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commit {
	private int id;
	private String content;
	private LocalDate regDate;
	private int writer;
	private int articleId;
	private String writerName;
}
