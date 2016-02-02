package br.com.sisgns.component;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.sisgns.model.User;

@SessionScoped
@Named
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1269930684928101980L;
	
	private User user;
	
	public void login(User user) {
		this.user = user;
	}
	
	public void logout() {
		this.user = null;
	}
	
	public Boolean isLogged() {
		return this.user != null;
	}
	
	public User getUser() {
		return this.user;
	}
}
