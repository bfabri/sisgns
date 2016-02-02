package br.com.sisgns.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.sisgns.model.User;
import br.com.sisgns.model.User_;
import br.com.sisgns.security.Encrypted;
import br.com.sisgns.security.PlainText;

@RequestScoped
public final class JpaUserDao implements UserDao {

	private EntityManager em;
	
	/**
     * @deprecated CDI eyes only
     */
	protected JpaUserDao() {
		this(null);
	}
	
	@Inject
	public JpaUserDao(EntityManager em) {
		this.em = em;
	}

	@Override
	public User find(String email, String password) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> c = cb.createQuery(User.class);
		
		Root<User> user = c.from(User.class);
		c.select(user).where(cb.and(cb.equal(user.get(User_.email), email), cb.equal(user.get(User_.password),  new Encrypted(new PlainText(password)).text())));
		
		TypedQuery<User> query = em.createQuery(c);
		
		List<User> users = query.getResultList();
		return users.isEmpty() ? null : users.iterator().next();
	}
	
}
