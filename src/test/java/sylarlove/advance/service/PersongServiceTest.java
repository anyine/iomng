/**
 * 
 */
package sylarlove.advance.service;

import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import sylarlove.advance.dao.OrganizationDao;
import sylarlove.advance.model.main.Organization;
import sylarlove.advance.model.main.User;
import cn.huijin.vms.model.Card;
import cn.huijin.vms.model.InnerCar;
import cn.huijin.vms.model.InnerPerson;
import cn.huijin.vms.service.IInnerCarService;
import cn.huijin.vms.service.IInnerPersonService;

/**
 * 
 * @author 武继明
 * @since 2013年10月22日 下午4:16:06
 * 
 */
@SpringApplicationContext("classpath:applicationContext.xml")
@Transactional(value = TransactionMode.COMMIT, transactionManagerName = "transactionManager")
public class PersongServiceTest extends UnitilsJUnit4 {
	@SpringBeanByType
	private OrganizationDao organizationDao;
	@SpringBeanByType
	private IInnerPersonService innerPersonServcie;
	@Test
	public void add() {

		InnerPerson p = new InnerPerson();
		Card card = new Card();
		card.setNumber("888888");
		p.setCard(card);

		p.setCertificate("3715211990090909090");
		p.setName("测试1");

		Organization organization = organizationDao.findOne(32769L);
		p.setOrganization(organization);

		p.setSex("man");
		User u1 = new User();
		u1.setId(1L);
		User u2 = new User();
		u2.setId(131072L);
		p.getUsers().add(u2);
		p.getUsers().add(u1);
		innerPersonServcie.add(p);
	}

	@Test
	public void find() {
	}
}
