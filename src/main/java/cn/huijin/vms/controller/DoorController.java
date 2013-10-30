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
import cn.huijin.vms.model.Door;
import cn.huijin.vms.service.IControllerService;
import cn.huijin.vms.service.IDoorService;

/**
 * @author 武继明
 *  @since 2013年10月16日  下午4:38:39
 *
 */
@Controller
@RequestMapping("/door")
public class DoorController {
	@Inject
	private IDoorService doorService;
	@Inject
	private IControllerService controllerService;
	
	private final String LIST="door/list";
	private final String CONTROLLERLIST="door/controllerlist";
	private final String READERLIST="door/readerlist";
	
	@RequestMapping(value={"/getReaders"})
	public String getReader(Long controllerId,Model model){
		model.addAttribute(controllerService.getOne(controllerId).getReaders() );
		return READERLIST;
	}
	
	@RequestMapping(value={"/getControllers"})
	public String getController(Long doorId,Model model){
		model.addAttribute(controllerService.getControllersByDoorId(doorId));
		return CONTROLLERLIST;
	}
	
	@RequestMapping(value={"/doors","/",""},method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute(doorService.list() );
		return LIST;
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update( @Valid Door door,Errors errors,Model model){
		Map<String,Object> result=new HashMap<String, Object>();
		if(errors.hasErrors()){
			result.put("success", false);
			result.put("message","验证错误。");
			return result;
		}
			try {
				doorService.update(door);
				result.put("success", true);
			} catch (Exception e) {
				result.put("success", false);
				result.put("message",e.getMessage());
			}
			return result;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String,Object> add(@ModelAttribute Door door,Errors errors,Model model){
		Map<String,Object> result=new HashMap<String, Object>();
		if(errors.hasErrors()){
			result.put("success", false);
			result.put("message","验证错误。");
			return result;
		}
		try {
			doorService.add(door);
			result.put("success", true);
		} catch (ExistedException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String,Object> delete(@RequestBody Door door){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			doorService.delete(door.getId());
			result.put("success", true);
		} catch (ServiceException e) {
			result.put("success", false);
			result.put("message",e.getMessage());
		}
		return result;
		
	}
}
