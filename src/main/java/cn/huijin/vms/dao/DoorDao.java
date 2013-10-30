/**
 * 
 */
package cn.huijin.vms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.huijin.vms.model.Door;

/**
 * 
 * @author 武继明
 *  @since 2013年10月16日  下午12:06:04
 *
 */
public interface DoorDao extends JpaRepository<Door, Long>{
	Door findByName(String name);
}
