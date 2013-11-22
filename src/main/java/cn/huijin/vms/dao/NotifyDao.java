/**
 * 
 */
package cn.huijin.vms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.huijin.vms.model.Notify;

/**
 * 
 * @author 武继明
 *  @since 2013年11月17日  下午9:05:17
 *
 */
public interface NotifyDao extends JpaRepository<Notify, Long>{

	/**
	 * 通过名字查找
	 * @param name
	 * @return
	 */
	Notify findByName(String name);

	/**
	 * 
	 * @param name
	 * @param id
	 * @return
	 */
	Notify findByNameAndIdNot(String name, Long id);

}
