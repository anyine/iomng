/**
 * 
 */
package cn.huijin.vms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.huijin.vms.model.Car;

/**
 * @author 武继明
 *  @since 2013年10月11日  下午4:13:57
 *
 */
public interface CarDao extends JpaRepository<Car, Long>{
	/**
	 * 通过rfid获取车辆对象
	 * @param rfid
	 * @return
	 */
	Car findByRfid(String rfid);
	/**
	 * 通过车牌号获取车辆对象
	 * @param license
	 * @return
	 */
	Car findByLicense(String license);
	

}
