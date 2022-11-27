package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	
	String getName() {
		return this.name;
	}
	
	String getUsername() {
		return this.username;
	}
	
	String getPassword() {
		return this.password;
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
