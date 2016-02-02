package br.com.sisgns.infra;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.caelum.vraptor.events.VRaptorInitialized;
import br.com.sisgns.model.User;
import br.com.sisgns.security.Encrypted;
import br.com.sisgns.security.PlainText;

public class InitDatabase {
	private EntityManagerFactory factory;

	@Inject
	public InitDatabase(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public void inicializa(@Observes VRaptorInitialized evento) {

		EntityManager manager = null;

		try {
			manager = factory.createEntityManager();
			manager.getTransaction().begin();
			
			User user = new User("brunoferreirafabri@gmail.com", new Encrypted(new PlainText("1234567")).text());
			manager.persist(user);
			
			manager.getTransaction().commit();
		} finally {
			if (manager != null && manager.isOpen()) {
				manager.close();
			}
		}
	}
}
