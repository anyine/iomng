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
import cn.huijin.vms.dao.InnerPersonRecordDao;
import cn.huijin.vms.model.InnerPersonRecord;

/**
 * 取消警告标记的短信回调
 * @author 武继明
 *  @since 2013年11月21日  下午2:45:45
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
	// 取消警告标记的短信匹配模式
	String regex = "^\\*\\d+$";
	Pattern pattern = Pattern.compile(regex);
	
	@Override
	public void process(String fromPhoneNumber, String message) {
		Matcher matcher =pattern.matcher(message);
		if(matcher.find()){
			User user=userDao.findByPhone(fromPhoneNumber);
			if(user!=null){
				cancleRecordWarning(user,matcher);
			}
		}
	}
	/**
	 * @param message
	 */
	private void cancleRecordWarning(User user,Matcher matcher) {
		Long recordId=Long.valueOf(matcher.group().substring(1));
		InnerPersonRecord record=recordDao.findOne(recordId);
		if(record!=null){
			record.setStatus("normal");
			recordDao.save(record);
		}else{
			String str="没有找到编号为"+recordId+"的记录信息。请核实编号。";
			smsService.sendMessage(user.getPhone(), str);
		}
	}

}
