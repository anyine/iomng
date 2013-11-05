/**
 * 
 */
package sylarlove.advance.controller;


import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sylarlove.advance.service.IUserService;

/**
 * @author 武继明
 *  @since 2013年10月12日  上午10:42:07
 *
 */
@Controller
public class IndexController {
	@Inject
	private IUserService userService;
	@RequestMapping(value={"/index","/"})
	public String index(){
		return "index";
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(@ModelAttribute LoginCommand loginCommand){
		return "login";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@ModelAttribute @Valid LoginCommand loginCommand,Errors errors){
		if(errors.hasErrors()){
			return "login";
		}
		try {
			userService.login(loginCommand.getUsername(), loginCommand.getPassword(), loginCommand.getRememberMe());
		} catch (Exception e) {
			return "login";
		}
		return "redirect:/index";
	}
}
