/**
 * 
 */
package cn.huijin.vms.controller;

import java.util.Date;

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
import cn.huijin.vms.model.Car;
import cn.huijin.vms.service.ICarService;
import cn.huijin.vms.service.ICarTypeService;

/**
 * @author 武继明
 *  @since 2013年10月12日  上午9:50:18
 *
 */
@Controller
@RequestMapping("/car")
public class CarController {
	@Inject
	private ICarService carService;
	@Inject
	private ICarTypeService carTypeService;
	
	private final String ADD="car/add";
	private final String LIST="car/list";
	private final String SHOW="car/show";
	private final String UPDATE="car/update";
	
	@RequestMapping(value="/changeStatus/{id}",method=RequestMethod.GET)
	public String changeStatus(@PathVariable Long id){
		carService.changeStatus(id);
		return "redirect:/car/";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable Long id,Model model){
		model.addAttribute("carTypeList",carTypeService.list());
		model.addAttribute(carService.getOne(id));
		return UPDATE;
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@Valid Car car,Errors errors,Model model){
		if(errors.hasErrors()){
			return this.update(car.getId(), model);
		}
			try {
				carService.update(car);
			} catch (ExistedException e) {
				model.addAttribute("errorMsg", e.getMessage());
				return this.update(car.getId(), model);
			}
		return "redirect:/car/";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable Long id,Model model){
		model.addAttribute(carService.getOne(id));
		return SHOW;
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable Long id){
		carService.delete(id);
		return "redirect:/car/";
	}
	
	@RequestMapping(value={"/cars","/",""},method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("carList",carService.list());
		return LIST;
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(@ModelAttribute Car car,Model model){
		model.addAttribute("carTypeList",carTypeService.list());
		return ADD;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Valid Car car,Errors errors,Model model){
		if(errors.hasErrors()){
			return this. add(car, model);
		}
		car.setCreateTime(new Date());
		try {
			carService.add(car);
		} catch (ExistedException e) {
			model.addAttribute("errorMsg", e.getMessage());
			return this.add(car, model);
		}
		return "redirect:/car/";
	}
	
}
