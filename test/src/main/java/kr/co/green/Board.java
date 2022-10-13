package kr.co.green;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Board {

	private int id;
	private String title;
	private String content;
	private String reg_date;
	
	public Board() {};
	
	public Board(String title, String content) {
		this.title = title;
		this.content = content;
//		this.reg_date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	public Board(int id, String title, String content, String reg_date) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", content=" + content + ", reg_date=" + reg_date + "]";
	}
	
	
	
}