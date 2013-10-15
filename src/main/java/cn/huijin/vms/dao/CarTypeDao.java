/**
 * 
 */
package cn.huijin.vms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.huijin.vms.model.CarType;

/**
 * @author 武继明
 *  @since 2013年10月11日  下午4:13:57
 *
 */
public interface CarTypeDao extends JpaRepository<CarType, Long>{

	/**
	 * 通过名字查找车辆类型
	 * @param name
	 * @return
	 */
	CarType findByName(String name);

	/**
	 * 
	 * @param name
	 * @param id
	 * @return
	 */
	CarType findByNameAndIdNot(String name, Long id);

}
