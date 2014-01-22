package cn.huijin.vms.service.sms.test;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import cn.huijin.vms.service.sms.parser.ApproveToken;
import cn.huijin.vms.service.sms.parser.LeaveToken;
import cn.huijin.vms.service.sms.parser.MessageParser;
import cn.huijin.vms.service.sms.parser.RecordToken;
import cn.huijin.vms.service.sms.parser.Token;
import cn.huijin.vms.service.sms.parser.Util;

public class TestParser {
	
	public void assertEquals(Token s, Token t) {
		Assert.assertNotNull(s);
		Assert.assertNotNull(t);
		Assert.assertEquals(s.getClass(), t.getClass());
		if (s instanceof ApproveToken) {
			ApproveToken as = (ApproveToken) s;
			ApproveToken at = (ApproveToken) t;
			Assert.assertEquals(as.getAgree(), at.getAgree());
			Assert.assertEquals(as.getLeaveId(), at.getLeaveId());
		} else if (s instanceof LeaveToken) {
			LeaveToken as = (LeaveToken) s;
			LeaveToken at = (LeaveToken) t;
			Assert.assertEquals(as.getStart(), at.getStart());
			Assert.assertEquals(as.getEnd(), at.getEnd());
			Assert.assertEquals(as.getReason(), at.getReason());
		} else if (s instanceof RecordToken) {
			RecordToken as = (RecordToken) s;
			RecordToken at = (RecordToken) t;
			Assert.assertEquals(as.getRecordId(), at.getRecordId());
		}
	}

	private void assertApprove(String str, Boolean aggree, Long leaveId) throws ParseException {
		assertEquals(MessageParser.parse(str), new ApproveToken(leaveId, aggree));
	}

	private void assertLeave(String str, String start, String end, String reason)
			throws ParseException {
		assertEquals(MessageParser.parse(str),
				new LeaveToken(Util.fromString(start), Util.fromString(end), reason));
	}

	private void assertRecord(String str, Long recordId) throws ParseException {
		assertEquals(MessageParser.parse(str), new RecordToken(recordId));
	}

	@Test
	public void TestAll() throws ParseException {
		MessageParser.setCurrent("2014-01-24 08:09:10");
		
		//外出申请（短假）
		assertLeave("出差", "2014-01-24 08:09:10",
				"2014-01-24 10:09:10", "出差");
		
		assertLeave("912出差", "2014-01-24 08:09:10",
				"2014-01-24 09:12:00", "出差");
		
		assertLeave("712出差", "2014-01-24 08:09:10",
				"2014-01-25 07:12:00", "出差");
		
		assertLeave("712 1212出差", "2014-01-25 07:12:00",
				"2014-01-25 12:12:00", "出差");

		assertLeave("1112 出差", "2014-01-24 08:09:10",
				"2014-01-24 11:12:00", "出差");

		assertLeave("1112到1212  出差 ", "2014-01-24 11:12:00",
				"2014-01-24 12:12:00", "出差");
		
		assertLeave("1112-1212出差", "2014-01-24 11:12:00",
				"2014-01-24 12:12:00", "出差");

		assertLeave("1112 1212出差", "2014-01-24 11:12:00",
				"2014-01-24 12:12:00", "出差");

		assertLeave("1112  1212出差", "2014-01-24 11:12:00",
				"2014-01-24 12:12:00", "出差");
		
		assertLeave("1112***1212出差", "2014-01-24 11:12:00",
				"2014-01-24 12:12:00", "出差");
		
		//同意外出申请
		assertApprove("010", true, 10L);
		assertApprove("002", false, 2L);
		assertApprove("0099", false, 99L);
		
//		assertApprove("2342)0", false, 2342L);
//		assertApprove("122)0", false, 122L);
		
		//标记车辆外出违规
		assertRecord("1000", 1000L);
		assertRecord("100", 100L);
		assertRecord("10", 10L);
		assertRecord("1", 1L);
	}
}