/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import sylarlove.advance.exception.ExistedException;
import cn.huijin.vms.model.InnerCar;

/**
 * @author 武继明
 *  @since 2013年10月13日  下午9:18:48
 *
 */
public interface IInnerCarService {
	/**
	 * 新增内部车辆
	 * @param innerCar
	 */
	void add(InnerCar innerCar) throws ExistedException;
	/**
	 * 删除内部车辆
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 更新内部车辆
	 * @param innerCar
	 */
	void update(InnerCar innerCar) throws ExistedException;
	/**
	 * 获取内部车辆信息
	 * @param id
	 * @return
	 */
	InnerCar getOne(Long id);
	/**
	 * 获取所有内部车辆信息
	 * @return
	 */
	List<InnerCar> list();
	/**
	 * 修改内部车辆状态
	 * @param id
	 */
	void changeStatus(Long id);
	/**
	 * 修改内部车辆通行级别
	 * @param id
	 */
	void changeLevel(Long id);
	
	
}
