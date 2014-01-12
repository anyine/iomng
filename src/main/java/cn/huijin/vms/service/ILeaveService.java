/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import sylarlove.advance.model.main.User;
import cn.huijin.vms.model.Leave;

/**
 * 
 * @author 武继明
 *  @since 2013年11月13日  下午9:13:25
 *
 */
public interface ILeaveService {
	/**
	 * 新增请假条
	 * @param leave
	 */
	void add(Leave leave);
	/**
	 * 请假条列表
	 * @return
	 */
	List<Leave> list();
	/**
	 * @param leaveId
	 * @return
	 */
	Leave findOne(Long leaveId);
	/**
	 * 审批申请
	 * @param leave
	 * @param user
	 * @param agree
	 */
	void approve(Leave leave, User user, Boolean agree);
	/**
	 * 改变申请批准状态
	 * @param id
	 */
	void changeAgree(Long id);
}
