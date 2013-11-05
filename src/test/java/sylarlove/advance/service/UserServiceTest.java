/**
 * 
 */
package sylarlove.advance.service;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import sylarlove.advance.model.main.Role;
import sylarlove.advance.model.main.User;
import sylarlove.advance.realm.ShiroDbRealm;

/**
 * 
 * @author 武继明
 *  @since 2013年10月29日  下午3:33:12
 *
 */
@SpringApplicationContext("classpath:applicationContext.xml")
@Transactional(value = TransactionMode.COMMIT, transactionManagerName = "transactionManager")
public class UserServiceTest extends UnitilsJUnit4{
	@SpringBeanByType
	private IUserService userService;
	
	@Test
	public void add(){
		User user=new User();
		user.setId(1L);
		user.setUsername("admin");
		user.setEmail("wzslw@163.com");
		user.setPhone("15288843691");
		user.setRealname("武继明");
		userService.add(user);
		
		
	}

	@Test
	public void findAll(){
	}
}
