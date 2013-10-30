/**
 * 
 */
package cn.huijin.vms.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import sylarlove.advance.model.main.Organization;
import sylarlove.advance.model.main.User;

/**
 * 内部车辆
 * @author 武继明
 *  @since 2013年10月15日  上午11:07:54
 *
 */
@Entity
@Table(name="t_inner_car")
public class InnerCar extends Car{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="organization_id")
	private Organization organization;
	/**
	 * 车辆类型
	 */
	@ManyToOne(optional=false)
	@JoinColumn(name="car_type_id")
	private CarType type;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;//负责人
	private String level="request";
	public CarType getType() {
		return type;
	}
	public void setType(CarType type) {
		this.type = type;
	}
	public String getLevel() {
		return level;
	}
	/**
	 * 设置通行级别，request(请求通行)，direct(直接通行)
	 * @param level
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 获取车辆负责人
	 * @return
	 */
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
}
