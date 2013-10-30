/**
 * 
 */
package cn.huijin.vms.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 外部人员
 * @author 武继明
 *  @since 2013年10月15日  上午11:17:35
 *
 */

@Entity
@Table(name="t_outer_person")
public class OuterPerson extends Person{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
