package br.com.sisgns.dao;

import br.com.sisgns.model.User;

public interface UserDao {
	User find(String email, String password);
}
