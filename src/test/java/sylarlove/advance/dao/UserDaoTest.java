package sylarlove.advance.dao;

import java.util.Date;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import sylarlove.advance.model.main.User;
@SpringApplicationContext("classpath:applicationContext.xml")
@Transactional(value=TransactionMode.ROLLBACK,transactionManagerName="transactionManager")
public class UserDaoTest extends UnitilsJUnit4 {
	@SpringBeanByType
	private UserDao userDao;
  @Test
  public void findByUsername() {
	  User expected=new User("test", "测试", "abc", null, "15288843691", "wzslw@163.com", new Date(), "enable");
	  expected.setId(1L);
	  userDao.save(expected);
	  User actual=userDao.findByUsername(expected.getUsername());
	  ReflectionAssert.assertReflectionEquals(expected, actual, ReflectionComparatorMode.LENIENT_DATES);

  }
  @Test
  public void save() {
	  User expected=new User("test", "测试", "abc", null, "15288843691", "wzslw@163.com", new Date(), "enable");
	  expected.setId(1L);
	  userDao.save(expected);
	  User actual=userDao.findOne(expected.getId());
	  ReflectionAssert.assertReflectionEquals(expected, actual, ReflectionComparatorMode.LENIENT_DATES);
  }
}
