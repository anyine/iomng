/**
 * 
 */
package cn.huijin.vms.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.huijin.vms.service.IRecordService;

/**
 * 
 * @author 武继明
 *  @since 2013年10月30日  下午3:06:10
 *
 */
@Controller
@RequestMapping("/record")
public class InnerCarRecordController {
	@Inject
	private IRecordService recordService;
	
	private final String INNERCARRECORDLIST="innerCarRecord/list";
	
	@RequestMapping(value={"/innerCarRecords"},method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute(recordService.listInnerCarRecord() );
		return INNERCARRECORDLIST;
	}
}
