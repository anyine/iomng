package sylarlove.advance.dao;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import cn.huijin.vms.dao.InnerPersonDao;
import cn.huijin.vms.model.InnerPerson;
@SpringApplicationContext(value={"classpath:applicationContext.xml","applicationContext-sms.xml"})

@Transactional(value = TransactionMode.COMMIT, transactionManagerName = "transactionManager")
public class InnerPersonDaoTest extends UnitilsJUnit4 {
	@SpringBeanByType
	private InnerPersonDao innerPersonDao;

	@Test
	public void find() {
		InnerPerson p=innerPersonDao.findByPhone("15288843691");
		System.out.println(p.getName());
	}


}
