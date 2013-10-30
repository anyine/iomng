/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import cn.huijin.vms.model.InnerCarRecord;
import cn.huijin.vms.model.Result;

/**
 * 出入记录服务接口
 * @author 武继明
 *  @since 2013年10月25日  上午9:00:50
 *
 */
public interface IRecordService {
	/**
	 * @param controllerSN 控制器sn
	 * @param readerNumber 读卡器编号
	 * @param number 卡号
	 * @return
	 */
	public Result add(String controllerSn,String readerNumber,String number);
	
	public List<InnerCarRecord>  listInnerCarRecord();
	
}
