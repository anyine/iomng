/**
 * 
 */
package sylarlove.advance.model.main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import sylarlove.advance.model.IdEntity;

/**
 * 机构对象
 * @author 武继明
 *  @since 2013年10月16日  上午8:54:14
 *
 */
@Entity
@Table(name="sa_t_organization")
public class Organization extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty(message="机构名称不能为空")
	@Length(max=32,message="机构名称长度最大为{max}")
	@Column( length=32)
	private String name;
	@Column(name="[index]")
	private Integer index;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	public Organization  parent;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="parent",fetch=FetchType.EAGER)
	@OrderBy("index ASC")
	public List<Organization>  children=new ArrayList<Organization>();
	
	public Integer getIndex() {
		return index;
	}
	/**
	 * 序号
	 * @param index
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
	@JsonBackReference
	public Organization getParent() {
		return parent;
	}
	public void setParent(Organization parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Organization> getChildren() {
		return children;
	}
	public void setChildren(List<Organization> children) {
		this.children = children;
	}
	
	
}
