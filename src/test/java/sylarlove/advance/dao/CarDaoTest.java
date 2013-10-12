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

import cn.huijin.vms.dao.CarDao;
import cn.huijin.vms.dao.CarTypeDao;
import cn.huijin.vms.model.Car;
import cn.huijin.vms.model.CarType;

@SpringApplicationContext("classpath:applicationContext.xml")
@Transactional(value = TransactionMode.ROLLBACK, transactionManagerName = "transactionManager")
public class CarDaoTest extends UnitilsJUnit4 {
	@SpringBeanByType
	private CarDao carDao;
	@SpringBeanByType
	private CarTypeDao carTypeDao;

	@Test
	public void findByLicense() {
		CarType ct=carTypeDao.findOne(1L);
		Car expected=new Car();
		expected.setCreateTime(new Date());
		expected.setLicense("WJ鲁0811X");
		expected.setRfid("111111");
		expected.setType(ct);
		carDao.save(expected);
		Car actual=carDao.findByLicense(expected.getLicense());
		ReflectionAssert.assertReflectionEquals(expected, actual, ReflectionComparatorMode.LENIENT_DATES);
	}

	@Test
	public void findByRfid() {
		CarType ct=carTypeDao.findOne(1L);
		Car expected=new Car();
		expected.setCreateTime(new Date());
		expected.setLicense("WJ鲁0811X");
		expected.setRfid("111111");
		expected.setType(ct);
		carDao.save(expected);
		Car actual=carDao.findByRfid(expected.getRfid());
		ReflectionAssert.assertReflectionEquals(expected, actual, ReflectionComparatorMode.LENIENT_DATES);

	}
}
