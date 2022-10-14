package kr.co.green;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.RequestBody;

public class Board {

	private int id;
	private String title;
	private String content;
	private String reg_date;
	private String mod_date;
	
	public Board() { //reg_date를 걍 현재시간으로 씀
		this.reg_date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		this.mod_date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	//@RequestBody Board board는 이거 못찾아오고 걍 빈생성자로 감!!
//	public Board(String title, String content) {
//		super();
//		this.title = title;
//		this.content = content;
//		this.reg_date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//	}
	
	public Board(int id, String title, String content, String reg_date, String mod_date) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
		this.mod_date = mod_date;
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
	
	public String getMod_date() {
		return mod_date;
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

	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", content=" + content + ", reg_date=" + reg_date
				+ ", mod_date=" + mod_date + "]";
	}
	

}