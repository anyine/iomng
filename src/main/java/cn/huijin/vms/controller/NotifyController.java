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
import cn.huijin.vms.model.Notify;
import cn.huijin.vms.service.INotifyService;

/**
 * 
 * @author 武继明
 * @since 2013年11月17日 下午9:15:05
 * 
 */
@Controller
@RequestMapping("/notify")
public class NotifyController {
	@Inject
	private INotifyService notifyService;

	private final String LIST = "notify/list";

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(@RequestBody @Valid Notify notify,
			Errors errors) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (errors.hasErrors()) {
			result.put("success", false);
			result.put("message", "验证错误。");
			return result;
		}
		try {
			notifyService.update(notify);
			result.put("success", true);
		} catch (ExistedException e) {
			result.put("success", false);
			result.put("message", e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestBody Notify notify) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			notifyService.delete(notify.getId());
			result.put("success", true);
		} catch (ServiceException e) {
			result.put("success", false);
			result.put("message", e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = { "/notifys", "/", "" }, method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("notifyList", notifyService.list());
		return LIST;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(@RequestBody Notify notify, Errors errors) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (errors.hasErrors()) {
			result.put("success", false);
			result.put("message", "验证错误。");
			return result;
		}
		try {
			notifyService.add(notify);
			result.put("success", true);
		} catch (ExistedException e) {
			result.put("success", false);
			result.put("message", e.getMessage());
		}
		return result;
	}

}
