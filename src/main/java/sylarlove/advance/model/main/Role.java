/**
 * 
 */
package sylarlove.advance.model.main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import sylarlove.advance.model.IdEntity;

/**
 * @author 武继明
 *  @since 2013年11月3日  下午7:57:02
 *
 */
@Entity
@Table(name="sa_t_role")
public class Role extends IdEntity{

	private static final long serialVersionUID = 1L;
	private String name;
	private String sn;
	
	@ManyToMany
	@JoinColumn(name="role_id")
	private List<Permission> permissions=new ArrayList<Permission>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	
}
