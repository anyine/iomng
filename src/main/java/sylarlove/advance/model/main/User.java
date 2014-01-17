/**
 * 
 */
package sylarlove.advance.model.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import sylarlove.advance.model.IdEntity;

/**
 * 用户对象
 * @author 武继明
 *  @since 2013年10月11日  上午11:15:42
 *
 */
@Entity
@Table(name="sa_t_user")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="sylarlove.advance.model.main")
public class User extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank
	@Length(min=1,max=32)
	@Column(nullable=false,length=32,updatable=false,unique=true)
	private String username;
	
	@NotBlank
	@Length(min=1,max=32)
	@Column(nullable=false,length=32,updatable=false)
	private String realname;
	
	@Column(nullable=false)
	private String password;
	@Transient
	private String plainPassword;
	
	@Length(max=12)
	private String phone;
	
	@Email
	@Length(max=128)
	@Column(length=128)
	private String email;
	
	@ManyToOne
	@JoinColumn(name="organization_id")
	private Organization organization;
	/**
	 * 用户创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time",updatable=false)
	private Date createTime=new Date();
	
	/**
	 * 使用状态 disabled,enabled
	 */
	@NotBlank
	@Length(max=16)
	@Column(nullable=false,length=16)
	private String status="enabled";
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private List<Role> roles=new ArrayList<Role>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPlainPassword() {
		return plainPassword;
	}
	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public User(String username, String realname, String password,
			String plainPassword, String phone, String email, Date createTime,
			String status) {
		super();
		this.username = username;
		this.realname = realname;
		this.password = password;
		this.plainPassword = plainPassword;
		this.phone = phone;
		this.email = email;
		this.createTime = createTime;
		this.status = status;
	}
	
	public User(){}
	@Override
	public String toString() {
		return this.username;
	}

}
