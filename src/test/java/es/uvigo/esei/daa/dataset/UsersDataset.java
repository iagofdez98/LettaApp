package es.uvigo.esei.daa.dataset;

import java.util.Arrays;
import java.util.Base64;

import es.uvigo.esei.daa.entities.User;

public final class UsersDataset {
	private UsersDataset() {}

	public static User[] users() {
		return new User[] {
			new User(login(), "713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca", "normal")
		};
	}
	
	public static User user(String login) {
		return Arrays.stream(users())
			.filter(user -> user.getLogin().equals(login))
			.findAny()
		.orElseThrow(IllegalArgumentException::new);
	}
	
	public static String login() {
		return "paco";
	}
	
	
	public static String userToken(String login) {
		final String chain = login + ":" + "admin" + "pass";
		
		return Base64.getEncoder().encodeToString(chain.getBytes());
	}
}
