/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import cn.huijin.vms.model.Controller;


/**
 * 
 * @author 武继明
 *  @since 2013年10月29日  下午8:46:24
 *
 */
public interface IControllerService {
	List<Controller> getControllersByDoorId(Long doorId);
	Controller getOne(Long id);
	/**
	 * 列出所有控制器
	 * @return
	 */
	List<Controller> list();
}
