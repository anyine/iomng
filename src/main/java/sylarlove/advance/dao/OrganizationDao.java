/**
 * 
 */
package sylarlove.advance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sylarlove.advance.model.main.Organization;

/**
 * 
 * @author 武继明
 *  @since 2013年10月16日  上午9:04:23
 *
 */
public interface OrganizationDao extends JpaRepository<Organization, Long>{
	Organization  findByParentIdAndName(Long parentId,String name);
	List<Organization>  findByParentId(Long parentId);
}
