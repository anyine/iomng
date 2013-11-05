/**
 * 
 */
package sylarlove.advance.model.main;

import javax.persistence.Entity;
import javax.persistence.Table;

import sylarlove.advance.model.IdEntity;

/**
 * @author 武继明
 *  @since 2013年11月3日  下午7:59:28
 *
 */
@Entity
@Table(name="sa_t_permission")
public class Permission extends IdEntity{

	private static final long serialVersionUID = 1L;
	private String name;
	private String sn;
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
	
}
