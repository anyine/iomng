/**
 * 
 */
package cn.huijin.vms.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import sylarlove.advance.model.main.Organization;

/**
 * 内部人员
 * @author 武继明
 *  @since 2013年10月15日  上午11:17:35
 *
 */
@Entity
@Table(name="t_inner_person")
public class InnerPerson extends Person{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty(message="证件号码不能为空")
	private String certificate;
	@ManyToOne
	@JoinColumn(name="organization_id")
	private Organization organization;
	public String getCertificate() {
		return certificate;
	}
	/**
	 * 设置证件号码
	 * @param certificate
	 */
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
}
