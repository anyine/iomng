/**
 * 
 */
package cn.huijin.vms.service.sms;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sylarlove.advance.moudle.sms.IReciveCallBack;
import sylarlove.advance.moudle.sms.ISmsService;
import cn.huijin.vms.dao.InnerPersonDao;
import cn.huijin.vms.model.InnerPerson;
import cn.huijin.vms.model.Leave;
import cn.huijin.vms.service.ILeaveService;

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

	// 申请信息匹配模式
	String regex = "([0-9]{4}[年|/-]?[0,1]?[1-9][月|/-]?[0-3]?[0-9]日?\\s?[0,2]?[0-5]?[0-9][点|:]?+[0-5]?[0-9]分?)?[\\*|\\s|,|，]?([0-9]{4}[年|/-]?[0,1]?[1-9][月|/-]?[0-3]?[0-9]日?\\s?[0,2]?[0-5]?[0-9][点|:]?+[0-5]?[0-9]分?)?[\\*|\\s|,|，]?(\\S+)";
	Pattern pattern = Pattern.compile(regex);
	@Override
	public void process(String fromPhoneNumber, String message) {
		Matcher matcher = pattern.matcher(message);
		if (matcher.find()) {// 能匹配
			// 获取绑定手机号的人员信息
			InnerPerson person = innerPersonDao.findByPhone(fromPhoneNumber);
			if (person != null) {
				smsAddLeave(person, matcher);
			}
		}
	}

	/**
	 * 短信添加人员外出申请
	 */
	private void smsAddLeave(InnerPerson person, Matcher matcher) {
		// 解析信息内容，如果信息格式不正确，发送提示，格式正确则执行申请
		Leave leave = null;
		try {
			leave = parse2Leave(matcher);
		} catch (Exception e) {
			logger.info("格式有错误短信：{}", matcher.group());
			String str = "短信格式有误：正确的短信格式为："
					+ " [时间]（空白符|*|，|,）[时间]（空白符|*|，|,）事由。"
					+ "----[]内为可选,()内为任选一字符。" + " 其中日期时间格式可以为以下几种："
					+ "yyyyMMddHHmm、" + "yyyy-MM-dd HH:mm、"
					+ "yyyy年MM月dd日 HH时mm分、" + "yyyy/MM/dd HH:mm";
			smsService.sendMessage(person.getPhone(), str);
		}
		leave.setPerson(person);
		leaveService.add(leave);
	}

	/**
	 * 将信息字符串解析成请假申请，支持的字符串格式为<br>
	 * [时间]（空白符|*|，|,）[时间]（空白符|*|，|,）事由----[]内为可选,()内为任选一字符
	 * 其中日期时间格式可以为以下几种：yyyyMMddHHmm、yyyy-MM-dd HH:mm、yyyy年MM月dd日
	 * HH时mm分、yyyy/MM/dd HH:mm
	 * 
	 * @param message
	 * @return
	 * @throws ParseException
	 */
	private Leave parse2Leave(Matcher matcher) throws ParseException {
		Leave leave = null;
		leave = new Leave();
		Date startTime = parse2Date(matcher.group(1));
		Date endTime = parse2Date(matcher.group(2));
		String reason = matcher.group(3);
		if (startTime == null) {
			// 默认当前时间为起始时间
			leave.setStartTime(new Date());
		}
		if (endTime == null) {
			// 默认结束时间为当前时间之后两小时
			leave.setEndTime(DateUtils.addHours(new Date(), 2));
		}
		leave.setReason(reason);
		return leave;
	}

	/**
	 * 将字符串解析成时间
	 * 
	 * @param group
	 * @return
	 * @throws ParseException
	 */
	private Date parse2Date(String dateStr) throws ParseException {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}

		Date date = null;
		try {
			// 第一次尝试
			DateUtils.parseDate("yyyyMMddHHmm", dateStr);
			logger.debug("成功解析日期，格式为：yyyy年MM月dd日 HH时mm分");
		} catch (ParseException e1) {
			try {
				// 第二次尝试
				DateUtils.parseDate("yyyy-MM-dd HH:mm", dateStr);
				logger.debug("成功解析日期，格式为：yyyy-MM-dd HH:mm");
			} catch (ParseException e2) {

				try {
					DateUtils.parseDate("yyyy年MM月dd日 HH时mm分", dateStr);
					logger.debug("成功解析日期，格式为：yyyy年MM月dd日 HH时mm分");
				} catch (ParseException e3) {
					// 第三次尝试
					DateUtils.parseDate("yyyy/MM/dd HH:mm", dateStr);
					logger.debug("成功解析日期，格式为：yyyy/MM/dd HH:mm");
				}
			}
		}

		return date;
	}
}
