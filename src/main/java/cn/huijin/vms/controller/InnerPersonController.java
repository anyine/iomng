/**
 * 
 */
package cn.huijin.vms.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sylarlove.advance.exception.ExistedException;
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
	
	private final String ADD="innerPerson/add";
	private final String LIST="innerPerson/list";
	private final String SHOW="innerPerson/show";
	private final String UPDATE="innerPerson/update";
	
	@RequestMapping(value="/changeStatus/{id}",method=RequestMethod.GET)
	public String changeStatus(@PathVariable Long id){
		innerPersonService.changeStatus(id);
		return "redirect:/innerCar/";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable Long id,Model model){
		model.addAttribute(innerPersonService.getOne(id));
		return UPDATE;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@Valid InnerPerson innerPerson,Errors errors,Model model){
		if(errors.hasErrors()){
			return this.update(innerPerson.getId(), model);
		}
			try {
				innerPersonService.update(innerPerson);
			} catch (ExistedException e) {
				model.addAttribute("errorMsg", e.getMessage());
				return this.update(innerPerson.getId(), model);
			}
		return "redirect:/innerPerson/";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable Long id,Model model){
		model.addAttribute(innerPersonService.getOne(id));
		return SHOW;
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable Long id){
		innerPersonService.delete(id);
		return "redirect:/innerCar/";
	}
	
	@RequestMapping(value={"/innerCars","/",""},method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("innerPersonList",innerPersonService.list());
		return LIST;
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(@ModelAttribute InnerPerson innerperson,Model model){
		return ADD;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Valid InnerPerson innerPerson,Errors errors,Model model){
		if(errors.hasErrors()){
			return this. add(innerPerson, model);
		}
		
		try {
			innerPersonService.add(innerPerson);
		} catch (ExistedException e) {
			model.addAttribute("errorMsg", e.getMessage());
			return this.add(innerPerson, model);
		}
		return "redirect:/innerPerson/";
	}
	
}
