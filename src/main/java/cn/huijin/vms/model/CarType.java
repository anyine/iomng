/**
 * 
 */
package cn.huijin.vms.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import sylarlove.advance.model.IdEntity;

/**
 * 车辆类型
 * @author 武继明
 *  @since 2013年10月11日  下午4:28:19
 *
 */
@Entity
@Table(name="t_car_type")
public class CarType extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Length(max=32)
	@Column(length=32,unique=true)
	private String name;
	
	@OneToMany(mappedBy="type",cascade={CascadeType.REMOVE})
	private Set<InnerCar> innerCars=new HashSet<InnerCar>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonBackReference
	public Set<InnerCar> getInnerCars() {
		return innerCars;
	}

	public void setInnerCars(Set<InnerCar> innerCars) {
		this.innerCars = innerCars;
	}
	
}
