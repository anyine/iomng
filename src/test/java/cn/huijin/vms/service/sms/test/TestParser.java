package cn.huijin.vms.service.sms.test;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import cn.huijin.vms.service.sms.parser.Approve;
import cn.huijin.vms.service.sms.parser.Leave;
import cn.huijin.vms.service.sms.parser.MessageParser;
import cn.huijin.vms.service.sms.parser.Record;
import cn.huijin.vms.service.sms.parser.Token;
import cn.huijin.vms.service.sms.parser.Util;

public class TestParser {
	
	public void assertEquals(Token s, Token t) {
		Assert.assertNotNull(s);
		Assert.assertNotNull(t);
		Assert.assertEquals(s.getClass(), t.getClass());
		if (s instanceof Approve) {
			Approve as = (Approve) s;
			Approve at = (Approve) t;
			Assert.assertEquals(as.getAgree(), at.getAgree());
			Assert.assertEquals(as.getLeaveId(), at.getLeaveId());
		} else if (s instanceof Leave) {
			Leave as = (Leave) s;
			Leave at = (Leave) t;
			Assert.assertEquals(as.getReason(), at.getReason());
			Assert.assertEquals(as.getEnd(), at.getEnd());
			Assert.assertEquals(as.getStart(), at.getStart());
		} else if (s instanceof Record) {
			Record as = (Record) s;
			Record at = (Record) t;
			Assert.assertEquals(as.getRecordId(), at.getRecordId());
		}
	}

	private void assertApprove(String str, Boolean aggree, Long leaveId) {
		assertEquals(MessageParser.parse(str), new Approve(leaveId, aggree));
	}

	private void assertLeave(String str, String start, String end, String reason)
			throws ParseException {
		assertEquals(MessageParser.parse(str),
				new Leave(Util.fromString(start), Util.fromString(end), reason));
	}

	private void assertRecord(String str, Long recordId) {
		assertEquals(MessageParser.parse(str), new Record(recordId));
	}

	@Test
	public void TestAll() throws ParseException {
		MessageParser.setCurrent("2014-01-24 08:09:10");
		assertApprove("10 1", true, 10L);
		assertLeave("1112 1212 出差", "2014-01-24 11:12:00",
				"2014-01-24 12:12:00", "出差");
		assertRecord("10001", 10001L);
	}
}