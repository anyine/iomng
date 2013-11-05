/**
 * 
 */
package sylarlove.advance.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sylarlove.advance.exception.ExistedException;
import sylarlove.advance.exception.ServiceException;
import sylarlove.advance.model.main.Organization;
import sylarlove.advance.service.IOrganizationService;

/**
 * 
 * @author 武继明
 *  @since 2013年10月22日  下午11:02:26
 *
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {
	final String LIST="organization/list";
	@Inject
	private IOrganizationService organizationService;
	
	@RequestMapping("/getRoot")
	@ResponseBody
	public Organization getRoot(){
		return organizationService.getRoot() ;
	}
	@RequestMapping("/getOrganization")
	public String getOrganization(Long parentId,Model model){
		model.addAttribute(organizationService.getByParentId(parentId));
		return LIST;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update( @Valid Organization organization,Errors errors){
		System.out.println(organization.getId()+"=="+organization.getIndex()+organization.getName());
		Map<String,Object> result=new HashMap<String, Object>();
		if(errors.hasErrors()){
			result.put("success", false);
			result.put("message","验证错误。");
			return result;
		}
			try {
				organizationService.update(organization);
				result.put("success", true);
			} catch (Exception e) {
				result.put("success", false);
				result.put("message",e.getMessage());
			}
			return result;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String,Object> add(@ModelAttribute @Valid Organization organization,Errors errors){
		Map<String,Object> result=new HashMap<String, Object>();
		if(errors.hasErrors()){
			result.put("success", false);
			result.put("message","验证错误。");
			return result;
		}
		try {
			organizationService.add(organization);
			result.put("success", true);
		} catch (ExistedException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String,Object> delete(Long id){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			organizationService.delete(id);
			result.put("success", true);
		} catch (ServiceException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
	}
}
