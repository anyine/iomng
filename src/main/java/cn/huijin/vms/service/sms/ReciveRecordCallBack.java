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
import cn.huijin.vms.dao.InnerPersonRecordDao;
import cn.huijin.vms.model.InnerPersonRecord;
import cn.huijin.vms.service.sms.parser.MessageParser;
import cn.huijin.vms.service.sms.parser.RecordToken;
import cn.huijin.vms.service.sms.parser.Token;

/**
 * 取消警告标记的短信回调
 * 
 * @author 武继明
 * @since 2013年11月21日 下午2:45:45
 * 
 */
public class ReciveRecordCallBack implements IReciveCallBack {
	final static Logger logger = LoggerFactory
			.getLogger(ReciveRecordCallBack.class);
	@Inject
	InnerPersonRecordDao recordDao;
	@Inject
	UserDao userDao;
	@Inject
	ISmsService smsService;

	@Override
	public void process(String fromPhoneNumber, String message) {
		try {
			Token t = MessageParser.parse(message);
			if (t instanceof RecordToken) {
				RecordToken r = (RecordToken) t;
				User user = userDao.findByPhone(fromPhoneNumber);
				if (user != null) {
					InnerPersonRecord record = recordDao.findOne(r
							.getRecordId());
					if (record != null) {
						record.setStatus("normal");
						recordDao.save(record);
					} else {
						String str = "没有找到编号为" + r.getRecordId()
								+ "的记录信息。请核实编号。";
						smsService.sendMessage(user.getPhone(), str);
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
