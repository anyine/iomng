/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import sylarlove.advance.exception.ExistedException;
import cn.huijin.vms.model.Car;

/**
 * @author 武继明
 *  @since 2013年10月13日  下午9:18:48
 *
 */
public interface ICarService {
	/**
	 * 新增车辆
	 * @param car
	 */
	void add(Car car) throws ExistedException;
	/**
	 * 删除车辆
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 更新车辆
	 * @param car
	 */
	void update(Car car) throws ExistedException;
	/**
	 * 获取车辆信息
	 * @param id
	 * @return
	 */
	Car getOne(Long id);
	/**
	 * 获取所有车辆信息
	 * @return
	 */
	List<Car> list();
	/**
	 * 修改车辆状态
	 * @param id
	 */
	void changeStatus(Long id);
	
	
}
