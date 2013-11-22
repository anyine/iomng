/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import sylarlove.advance.exception.ExistedException;
import sylarlove.advance.exception.ServiceException;
import cn.huijin.vms.model.Notify;

/**
 * 
 * @author 武继明
 *  @since 2013年11月17日  下午9:06:52
 *
 */
public interface INotifyService {
	/**
	 * 添加短信通知策略
	 * @param notify
	 */
	void add(Notify notify);
	/**
	 * 列出所有短信通知策略
	 * @return
	 */
	List<Notify> list();
	/**
	 * 查询一个短信通知策略
	 * @param id
	 * @return
	 */
	Notify getOne(Long id);
	/**
	 * 更新
	 * @param carType
	 */
	void update(Notify notify) throws ExistedException;
	/**
	 * 删除
	 * @param id
	 */
	void delete(Long id) throws ServiceException;
}
