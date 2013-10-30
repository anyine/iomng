/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import sylarlove.advance.exception.ExistedException;
import cn.huijin.vms.model.Door;


/**
 * 
 * @author 武继明
 *  @since 2013年10月16日  下午12:06:57
 *
 */
public interface IDoorService {
	/**
	 * 添加一个门
	 * @param door
	 */
	void add(Door door) throws ExistedException;
	/**
	 * 删除一个门
	 * @param id
	 */
	void delete(Long id);
	List<Door> list();
	/**
	 * 更新门信息
	 * @param door
	 */
	void update(Door door);
	/**
	 * 获取一个门
	 * @param id
	 * @return
	 */
	Door getOne(Long id);
}
