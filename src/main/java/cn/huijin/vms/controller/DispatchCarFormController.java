/**
 * 
 */
package cn.huijin.vms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sylarlove.advance.exception.ExistedException;
import cn.huijin.vms.model.DispatchCarForm;
import cn.huijin.vms.service.IDispatchCarFormService;

/**
 * 
 * @author 武继明
 *  @since 2013年11月12日  下午4:19:42
 *
 */
@Controller
@RequestMapping("/dispatchCarForm")
public class DispatchCarFormController {
	@Inject
	private IDispatchCarFormService dispatchCarFormService;
	
	private final String LIST="dispatchCarForm/list";
	
	@RequestMapping(value={"/dispatchCarForms","/",""},method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("dispatchCarFormList",dispatchCarFormService.list());
		return LIST;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> add(@RequestBody DispatchCarForm dispatchCarForm,Errors errors){
		Map<String,Object> result=new HashMap<String, Object>();
		if(errors.hasErrors()){
			result.put("success", false);
			result.put("message","验证错误。");
			return result;
		}
		try {
			dispatchCarFormService.add(dispatchCarForm);
			result.put("success", true);
		} catch (ExistedException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
	}
	
}
