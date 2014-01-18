/**
 * 
 */
package cn.huijin.vms.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.huijin.vms.model.InnerPerson;

/**
 * 
 * @author 武继明
 *  @since 2013年10月22日  下午3:45:41
 *
 */
public interface InnerPersonDao extends JpaRepository<InnerPerson, Long>{

	List<InnerPerson> findByUsersId(Long id);
	/**
	 * 通过卡号获取人员对象
	 * @param cardNumber
	 * @return
	 */
	InnerPerson findByCardNumber(String cardNumber);
	/**
	 * 
	 * @param cardNumber
	 * @param id
	 * @return
	 */
	InnerPerson findByCardNumberAndIdNot(String cardNumber,Long id);
	/**
	 * @param certificate
	 * @return
	 */
	InnerPerson findByCertificate(String certificate);
	/**
	 * @param certificate
	 * @param id
	 * @return
	 */
	InnerPerson findByCertificateAndIdNot(String certificate, Long id);
	/**
	 * 通过绑定手机查询人员信息
	 * @param phone
	 * @return
	 */
	InnerPerson findByPhone(String phone);
}
