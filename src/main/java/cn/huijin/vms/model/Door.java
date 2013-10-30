/**
 * 
 */
package cn.huijin.vms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import sylarlove.advance.model.IdEntity;
import sylarlove.advance.model.main.Organization;

/**
 * 门
 * @author 武继明
 *  @since 2013年10月16日  上午10:45:42
 *
 */
@Entity
@Table(name="t_door")
public class Door extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty(message="门的名称不能为空。")
	private String name;
	
	@NotNull(message="机构不能为空。")
	@ManyToOne
	@JoinColumn(name="organization_id")
	private Organization organization;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="door")
	private List<Controller> controllers=new ArrayList<Controller>();
	
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public List<Controller> getControllers() {
		return controllers;
	}
	public void setControllers(List<Controller> controllers) {
		this.controllers = controllers;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
