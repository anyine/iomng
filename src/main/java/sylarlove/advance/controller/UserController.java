/**
 * 
 */
package sylarlove.advance.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sylarlove.advance.exception.ExistedException;
import sylarlove.advance.exception.ServiceException;
import sylarlove.advance.model.main.User;
import sylarlove.advance.service.IUserService;

/**
 * 
 * @author 武继明
 *  @since 2013年10月29日  下午3:23:15
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Inject
	private IUserService userService;
	
	private final String LIST="user/list";
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update( @Valid User user,Errors errors){
		Map<String,Object> result=new HashMap<String, Object>();
		if(errors.hasErrors()){
			result.put("success", false);
			result.put("message","验证错误。");
			return result;
		}
			try {
				userService.update(user);
				result.put("success", true);
			} catch (ExistedException e) {
				result.put("success", false);
				result.put("message",e.getMessage());
			}
			return result;
	}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delete(@RequestBody User user){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			userService.delete(user.getId());
			result.put("success", true);
		} catch (ServiceException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value={"/users","/",""},method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("userList",userService.list());
		return LIST;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> add(@Valid User user,Errors errors){
		Map<String,Object> result=new HashMap<String, Object>();
		if(errors.hasErrors()){
			result.put("success", false);
			result.put("message","验证错误。");
			return result;
		}
		try {
			userService.add(user);
			result.put("success", true);
		} catch (ExistedException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
	}
	@RequestMapping(value="/permission",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> permission(Long id,String menuId){
		Map<String,Object> result=new HashMap<String, Object>();
		List<Long> permissionIds=new ArrayList<Long>();
		for(String s:menuId.split(",")){
			permissionIds.add(Long.valueOf(s));
		}
		try {
			userService.addPermission(id,permissionIds);
			result.put("success", true);
		} catch (ExistedException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
	}
}
