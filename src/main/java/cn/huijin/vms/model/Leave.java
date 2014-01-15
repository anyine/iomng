/**
 * 
 */
package cn.huijin.vms.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import sylarlove.advance.model.IdEntity;

/**
 * 请假条
 * @author 武继明
 *  @since 2013年10月22日  下午4:43:04
 *
 */
@Entity
@Table(name="t_leave")
public class Leave extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * simple id
	 * 简单ID 循环取值 用于简化短信审批操作 
	 */
	private Long simpleId;
	
	@NotNull(message="必须填写请假人。")
	@ManyToOne
	@JoinColumn(name="person_id")
	private InnerPerson person;//请假人
	
	@NotEmpty(message="必须填写事由")
	private String reason;//事由
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time")
	private Date startTime=new Date();

	@NotNull(message="必须填写归队时间。")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time")
	private Date endTime;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime=new Date();//创建时间
	
	private String remark;//备注
	
	private Boolean agree=false;//审批结果

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="approve_time")
	private Date approveTime;//审批时间
	
	private String approvePerson;//审批人
	@OneToMany(cascade={CascadeType.REMOVE},mappedBy="leave")
	private Set<InnerPersonRecord> personRecord=new HashSet<InnerPersonRecord>();
	
	public InnerPerson getPerson() {
		return person;
	}

	public void setPerson(InnerPerson person) {
		this.person = person;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getAgree() {
		return agree;
	}

	public void setAgree(Boolean agree) {
		this.agree = agree;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public String getApprovePerson() {
		return approvePerson;
	}

	public void setApprovePerson(String approvePerson) {
		this.approvePerson = approvePerson;
	}

	public Set<InnerPersonRecord> getPersonRecord() {
		return personRecord;
	}

	public void setPersonRecord(Set<InnerPersonRecord> personRecord) {
		this.personRecord = personRecord;
	}

	public Long getSimpleId() {
		return simpleId;
	}

	public void setSimpleId(Long simpleId) {
		this.simpleId = simpleId;
	}
	
}
