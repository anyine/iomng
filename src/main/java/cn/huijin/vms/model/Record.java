/**
 * 
 */
package cn.huijin.vms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import sylarlove.advance.model.IdEntity;

/**
 * 出入记录
 * @author 武继明
 *  @since 2013年10月22日  下午4:27:34
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Record extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Enumerated(EnumType.STRING)
	private StatusType type=StatusType.OUT;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date=new Date();
	
	@NotNull(message="出入记录门信息不可为空")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="door_id")
	private Door door;

	public StatusType getType() {
		return type;
	}

	public void setType(StatusType type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Door getDoor() {
		return door;
	}

	public void setDoor(Door door) {
		this.door = door;
	}
	
	
}
