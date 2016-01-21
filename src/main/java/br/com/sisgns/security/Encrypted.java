package br.com.sisgns.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encrypted implements Password {

	private static final String ALGORITHM = "SHA-512";
	private static final String ENCODING = "UTF-8";
	
	private Password password;
	
	public Encrypted(Password password) {
		this.password = password;
	}
	
	@Override
	public String text() {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(ALGORITHM);
			byte[] encryptedPassword = digest.digest(this.password.text().getBytes(ENCODING));
			return Base64.getEncoder().encodeToString(encryptedPassword);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new IllegalStateException();
		}
	}
}
