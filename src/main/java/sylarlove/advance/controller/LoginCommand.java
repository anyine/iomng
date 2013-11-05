/**
 * 
 */
package sylarlove.advance.controller;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author 武继明
 *  @since 2013年11月3日  下午8:50:23
 *
 */
public class LoginCommand {
	@NotBlank(message="用户名不能为空。")
	private String username;
	@NotBlank(message="密码不能为空。")
	private String password;
	private Boolean rememberMe;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	 
}
