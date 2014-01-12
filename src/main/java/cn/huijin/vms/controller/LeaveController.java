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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sylarlove.advance.exception.ExistedException;
import cn.huijin.vms.model.Leave;
import cn.huijin.vms.service.ILeaveService;

/**
 * 
 * @author 武继明
 *  @since 2013年11月13日  下午9:18:02
 *
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {
	@Inject
	private ILeaveService leaveService;
	
	private final String LIST="leave/list";
	
	@RequestMapping(value={"/leaves","/",""},method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("leaveList",leaveService.list());
		return LIST;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> add(Leave leave,Errors errors){
		Map<String,Object> result=new HashMap<String, Object>();
		if(errors.hasErrors()){
			result.put("success", false);
			result.put("message","验证错误。");
			return result;
		}
		try {
			leaveService.add(leave);
			result.put("success", true);
		} catch (ExistedException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
	}
	@RequestMapping(value="/changeAgree",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> changeAgree(Long id){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			leaveService.changeAgree(id);
			result.put("success", true);
		} catch (ExistedException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
	}
	
}
