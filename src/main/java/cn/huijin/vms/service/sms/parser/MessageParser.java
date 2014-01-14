package cn.huijin.vms.service.sms.parser;

import java.text.ParseException;
import java.util.Date;

public class MessageParser {

	/**
	 * 在单元测试中，用来模拟当前时间
	 * 如果为null，则使用当前时间
	 */
	private static Date currentTime;

	/**
	 * 获取当前时间
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

	public static Token parse(String message) {
		return null;
	}
}
