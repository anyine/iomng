/**
 * 
 */
package cn.huijin.vms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import sylarlove.advance.model.IdEntity;

/**
 * 人员对象
 * @author 武继明
 *  @since 2013年10月14日  下午10:40:15
 *
 */
@Entity
@Table(name="t_person")
public class Person extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty(message="{Person.name.NotEmpty}")
	@Length(max=32,message="{Person.name.Length}")
	@Column(unique=true,length=32,updatable=false)
	private String name;
	@NotEmpty(message="{Person.name.NotEmpty}")
	@Length(max=32,message="{Person.name.Length}")
	private String realname;
	@Length(max=32,message="{Person.rfid.Length}")
	@Column(length=32)
	private String rfid;
	
	private String status="in";
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public String getStatus() {
		return status;
	}
	/**
	 * 设置人员状态、in 、 out
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
