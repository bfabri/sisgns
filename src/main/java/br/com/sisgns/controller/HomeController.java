package br.com.sisgns.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.brutauth.auth.annotations.Public;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.sisgns.component.UserInfo;
import br.com.sisgns.dao.UserDao;
import br.com.sisgns.model.User;

@Controller
public class HomeController {
	
	private UserInfo userInfo;
	private UserDao userDao;
	private Result result;
	private Validator validator;
	
	/**
     * @deprecated CDI eyes only
     */
	protected HomeController() {
		this(null, null, null, null);
	}
	
	@Inject
	public HomeController(UserInfo userInfo, UserDao userDao, Result result, Validator validator) {
		this.userInfo = userInfo;
		this.userDao = userDao;
		this.result = result;
		this.validator = validator;
	}
	
	
	@Public @Get @Path("/")
	public void index() {}
	
	@Get
	public void home() {}
	
	@Public @Post
	public void login(@Valid User user) {
		result.include("user", user);
		validator.onErrorRedirectTo(this).index();
		
		final User currentUser = userDao.find(user.getEmail(), user.getPassword());
		validator.addIf(currentUser == null, new I18nMessage("user", "user.not.exists"));
		validator.onErrorRedirectTo(this).index();
		
		userInfo.login(currentUser);
		result.redirectTo(this).home();
	}
	
}
