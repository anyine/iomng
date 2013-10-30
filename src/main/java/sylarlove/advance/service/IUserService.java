/**
 * 
 */
package sylarlove.advance.service;

import java.util.List;

import sylarlove.advance.exception.ExistedException;
import sylarlove.advance.exception.ServiceException;
import sylarlove.advance.model.main.User;

/**
 * 
 * @author 武继明
 *  @since 2013年10月29日  下午3:25:32
 *
 */
public interface IUserService {
	/**
	 * 添加用户
	 * @param user
	 */
	void add(User user);
	/**
	 * 列出所有用户
	 * @return
	 */
	List<User> list();
	/**
	 * 查询一个用户
	 * @param id
	 * @return
	 */
	User getOne(Long id);
	/**
	 * 更新用户
	 * @param user
	 */
	void update(User user) throws ExistedException;
	/**
	 * 删除用户
	 * @param id
	 */
	void delete(Long id) throws ServiceException;
}
