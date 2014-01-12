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
	String regex = "(\\d{12} )?(\\d{12} )?(\\bsy.+)";
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
		} else {
			logger.info("不正确格式的短信：{}", message);
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
					+ " [时间]空格[时间]空格sy:事由。 看起来应该像这样：201312250530 201312261507 sy回家";
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
		String reason = matcher.group(3).substring(3);
		if (startTime == null && endTime == null) {
			// 默认当前时间为起始时间
			leave.setStartTime(new Date());
			// 默认结束时间为当前时间之后两小时
			leave.setEndTime(DateUtils.addHours(new Date(), 2));
		} else if (endTime == null) {// 仅仅第二个为null
			leave.setStartTime(new Date());
			leave.setEndTime(startTime);
		} else {
			leave.setStartTime(startTime);
			leave.setEndTime(endTime);
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
		dateStr = StringUtils.trim(dateStr);
		Date date = null;
		try {
			// 第一次尝试
			date = DateUtils.parseDate(dateStr, "yyyyMMddHHmm");
			logger.debug("成功解析日期，格式为：yyyyMMddHHmm");
		} catch (ParseException e1) {
			try {
				// 第二次尝试
				date = DateUtils.parseDate(dateStr, "yyyy-MM-dd HH:mm");
				logger.debug("成功解析日期，格式为：yyyy-MM-dd HH:mm");
			} catch (ParseException e2) {

				try {
					date = DateUtils.parseDate(dateStr, "yyyy年MM月dd日 HH时mm分");
					logger.debug("成功解析日期，格式为：yyyy年MM月dd日 HH时mm分");
				} catch (ParseException e3) {
					// 第三次尝试
					date = DateUtils.parseDate(dateStr, "yyyy/MM/dd HH:mm");
					logger.debug("成功解析日期，格式为：yyyy/MM/dd HH:mm");
				}
			}
		}

		return date;
	}

	public static void main(String args[]) {
		String regex = "(\\d{12} )?(\\d{12} )?(\\bsy:.+)";
		Pattern pattern = Pattern.compile(regex);
		String message = "201312161800 sy:回家";
		Matcher matcher = pattern.matcher(message);
		if (matcher.find()) {
			System.out.println(matcher.group());
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println(matcher.group(3).substring(3));
		}
	}
}
