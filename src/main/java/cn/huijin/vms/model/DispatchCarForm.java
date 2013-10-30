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

import sylarlove.advance.model.IdEntity;

/**
 * 派车单
 * @author 武继明
 *  @since 2013年10月22日  下午5:11:04
 *
 */
@Entity
@Table(name="t_dispatch_car_form")
public class DispatchCarForm extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	private String driver;
	private String user;
	private String line;//行车路线
	private String target;//到达地点
	private String reason;//事由
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time")
	private Date startTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time")
	private Date endTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="approve_time")
	private Date approveTime;
	
	private String carManager;//车管干部
	private String approvePerson;//批准人
	private Boolean agree=false;
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
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
	public String getCarManager() {
		return carManager;
	}
	public void setCarManager(String carManager) {
		this.carManager = carManager;
	}
	public String getApprovePerson() {
		return approvePerson;
	}
	public void setApprovePerson(String approvePerson) {
		this.approvePerson = approvePerson;
	}
	public Boolean getAgree() {
		return agree;
	}
	public void setAgree(Boolean agree) {
		this.agree = agree;
	}
	
	
}
