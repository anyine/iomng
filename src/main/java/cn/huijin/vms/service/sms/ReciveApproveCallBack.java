/**
 * 
 */
package cn.huijin.vms.service.sms;

import java.text.ParseException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sylarlove.advance.dao.UserDao;
import sylarlove.advance.model.main.User;
import sylarlove.advance.moudle.sms.IReciveCallBack;
import sylarlove.advance.moudle.sms.ISmsService;
import cn.huijin.vms.model.Leave;
import cn.huijin.vms.service.ILeaveService;
import cn.huijin.vms.service.sms.parser.ApproveToken;
import cn.huijin.vms.service.sms.parser.MessageParser;
import cn.huijin.vms.service.sms.parser.Token;

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

	@Override
	public void process(String fromPhoneNumber, String message) {
		try {
			Token t = MessageParser.parse(message);
			if (t instanceof ApproveToken) {
				ApproveToken a = (ApproveToken) t;
				// 匹配审批
				// 查找手机号码绑定的负责人
				User user = userDao.findByPhone(fromPhoneNumber);
				if (user != null) {
					// 返回审批结果提示
					Leave leave = leaveService.findOne(a.getLeaveId());
					if (leave != null) {
						leaveService.approve(leave, user, a.getAgree());
						String str = "短信审批操作成功。您"
								+ (a.getAgree() ? "同意了" : "不同意")
								+ leave.getPerson().getName() + "的外出申请。";
						smsService.sendMessage(user.getPhone(), str);
					} else {
						String str = "没有找到编号为" + a.getLeaveId()
								+ "的申请信息。请核实编号。";
						smsService.sendMessage(user.getPhone(), str);
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return;
		}
	}
}
