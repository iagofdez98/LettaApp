package es.uvigo.esei.daa.entities;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class UserUnitTest {
	@Test
	public void testUserStringStringString() {
		final String login = "jose";
		final String password = "713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca";
		final String role = "normal";
		
		final User user = new User(login, password, role);
		
		assertThat(user.getLogin(), is(equalTo(login)));
		assertThat(user.getPassword(), is(equalTo(password)));
		assertThat(user.getRole(), is(equalTo(role)));
	}
	
	@Test(expected = NullPointerException.class)
	public void testUserNullLoginStringString() {
		new User(null, "713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca", "normal");
	}

	@Test(expected = NullPointerException.class)
	public void testUserStringNullPasswordString() {
		new User("jose", null, "normal");
	}
	
	@Test(expected = NullPointerException.class)
	public void testUserStringStringNullRole() {
		new User("jose", "713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca", null);
	}

	@Test
	public void testSetLogin() {
		final String login = "jose";
		final String password = "713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca";
		final String role = "normal";
				
		
		final User user = new User(login, password, role);
		
		user.setLogin("juan");
		assertThat(user.getLogin(), is(equalTo("juan")));
		assertThat(user.getPassword(), is(equalTo(password)));
		assertThat(user.getRole(), is(equalTo(role)));
	}
	
	
	@Test(expected = NullPointerException.class)
	public void testSetNullLogin() {
		final User user = new User("jose", "713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca", "normal");
		user.setLogin(null);
	}

	@Test(expected = NullPointerException.class)
	public void testSetNullPassword() {
		final User user = new User("jose", "713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca", "normal");
		user.setPassword(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSetNullRole() {
		final User user = new User("jose", "713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca", "normal");
		user.setRole(null);
	}
	
	

	@Test
	public void testSetPassword() {
		final String login = "jose";
		final String password = "713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca";
		final String role = "normal";
				
		
		final User user = new User(login, password, role);
		
		user.setPassword("713bfda78870bf9d1b261f565286f85e97ee614efe5f0fvf7c34e7ca4f65baca");
		assertThat(user.getLogin(), is(equalTo(login)));
		assertThat(user.getPassword(), is(equalTo("713bfda78870bf9d1b261f565286f85e97ee614efe5f0fvf7c34e7ca4f65baca")));
		assertThat(user.getRole(), is(equalTo(role)));
	}
	
	@Test
	public void testSetRole() {
		final String login = "jose";
		final String password = "713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca";
		final String role = "normal";
				
		
		final User user = new User(login, password, role);
		
		user.setRole("admin");
		assertThat(user.getLogin(), is(equalTo(login)));
		assertThat(user.getPassword(), is(equalTo(password)));
		assertThat(user.getRole(), is(equalTo("admin")));
	}

}
