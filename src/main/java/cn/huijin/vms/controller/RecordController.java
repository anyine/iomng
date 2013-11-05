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
public class RecordController {
	@Inject
	private IRecordService recordService;
	
	private final String INNERCARRECORDLIST="innerCarRecord/list";
	private final String INNERPERSONRECORDLIST="innerPersonRecord/list";
	
	@RequestMapping(value={"/innerCarRecords"},method=RequestMethod.GET)
	public String listInnerCarRecords(Model model){
		model.addAttribute(recordService.listInnerCarRecord() );
		return INNERCARRECORDLIST;
	}
	@RequestMapping(value={"/innerPersonRecords"},method=RequestMethod.GET)
	public String listInnerPersonRecords(Model model){
		model.addAttribute(recordService.listInnerPersonRecord() );
		return INNERPERSONRECORDLIST;
	}
}
