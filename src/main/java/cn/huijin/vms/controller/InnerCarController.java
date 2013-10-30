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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sylarlove.advance.exception.ExistedException;
import sylarlove.advance.exception.ServiceException;
import cn.huijin.vms.model.InnerCar;
import cn.huijin.vms.service.IInnerCarService;

/**
 * 
 * @author 武继明
 *  @since 2013年10月30日  下午2:46:21
 *
 */
@Controller
@RequestMapping("/innerCar")
public class InnerCarController {
	@Inject
	private IInnerCarService innerCarService;
	
	private final String LIST="innerCar/list";
	
	@RequestMapping(value={"/innerCars","/",""},method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute(innerCarService.list());
		return LIST;
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update( @Valid InnerCar innerCar,Errors errors){
		Map<String,Object> result=new HashMap<String, Object>();
		if(errors.hasErrors()){
			result.put("success", false);
			result.put("message","验证错误。");
			return result;
		}
			try {
				innerCarService.update(innerCar);
				result.put("success", true);
			} catch (Exception e) {
				result.put("success", false);
				result.put("message",e.getMessage());
			}
			return result;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String,Object> add(@ModelAttribute InnerCar innerCar,Errors errors){
		Map<String,Object> result=new HashMap<String, Object>();
		if(errors.hasErrors()){
			result.put("success", false);
			result.put("message","验证错误。");
			return result;
		}
		try {
			innerCarService.add(innerCar);
			result.put("success", true);
		} catch (ExistedException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String,Object> delete(@RequestBody InnerCar innerCar){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			innerCarService.delete(innerCar.getId());
			result.put("success", true);
		} catch (ServiceException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
	}
	
}
