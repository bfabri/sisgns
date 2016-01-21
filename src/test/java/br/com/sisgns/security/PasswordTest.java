package br.com.sisgns.security;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PasswordTest {

	@Test
	public void testEncryptedPasswordLength() {
		int encryptedPasswordLength = 88;
		String encryptedPassword = new Encrypted(new PlainText("123456")).text();
		assertThat(encryptedPassword.length(), equalTo(encryptedPasswordLength));
	}
	
	@Test
	public void testDifferentEncryptedPasswords() {
		String firstPassword = new Encrypted(new PlainText("123456")).text();
		String secondPassword = new Encrypted(new PlainText("123")).text();
		assertThat(firstPassword, not(equalTo(secondPassword)));
	}
	
	@Test
	public void testSameEncryptedPasswords() {
		String firstPassword = new Encrypted(new PlainText("123")).text();
		String secondPassword = new Encrypted(new PlainText("123")).text();
		assertThat(firstPassword, equalTo(secondPassword));
	}
	
	@Test
	public void testPlainTextPasswordLength() {
		String password = "123";
		String plainTextPassword = new PlainText(password).text();
		assertThat(plainTextPassword.length(), equalTo(password.length()));
	}
	
	@Test
	public void testDifferentPlainTextPasswords() {
		String firstPassword = new PlainText("123456").text();
		String secondPassword = new PlainText("123").text();
		assertThat(firstPassword, not(equalTo(secondPassword)));
	}
	
	@Test
	public void testSamePlainTextPasswords() {
		String firstPassword = new PlainText("123456").text();
		String secondPassword = new PlainText("123456").text();
		assertThat(firstPassword, equalTo(secondPassword));
	}
	
}
