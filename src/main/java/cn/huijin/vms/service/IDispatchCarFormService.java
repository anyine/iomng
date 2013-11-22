/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import cn.huijin.vms.model.DispatchCarForm;

/**
 * 
 * @author 武继明
 *  @since 2013年11月12日  下午4:02:49
 *
 */
public interface IDispatchCarFormService {
	/**
	 * 新增派车单
	 * @param dispatchCarForm
	 */
	void add(DispatchCarForm dispatchCarForm);
	List<DispatchCarForm> list();
}
