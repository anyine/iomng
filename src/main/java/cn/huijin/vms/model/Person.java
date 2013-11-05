/**
 * 
 */
package cn.huijin.vms.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import sylarlove.advance.model.IdEntity;

/**
 * 人员对象
 * @author 武继明
 *  @since 2013年10月14日  下午10:40:15
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Person extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty(message="{Person.name.NotEmpty}")
	@Length(max=32,message="{Person.name.Length}")
	@Column(unique=true,length=32)
	private String name;
	@Length(max=5,message="{Person.sex.Length}")
	@Column(length=5)
	private String sex="man";
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="card_id")
	private Card card;
	@Enumerated(EnumType.STRING)
	private StatusType status=StatusType.IN;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime=new Date();
	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="person")
	private Set<InnerPersonRecord> personRecords=new HashSet<InnerPersonRecord>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}

	public StatusType getStatus() {
		return status;
	}
	public void setStatus(StatusType status) {
		this.status = status;
	}
	public Set<InnerPersonRecord> getPersonRecords() {
		return personRecords;
	}
	public void setPersonRecords(Set<InnerPersonRecord> personRecords) {
		this.personRecords = personRecords;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSex() {
		return sex;
	}
	/**
	 * 设置性别 man,woman
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
