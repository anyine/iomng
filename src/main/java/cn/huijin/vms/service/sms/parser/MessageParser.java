package cn.huijin.vms.service.sms.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageParser {

	/**
	 * 在单元测试中，用来模拟当前时间 如果为null，则使用当前时间
	 */
	private static Date currentTime;

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date current() {
		if (currentTime != null) {
			return currentTime;
		}
		return new Date();
	}

	public static void setCurrent(Date time) {
		MessageParser.currentTime = time;
	}

	public static void setCurrent(String time) throws ParseException {
		MessageParser.currentTime = Util.fromString(time);
	}

	private static Pattern leave1 = Pattern
			.compile("^([1-9]\\d{2,3})([^0-9].*)$");
	private static Pattern leave2 = Pattern
			.compile("^([1-9]\\d{2,3})[^0-9]{1,3}([1-9]\\d{2,3})([^0-9].*)$");
	private static Pattern approve = Pattern
			.compile("^(0|00)([1-9]\\d*)$");
	private static Pattern record = Pattern.compile("^([1-9]\\d*)$");

	public static Token parse(String message) throws ParseException {
		Matcher mleave1 = leave1.matcher(message);
		Matcher mleave2 = leave2.matcher(message);
		Matcher mapprove = approve.matcher(message);
		Matcher mrecord = record.matcher(message);
		if (mleave2.find()) {
			Date start = toDate(mleave2.group(1));
			Date end = toDate(mleave2.group(2));
			if (end.before(start)) {
				end = addHours(end, 24);
			}
			return new LeaveToken(start, end, mleave2.group(3).trim());
		}

		if (mleave1.find()) {
			Date start = toDate(mleave1.group(1));
			Date end = addHours(start, 2);
			return new LeaveToken(start, end, mleave1.group(2).trim());
		}

		if (mapprove.find()) {
			Long id = Long.parseLong(mapprove.group(2));
			if (mapprove.group(1).equals("0")) {
				return new ApproveToken(id, true);
			}
			return new ApproveToken(id, false);
		}

		if (mrecord.find()) {
			Long id = Long.parseLong(mrecord.group(1));
			return new RecordToken(id);
		}
		return null;
	}

	private static Date addHours(Date start, int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		calendar.add(Calendar.HOUR, i);
		return calendar.getTime();
	}

	private static Date toDate(String date) throws ParseException {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd ");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HHmm:ss");
		if (date.length() == 3) {
			date = "0" + date;
		}
		Date dt = format2.parse(format1.format(current()) + date + ":00");
		if (dt.before(current())) {
			dt = addHours(dt, 24);
		}
		return dt;
	}
}
