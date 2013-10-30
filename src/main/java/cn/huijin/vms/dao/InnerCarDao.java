/**
 * 
 */
package cn.huijin.vms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.huijin.vms.model.InnerCar;

/**
 * @author 武继明
 *  @since 2013年10月11日  下午4:13:57
 *
 */
public interface InnerCarDao extends JpaRepository<InnerCar, Long>{
	/**
	 * 通过卡号获取车辆对象
	 * @param cardNumber
	 * @return
	 */
	InnerCar findByCardNumber(String cardNumber);
	/**
	 * 通过车牌号获取车辆对象
	 * @param license
	 * @return
	 */
	InnerCar findByLicense(String license);
	/**
	 * 
	 * @param cardNumber
	 * @param license
	 * @return
	 */
	InnerCar findByCardNumberAndLicenseNot(String cardNumber,String license);
}
