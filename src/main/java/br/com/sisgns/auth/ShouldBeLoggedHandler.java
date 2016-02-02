package br.com.sisgns.auth;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.brutauth.auth.handlers.RuleHandler;
import br.com.caelum.vraptor.Result;
import br.com.sisgns.controller.HomeController;

@RequestScoped
public class ShouldBeLoggedHandler implements RuleHandler {
    
	private final Result result;

    /**
     * @deprecated CDI eyes only
     */
    protected ShouldBeLoggedHandler() {
        this(null);
    }

    @Inject
    public ShouldBeLoggedHandler(Result result) {
        this.result = result;
    }

    @Override
    public void handle() {
        result.redirectTo(HomeController.class).index();
    }
}