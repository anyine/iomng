package sylarlove.advance.dao;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import cn.huijin.vms.dao.CarTypeDao;
import cn.huijin.vms.dao.InnerCarDao;

@SpringApplicationContext("classpath:applicationContext.xml")
@Transactional(value = TransactionMode.ROLLBACK, transactionManagerName = "transactionManager")
public class CarDaoTest extends UnitilsJUnit4 {
	@SpringBeanByType
	private InnerCarDao innerCarDao;
	@SpringBeanByType
	private CarTypeDao carTypeDao;

	@Test
	public void findByLicense() {
	}

	@Test
	public void findByRfid() {
	}

	@Test
	public void findByRfidAndLicenseNot() {
	}
}
