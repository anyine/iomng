/**
 * 
 */
package cn.huijin.vms.service.sms;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sylarlove.advance.moudle.sms.IReciveCallBack;
import sylarlove.advance.moudle.sms.ISmsService;
import cn.huijin.vms.dao.InnerPersonDao;
import cn.huijin.vms.model.InnerPerson;
import cn.huijin.vms.model.Leave;
import cn.huijin.vms.service.ILeaveService;
import cn.huijin.vms.service.sms.parser.LeaveToken;
import cn.huijin.vms.service.sms.parser.MessageParser;
import cn.huijin.vms.service.sms.parser.Token;

/**
 * 收到人员申请短信的回调方法类
 * 
 * @author 武继明
 * @since 2013年11月21日 上午9:32:19
 * 
 */
public class ReciveLeaveCallBack implements IReciveCallBack {
	final static Logger logger = LoggerFactory
			.getLogger(ReciveLeaveCallBack.class);
	@Inject
	InnerPersonDao innerPersonDao;
	@Inject
	ILeaveService leaveService;
	@Inject
	ISmsService smsService;

	@Override
	public void process(String fromPhoneNumber, String message) {
		try {
			Token t = MessageParser.parse(message);
			if (t instanceof LeaveToken) {
				LeaveToken a = (LeaveToken) t;
				// 获取绑定手机号的人员信息
				InnerPerson person = innerPersonDao
						.findByPhone(fromPhoneNumber);
				if (person != null) {
					Leave leave = null;
					leave = new Leave();
					leave.setStartTime(a.getStart());
					leave.setEndTime(a.getEnd());
					leave.setReason(a.getReason());
					leave.setPerson(person);
					leaveService.add(leave);
				}
			}
		} catch (Exception e) {
			logger.info("格式有错误短信：{}", message);
			String str = "短信格式有误：正确的短信格式为："
					+ " [时间]空格[时间]事由。 看起来应该像这样：825 1507回家";
			smsService.sendMessage(fromPhoneNumber, str);
		}
	}

}
