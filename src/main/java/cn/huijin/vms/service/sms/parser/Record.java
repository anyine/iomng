package cn.huijin.vms.service.sms.parser;

public class Record implements Token {
	private Long recordId;

	public Record() {

	}

	public Record(Long recordId) {
		this.recordId = recordId;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

}
