/**
 * 
 */
package cn.huijin.vms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.huijin.vms.model.Controller;

/**
 * 
 * @author 武继明
 *  @since 2013年10月29日  下午8:48:49
 *
 */
public interface ControllerDao extends JpaRepository<Controller,Long>{
	List<Controller> findByDoorId(Long doorId);
}
