/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import sylarlove.advance.exception.ExistedException;
import sylarlove.advance.exception.ServiceException;
import cn.huijin.vms.model.CarType;

/**
 * @author 武继明
 *  @since 2013年10月14日  下午8:24:32
 *
 */
public interface ICarTypeService {
	/**
	 * 添加车辆类型
	 * @param carTypes
	 */
	void add(CarType carTypes);
	/**
	 * 列出所有车辆列表
	 * @return
	 */
	List<CarType> list();
	/**
	 * 查询一个车辆类型信息
	 * @param id
	 * @return
	 */
	CarType getOne(Long id);
	/**
	 * 更新车辆类型信息
	 * @param carType
	 */
	void update(CarType carType) throws ExistedException;
	/**
	 * 删除车辆类型
	 * @param id
	 */
	void delete(Long id) throws ServiceException;
}
