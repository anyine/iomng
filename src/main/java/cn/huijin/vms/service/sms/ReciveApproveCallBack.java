/**
 * 
 */
package cn.huijin.vms.service.sms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sylarlove.advance.dao.UserDao;
import sylarlove.advance.model.main.User;
import sylarlove.advance.moudle.sms.IReciveCallBack;
import sylarlove.advance.moudle.sms.ISmsService;
import cn.huijin.vms.model.Leave;
import cn.huijin.vms.service.ILeaveService;

/**
 * 申请审批短信回调类
 * 
 * @author 武继明
 * @since 2013年11月21日 下午1:50:15
 * 
 */
public class ReciveApproveCallBack implements IReciveCallBack {
	final static Logger logger = LoggerFactory
			.getLogger(ReciveApproveCallBack.class);
	@Inject
	UserDao userDao;
	@Inject
	ILeaveService leaveService;
	@Inject
	ISmsService smsService;
	// 申请审批信息匹配模式
	String regex = "^(\\d+)\\*([0,1])$";
	Pattern pattern = Pattern.compile(regex);

	@Override
	public void process(String fromPhoneNumber, String message) {
		Matcher matcher = pattern.matcher(message);
		if (matcher.find()) {
			// 匹配审批
			// 查找手机号码绑定的负责人
			User user = userDao.findByPhone(fromPhoneNumber);
			if (user != null) {
				approveLeave(user, matcher);
			}
		}
	}

	/**
	 * 审批申请
	 * 
	 * @param user
	 * @param message
	 */
	private void approveLeave(User user, Matcher matcher) {
		// 返回审批结果提示
		Long leaveId = Long.valueOf(matcher.group(1));
		Leave leave = leaveService.findOne(leaveId);
		if (leave != null) {
			Boolean agree = "1".equals(matcher.group(2));
			leaveService.approve(leave, user, agree);
			String str = "短信审批操作成功。您" + (agree ? "同意了" : "不同意")
					+ leave.getPerson().getName() + "的外出申请。";
			smsService.sendMessage(user.getPhone(), str);
		} else {
			String str = "没有找到编号为" + leaveId + "的申请信息。请核实编号。";
			smsService.sendMessage(user.getPhone(), str);
		}

	}

}
