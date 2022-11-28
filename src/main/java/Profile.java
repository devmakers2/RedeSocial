package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Profile {
	private String name;
	private String username;
	private String password;
	private List<Post> posts = new ArrayList<>();

	Profile(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	void addAPost() {		
		System.out.println("por favos informe os dados do post:");
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("data (exemplo: “22/11/2022”): ");
		String date = scanner.nextLine();
		
		System.out.print("hora (exemplo: “22:38”): ");
		String hour = scanner.nextLine();
		
		System.out.print("conteúdo: ");
		String content = scanner.nextLine();
		
		this.posts.add(new Post(date, hour, content));
	}
	
	void printPosts() {
		for (Post post : this.posts) {
			System.out.println(post.getDate() + " às " + post.getHour() + " - \"" + post.getContent() + "\"");
		}
	}
	
	boolean isPasswordEqualTo(String password) {
		return this.password.equals(password);
	}
	
	String getName() {
		return this.name;
	}
	
	String getUsername() {
		return this.username;
	}
	
	List<Post> getPosts() {
		return this.posts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		return Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
}
