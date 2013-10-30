/**
 * 
 */
package cn.huijin.vms.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 外来车辆
 * @author 武继明
 *  @since 2013年10月15日  上午11:08:37
 *
 */
@Entity
@Table(name="t_outer_car")
public class OuterCar extends Car{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
