/**
 * 
 */
package cn.huijin.vms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sylarlove.advance.exception.ExistedException;
import sylarlove.advance.exception.ServiceException;
import sylarlove.advance.model.main.User;
import sylarlove.advance.service.IUserService;
import cn.huijin.vms.model.InnerPerson;
import cn.huijin.vms.service.IInnerPersonService;

/**
 * 
 * @author 武继明
 *  @since 2013年10月22日  下午3:39:29
 *
 */
@Controller
@RequestMapping("/innerPerson")
public class InnerPersonController {
	@Inject
	private IInnerPersonService innerPersonService;
	@Inject
	private IUserService userService;
	
	private final String LIST="innerPerson/list";
	
	@RequestMapping(value="/changeStatus/{id}",method=RequestMethod.GET)
	public String changeStatus(@PathVariable Long id){
		innerPersonService.changeStatus(id);
		return "redirect:/innerCar/";
	}
	
	@RequestMapping(value={"/innerPersons","/",""},method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute(innerPersonService.list());
		return LIST;
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update( @Valid InnerPerson innerPerson,Errors errors,String userIds){
		Map<String,Object> result=new HashMap<String, Object>();
		if(errors.hasErrors()){
			result.put("success", false);
			result.put("message","验证错误。");
			return result;
		}
			try {
				if(StringUtils.isNotBlank(userIds)){
					for(String userId: userIds.split(",")){
						User user=userService.getOne(Long.valueOf(userId));
						if(user!=null){
							innerPerson.getUsers().add(user);
						}
					}
				}
				innerPersonService.update(innerPerson);
				result.put("success", true);
			} catch (Exception e) {
				result.put("success", false);
				result.put("message",e.getMessage());
			}
			return result;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String,Object> add(@ModelAttribute @Valid InnerPerson innerPerson,Errors errors,String userIds){
		Map<String,Object> result=new HashMap<String, Object>();
		if(errors.hasErrors()){
			result.put("success", false);
			result.put("message","验证错误。");
			return result;
		}
		try {
			if(StringUtils.isNotBlank(userIds)){
				for(String userId: userIds.split(",")){
					User user=userService.getOne(Long.valueOf(userId));
					if(user!=null){
						innerPerson.getUsers().add(user);
					}
				}
			}
			innerPersonService.add(innerPerson);
			result.put("success", true);
		} catch (ExistedException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String,Object> delete(@RequestBody InnerPerson innerPerson){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			innerPersonService.delete(innerPerson.getId());
			result.put("success", true);
		} catch (ServiceException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
	}

}
