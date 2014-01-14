package cn.huijin.vms.service.sms.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Date fromString(String time) throws ParseException {
		return format.parse(time);
	}

	public static String fromDate(Date time) {
		return format.format(time);
	}
}
