/**
 * 
 */
package sylarlove.advance.service;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sylarlove.advance.dao.PermissionDao;
import sylarlove.advance.dao.RoleDao;
import sylarlove.advance.dao.UserDao;
import sylarlove.advance.exception.ExistedException;
import sylarlove.advance.model.main.Permission;
import sylarlove.advance.model.main.Role;
import sylarlove.advance.model.main.User;
import sylarlove.advance.realm.ShiroDbRealm;

/**
 * 
 * @author 武继明
 *  @since 2013年10月29日  下午3:26:42
 *
 */
@Service
@Transactional
public class UserService implements IUserService{
	static final Logger logger=LoggerFactory.getLogger(UserService.class);
	@Inject
	private UserDao userDao;
	@Inject
	private RoleDao roleDao;
	@Inject
	private PermissionDao permissionDao;
	@Override
	public List<User> list() {
		return userDao.findAll(new Sort(Direction.ASC, "id"));
	}
	@Override
	public void add(User user) {
		User exist=userDao.findByUsername(user.getUsername());
		if(exist!=null){
			throw new ExistedException("该用户已存在。");
		}
		//默认密码 123456
		user.setPassword(ShiroDbRealm.encryptPassword("123456"));
		userDao.save(user);
	}
	@Override
	public User getOne(Long id) {
		return userDao.findOne(id);
	}
	@Override
	public void update(User user) {
		User oldUser=userDao.findOne(user.getId());
		user.setPassword(oldUser.getPassword());
		userDao.save(user);
	}
	@Override
	public void delete(Long id) {
		//管理员不能删除
		if(1L==id){
			logger.warn("尝试删除超级管理员账户。");
			throw new ServiceException("超级管理员账户不能删除。");
		}
		//删除菜单角色
		User u=userDao.findOne(id);
		Role r=roleDao.findByName(u.getUsername());
		if(r!=null){
			roleDao.delete(r);
		}
		userDao.delete(id);
	}
	@Override
	public User getByUsername(String username) {
		return userDao.findByUsername(username);
	}
	@Override
	public void login(String username, String password, Boolean rememberMe) {
		UsernamePasswordToken token=new UsernamePasswordToken(username, password, rememberMe);
		SecurityUtils.getSubject().login(token);
	}
	@Override
	public void logout() {
		SecurityUtils.getSubject().logout();
	}
	@Override
	public void changePassword(String oldPassword, String newPassword) {
		User user=(User)SecurityUtils.getSubject().getPrincipal();
		if(!ShiroDbRealm.validatePassword(oldPassword, user.getPassword())){
			throw new sylarlove.advance.exception.ServiceException("旧密码不正确。");
		}
		user.setPassword(ShiroDbRealm.encryptPassword(newPassword));
		userDao.save(user);
	}
	@Override
	public void addPermission(Long id, List<Long> permissionIds) {
		User user=userDao.findOne(id);
		Role role=roleDao.findByName(user.getUsername());
		if(role==null){
			role=new Role();
			role.setName(user.getUsername());
			role.setSn(user.getUsername());
		}
		List<Permission> permissions=permissionDao.findAll(permissionIds);
		role.setPermissions(permissions);
		roleDao.save(role);
		user.setRoles(Arrays.asList(role));
	}
	@Override
	public Role getMenuRole(Long id) {
		return roleDao.findByName(userDao.findOne(id).getUsername());
	}
	
}
