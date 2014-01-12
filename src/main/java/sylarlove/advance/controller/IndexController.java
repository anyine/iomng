/**
 * 
 */
package sylarlove.advance.controller;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sylarlove.advance.exception.ServiceException;
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
	
	@RequestMapping(value="/changePassword",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> changePwd(String oldPassword,String newPassword){
		Map<String,Object> map=new HashMap<String,Object>();
		try{
		userService.changePassword(oldPassword,newPassword);
		}catch(ServiceException e){
			map.put("success", false);
			map.put("msg", e.getMessage());
			return map;
		}
		map.put("success",true);
		map.put("msg", "修改成功。");
		return map;
	}
	
	@RequestMapping(value={"/index","/"})
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/unauthorized")
	public String unauthorized(){
		return "unauthorized";
	}
	@RequiresUser
	@RequestMapping(value="/logout")
	public String logout(){
		userService.logout();
		return "index";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(@ModelAttribute LoginCommand loginCommand){
		return "login";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute @Valid LoginCommand loginCommand,Errors errors){
		ModelAndView mv=new ModelAndView("login");
		if(errors.hasErrors()){
			return mv;
		}
		try {
			userService.login(loginCommand.getUsername(), loginCommand.getPassword(), loginCommand.getRememberMe());
		} catch (AuthenticationException e) {
			mv.addObject("success", false);
			mv.addObject("msg","用户名或密码错误。");
			return mv;
		}
		mv.setViewName("redirect:/index");
		return mv;
	}
}
