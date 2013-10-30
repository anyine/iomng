/**
 * 
 */
package cn.huijin.vms.controller;

import java.util.HashMap;
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
	
	private final String LIST="carType/list";
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update(@RequestBody @Valid CarType carType,Errors errors){
		Map<String,Object> result=new HashMap<String, Object>();
		if(errors.hasErrors()){
			result.put("success", false);
			result.put("message","验证错误。");
			return result;
		}
			try {
				carTypeService.update(carType);
				result.put("success", true);
			} catch (ExistedException e) {
				result.put("success", false);
				result.put("message",e.getMessage());
			}
			return result;
	}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delete(@RequestBody CarType carType){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			carTypeService.delete(carType.getId());
			result.put("success", true);
		} catch (ServiceException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value={"/carTypes","/",""},method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("carTypeList",carTypeService.list());
		return LIST;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> add(@RequestBody CarType carType,Errors errors){
		Map<String,Object> result=new HashMap<String, Object>();
		if(errors.hasErrors()){
			result.put("success", false);
			result.put("message","验证错误。");
			return result;
		}
		try {
			carTypeService.add(carType);
			result.put("success", true);
		} catch (ExistedException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
	}
	
}
