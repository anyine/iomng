/**
 * 
 */
package cn.huijin.vms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name="t_car")
public class Car extends IdEntity{

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
	
	@Length(max=32,message="{Car.rfid.Length}")
	@Column(length=32)
	private String rfid;
	/**
	 * 添加日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time",updatable=false)
	private Date createTime;
	
	/**
	 * 车辆状态
	 * in,out
	 */
	private String status="in";

	/**
	 * 车辆类型
	 */
	@ManyToOne(optional=false)
	@JoinColumn(name="car_type_id")
	private CarType type;

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public CarType getType() {
		return type;
	}

	public void setType(CarType type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
