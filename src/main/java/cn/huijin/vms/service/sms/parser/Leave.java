package cn.huijin.vms.service.sms.parser;

import java.util.Date;

public class Leave implements Token {
	private Date start;
	private Date end;
	private String reason;

	public Leave(Date start, Date end, String reason) {
		super();
		this.start = start;
		this.end = end;
		this.reason = reason;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
