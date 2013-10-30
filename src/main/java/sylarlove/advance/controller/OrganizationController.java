/**
 * 
 */
package sylarlove.advance.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
