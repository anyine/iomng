/**
 * 
 */
package cn.huijin.vms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.huijin.vms.model.Reader;

/**
 * 
 * @author 武继明
 *  @since 2013年10月25日  上午9:59:39
 *
 */
public interface ReaderDao extends JpaRepository<Reader,Long>{
	Reader findByControllerSnAndNumber(String controllerSn,String number);
}
