package sylarlove.advance.realm;

import javax.inject.Inject;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import sylarlove.advance.model.main.Permission;
import sylarlove.advance.model.main.Role;
import sylarlove.advance.model.main.User;
import sylarlove.advance.service.IUserService;

/**
 * 
 * @author 武继明
 * @since 2013年11月3日 下午6:47:42
 * 
 */
@Component
public class ShiroDbRealm extends AuthorizingRealm {
	private static final Logger log = LoggerFactory
			.getLogger(ShiroDbRealm.class);
	@Inject
	private IUserService userService;
	private static final String ALGORITHM = "SHA-1";// 密码加密方式

	/**
	 * 给ShiroDbRealm提供编码信息，用于密码密码比对 描述
	 */
	public ShiroDbRealm() {
		super();
		setName("shiroDbRealm");
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
				ALGORITHM);
		setCredentialsMatcher(matcher);
	}

	/**
	 * 认证回调函数, 登录时调用.
	 */
	// TODO 对认证进行缓存处理
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userService.getByUsername(token.getUsername());
		if (user != null) {
			if (user.getStatus().equals("disabled")) {
				throw new DisabledAccountException();
			}
			if(user.getId()==1L){
				log.info("超级管理员:{}[{}]登陆了系统", user.getRealname(),user.getUsername());
			}
			// 这里可以缓存认证
			return new SimpleAuthenticationInfo(user, user.getPassword(),
					getName());
		} else {
			return null;
		}

	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		User user = (User) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 给超级管理员赋予超级权限
		if (user.getId() == 1L) {
			info.addRole("admin");
			info.addStringPermission("*");
			return info;
		}
		if (!user.getRoles().isEmpty()) {
			for (Role r : user.getRoles()) {
				info.addRole(r.getSn());
				for (Permission p : r.getPermissions()) {
					info.addStringPermission(p.getSn());
				}
			}
		}
		log.info("{}[{}]登陆了系统", user.getRealname(),user.getUsername());
		return info;
	}

	public static class HashPassword {
		public String salt;
		public String password;
	}

	public static String encryptPassword(String plainPassword) {
		String result = new Sha1Hash(plainPassword).toHex();
		return result;
	}

	/**
	 * 
	 * 验证密码
	 * 
	 * @param plainPassword
	 *            明文密码
	 * @param password
	 *            密文密码
	 * @return
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		String oldPassword = new Sha1Hash(plainPassword).toHex();
		return password.equals(oldPassword);
	}

}
