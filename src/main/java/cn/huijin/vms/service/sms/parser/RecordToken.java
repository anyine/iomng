package cn.huijin.vms.service.sms.parser;

public class RecordToken implements Token {
	private Long recordId;

	public RecordToken() {

	}

	public RecordToken(Long recordId) {
		this.recordId = recordId;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

}
