/**
 * 
 */
package sylarlove.advance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sylarlove.advance.model.main.User;

/**
 * @author 武继明
 *  @since 2013年10月11日  下午2:17:18
 *
 */
public interface UserDao extends JpaRepository<User, Long>{
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
}
