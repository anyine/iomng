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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import sylarlove.advance.model.IdEntity;

/**
 * 短信通知时间
 * @author 武继明
 *  @since 2013年11月17日  下午8:49:04
 *
 */
@Entity
@Table(name="t_notify")
public class Notify extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Length(max=32)
	@Column(length=32,unique=true)
	private String name;
	@NotNull(message="起始时间必须填写。")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	@NotNull(message="截止时间必须填写。")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
}
