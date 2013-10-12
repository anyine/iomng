package sylarlove.advance.dao;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import cn.huijin.vms.dao.CarTypeDao;
import cn.huijin.vms.model.CarType;

@SpringApplicationContext("classpath:applicationContext.xml")
@Transactional(value = TransactionMode.COMMIT, transactionManagerName = "transactionManager")
public class CarTypeDaoTest extends UnitilsJUnit4 {
	@SpringBeanByType
	private CarTypeDao carTypeDao;

	@Test
	public void save() {
		CarType ct=new CarType();
		ct.setName("默认类型");
		carTypeDao.save(ct);
	}


}
