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
import sylarlove.advance.exception.ServiceException;
import cn.huijin.vms.model.CarType;
import cn.huijin.vms.service.ICarTypeService;

/**
 * 
 * @author 武继明
 *  @since 2013年10月12日  下午1:30:41
 *
 */
@Controller
@RequestMapping("/carType")
public class CarTypeController {
	@Inject
	private ICarTypeService carTypeService;
	
	private final String ADD="carType/add";
	private final String LIST="carType/list";
	private final String SHOW="carType/show";
	private final String UPDATE="carType/update";
	private final String ERROR="carType/error";
	

	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable Long id,Model model){
		model.addAttribute(carTypeService.getOne(id));
		return UPDATE;
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@Valid CarType carType,Errors errors,Model model){
		if(errors.hasErrors()){
			return UPDATE;
		}
			try {
				carTypeService.update(carType);
			} catch (ExistedException e) {
				model.addAttribute("errorMsg", e.getMessage());
				return UPDATE;
			}
		return "redirect:/carType/";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable Long id,Model model){
		model.addAttribute(carTypeService.getOne(id));
		return SHOW;
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable Long id,Model model){
		try {
			carTypeService.delete(id);
		} catch (ServiceException e) {
			model.addAttribute("errorMsg", e.getMessage());
			return ERROR;
		}
		return "redirect:/carType/";
	}
	
	
	@RequestMapping(value={"/carTypes","/",""},method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("carTypeList",carTypeService.list());
		return LIST;
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(@ModelAttribute CarType carType){
		return ADD;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute CarType carType,Errors errors,Model model){
		if(errors.hasErrors()){
			return ADD;
		}
		try {
			carTypeService.add(carType);
		} catch (ExistedException e) {
			model.addAttribute("errorMsg", e.getMessage());
			return ADD;
		}
		return "redirect:/carType/";
	}
	
}
