package main.java;

public class Post {
	private String date;
	private String hour;
	private String content;
	
	Post(String date, String hour, String content) {
		this.date = date;
		this.hour = hour;
		this.content = content;
	}

	String getDate() {
		return this.date;
	}

	String getHour() {
		return this.hour;
	}

	String getContent() {
		return this.content;
	}
}
