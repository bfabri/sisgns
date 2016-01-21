package br.com.sisgns.security;

public class PlainText implements Password {
	
	private String password;
	
	public PlainText(String password) {
		this.password = password;
	}
	
	@Override
	public String text() {
		return password;
	}

}
