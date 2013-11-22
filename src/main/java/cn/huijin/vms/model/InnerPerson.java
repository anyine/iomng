/**
 * 
 */
package cn.huijin.vms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import sylarlove.advance.model.main.Organization;
import sylarlove.advance.model.main.User;

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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="notify_id")
	private Notify notify;
	@NotEmpty(message="证件号码不能为空")
	private String certificate;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="organization_id")
	private Organization organization;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="person_id")
	private List<User> users=new ArrayList<User>();//请假负责人
	
	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.REMOVE},mappedBy="person")
	private List<Leave> leaves=new ArrayList<Leave>();
	
	public List<Leave> getLeaves() {
		return leaves;
	}
	public void setLeaves(List<Leave> leaves) {
		this.leaves = leaves;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
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
	public Notify getNotify() {
		return notify;
	}
	public void setNotify(Notify notify) {
		this.notify = notify;
	}
	
}
