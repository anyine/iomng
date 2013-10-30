/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import sylarlove.advance.exception.ExistedException;
import cn.huijin.vms.model.InnerPerson;

/**
 * 
 * @author 武继明
 *  @since 2013年10月22日  下午3:41:02
 *
 */
public interface IInnerPersonService {
	/**
	 * 新增内部人员
	 * @param innerPerson
	 */
	void add(InnerPerson innerPerson) throws ExistedException;
	/**
	 * 删除内部人员
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 更新内部人员
	 * @param innerPerson
	 */
	void update(InnerPerson innerPerson) throws ExistedException;
	/**
	 * 获取内部人员信息
	 * @param id
	 * @return
	 */
	InnerPerson getOne(Long id);
	/**
	 * 获取所有内部人员
	 * @return
	 */
	List<InnerPerson> list();
	/**
	 * 修改内部人员状态
	 * @param id
	 */
	void changeStatus(Long id);
	
	
}
