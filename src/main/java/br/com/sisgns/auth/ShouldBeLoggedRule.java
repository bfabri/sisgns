package br.com.sisgns.auth;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.brutauth.auth.annotations.GlobalRule;
import br.com.caelum.brutauth.auth.annotations.HandledBy;
import br.com.caelum.brutauth.auth.rules.CustomBrutauthRule;
import br.com.sisgns.component.UserInfo;

@RequestScoped 
@GlobalRule
@HandledBy(ShouldBeLoggedHandler.class)
public class ShouldBeLoggedRule implements CustomBrutauthRule {

    private final UserInfo userInfo;

    /**
     * @deprecated CDI eyes only
     */
    protected ShouldBeLoggedRule() {
    	this(null);
    }
    
    @Inject
    public ShouldBeLoggedRule(UserInfo userInfo) {
    	this.userInfo = userInfo;
    }
    
    public boolean isAllowed() {
        return userInfo.isLogged();
    }
}
