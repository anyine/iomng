/**
 * 
 */
package cn.huijin.vms.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 车辆出入记录
 * @author 武继明
 *  @since 2013年10月22日  下午4:35:44
 *
 */
@Entity
@Table(name="t_inner_car_record")
public class InnerCarRecord extends Record{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 记录状态 normal 正常，warning 警告
	 */
	private String status="normal";
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	@ManyToOne
	@JoinColumn(name="form_id")
	private DispatchCarForm dispatchCarForm;
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public DispatchCarForm getDispatchCarForm() {
		return dispatchCarForm;
	}
	public void setDispatchCarForm(DispatchCarForm dispatchCarForm) {
		this.dispatchCarForm = dispatchCarForm;
	}
	public String getStatus() {
		return status;
	}
	/**
	 * 设置记录状态
	 * normal 正常，warning 警告
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	public InnerCarRecord(StatusType type, Car car,Door door) {
		super();
		this.setDoor(door);
		this.setType(type);
		this.car = car;
	}
	public InnerCarRecord() {
		super();
	}
	
}
