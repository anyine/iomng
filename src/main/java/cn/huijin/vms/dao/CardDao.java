/**
 * 
 */
package cn.huijin.vms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.huijin.vms.model.Card;

/**
 * 
 * @author 武继明
 *  @since 2013年10月25日  上午10:07:09
 *
 */
public interface CardDao extends JpaRepository<Card,Long>{
	Card findByNumber(String number);
}
