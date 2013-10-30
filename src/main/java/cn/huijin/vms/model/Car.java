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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import sylarlove.advance.model.IdEntity;

/**
 * 	车辆类
 * @author 武继明
 *  @since 2013年10月11日  下午4:10:28
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Car extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 车牌号
	 */
	@NotEmpty(message="{Car.license.NotEmpty}")
	@Length(max=10,message="{Car.license.Length}")
	@Column(length=10,unique=true,updatable=false)
	private String license;

	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="car")
	private Set<InnerCarRecord> carRecords=new HashSet<InnerCarRecord>();

	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
	@JoinColumn(name="car_id")
	private Card card;
	/**
	 * 添加日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time",updatable=false)
	private Date createTime=new Date();
	
	/**
	 * 车辆状态
	 * in,out
	 */
	@Enumerated(EnumType.STRING)
	private StatusType status=StatusType.IN;


	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}


	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public Set<InnerCarRecord> getCarRecords() {
		return carRecords;
	}

	public void setCarRecords(Set<InnerCarRecord> carRecords) {
		this.carRecords = carRecords;
	}

	
}
