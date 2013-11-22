/**
 * 
 */
package cn.huijin.vms.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 人员出入记录
 * @author 武继明
 *  @since 2013年10月22日  下午4:38:08
 *
 */
@Entity
@Table(name="t_inner_person_record")
public class InnerPersonRecord extends Record{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 记录状态 normal 正常，warning 警告
	 */
	private String status="normal";
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="leave_id")
	private Leave leave;
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	/**
	 * 获取请假条
	 * @return
	 */
	public Leave getLeave() {
		return leave;
	}
	public void setLeave(Leave leave) {
		this.leave = leave;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public InnerPersonRecord(StatusType type, Person person,Door door) {
		super();
		this.setDoor(door);
		this.setType(type);
		this.person = person;
	}
	public InnerPersonRecord() {
		super();
	}
	
	
	
}
