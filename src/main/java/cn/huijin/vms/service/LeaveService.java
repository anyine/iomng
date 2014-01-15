/**
 * 
 */
package cn.huijin.vms.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sylarlove.advance.model.main.User;
import sylarlove.advance.moudle.sms.ISmsService;
import cn.huijin.vms.dao.InnerPersonDao;
import cn.huijin.vms.dao.LeaveDao;
import cn.huijin.vms.model.InnerPerson;
import cn.huijin.vms.model.Leave;

/**
 * 
 * @author 武继明
 * @since 2013年11月13日 下午9:15:06
 * 
 */
@Service
@Transactional
public class LeaveService implements ILeaveService {
	final static Logger logger = LoggerFactory.getLogger(LeaveService.class);
	@Inject
	private LeaveDao leaveDao;
	@Inject
	private InnerPersonDao personDao;
	@Inject
	ISmsService smsService;

	@Override
	public void add(Leave leave) {
		// 添加申请同事发送短信通知给请假负责人
		//设置SimpleId为1-99循环计数,如果需要允许三位数字，只需要修改为999即可.
		leave.setSimpleId((leaveDao.count() % 99) + 1);
		leaveDao.save(leave);

		InnerPerson person = personDao.findOne(leave.getPerson().getId());
		leave.setPerson(person);
		String message = person.getName()
				+ " 有一个外出申请，"
				+ "外出时间："
				+ DateFormatUtils.format(leave.getStartTime(),
						"yyyy-MM-dd HH:mm")
				+ "归队时间："
				+ DateFormatUtils
						.format(leave.getEndTime(), "yyyy-MM-dd HH:mm") + "事由："
				+ leave.getReason();
		// 如果已批准，则当前登录人为审批人
		if (leave.getAgree()) {
			User user = (User) SecurityUtils.getSubject().getPrincipal();
			leave.setRemark("提交已批准的审批。");
			approve(leave, user, true);
			message += "。该申请提交时已被管理员 " + user.getRealname() + " 批准。";
		} else {
			message += "。回复数字0" + leave.getSimpleId() + "同意,回复数字00"
					+ leave.getSimpleId() + "拒绝。系统时间：+"
					+ DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm");
		}
		for (User user : person.getUsers()) {
			if (user.getPhone() != null) {
				smsService.sendMessage(user.getPhone(), message);
			} else {
				logger.warn("用户：[{}]没有绑定手机号码。", user.getRealname());
			}
		}
	}

	@Override
	public List<Leave> list() {
		return leaveDao.findAll();
	}

	@Override
	public Leave findOne(Long leaveId) {
		return leaveDao.findOne(leaveId);
	}

	@Override
	public void approve(Leave leave, User user, Boolean agree) {
		// 短信通知申请者结果
		leave.setApprovePerson(user.getRealname());
		leave.setApproveTime(new Date());
		leave.setAgree(agree);
		leaveDao.save(leave);
		String phoneNumber = leave.getPerson().getPhone();
		String success = "已被"
				+ user.getRealname()
				+ "审批通过了。您在"
				+ DateFormatUtils.format(leave.getStartTime(),
						"yyyy-MM-dd HH-mm")
				+ "至"
				+ DateFormatUtils
						.format(leave.getEndTime(), "yyyy-MM-dd HH:mm")
				+ "可刷卡自由出入而不记录违规。";
		String fail = "没有通过。可致电" + user.getPhone() + "询问原因。";
		if (StringUtils.isNotBlank(phoneNumber)) {
			smsService.sendMessage(
					phoneNumber,
					"您于"
							+ DateFormatUtils.format(leave.getCreateTime(),
									"yyyy-MM-dd HH:mm") + "提交的外出申请"
							+ (agree ? success : fail));
		}
	}

	@Override
	public void changeAgree(Long id) {
		Leave leave = leaveDao.findOne(id);
		if (leave != null) {
			User user = (User) SecurityUtils.getSubject().getPrincipal();
			this.approve(leave, user, !leave.getAgree());
		}
	}

	@Override
	public Leave findBySimpleId(Long leaveId) {
		List<Leave> ls = leaveDao.findBySimpleId(leaveId, new Sort(
				Sort.Direction.DESC, "startTime"));
		if (ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}
}
