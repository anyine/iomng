/**
 * 
 */
package cn.huijin.vms.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.huijin.vms.model.Car;
import cn.huijin.vms.service.CarService;

/**
 * @author 武继明
 *  @since 2013年10月12日  上午9:50:18
 *
 */
@Controller
@RequestMapping("/car")
public class CarController {
	@Inject
	private CarService carService;
	
	private final String ADD="car/add";
	private final String LIST="car/list";
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(@ModelAttribute Car car){
		return ADD;
	}
	
	@RequestMapping(value="/cars",method=RequestMethod.POST)
	public String add(@ModelAttribute Car car,Errors errors){
		if(errors.hasErrors()){
			return ADD;
		}
		carService.add(car);
		return "redirect:/"+LIST;
	}
	
}
