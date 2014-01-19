/**
 * 
 */
package sylarlove.advance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sylarlove.advance.model.main.Permission;

/**
 * @author 武继明
 *  @since 2013年10月11日  下午2:17:18
 *
 */
public interface PermissionDao extends JpaRepository<Permission, Long>{
}
