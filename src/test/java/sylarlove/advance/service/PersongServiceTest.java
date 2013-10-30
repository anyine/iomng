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

import sylarlove.advance.dao.OrganizationDao;
import sylarlove.advance.model.main.Organization;
import cn.huijin.vms.model.Card;
import cn.huijin.vms.model.InnerPerson;
import cn.huijin.vms.service.IInnerPersonService;

/**
 * 
 * @author 武继明
 *  @since 2013年10月22日  下午4:16:06
 *
 */
@SpringApplicationContext("classpath:applicationContext.xml")
@Transactional(value = TransactionMode.COMMIT, transactionManagerName = "transactionManager")
public class PersongServiceTest extends UnitilsJUnit4{
	@SpringBeanByType
	private OrganizationDao organizationDao;
	@SpringBeanByType
	private IInnerPersonService innerPersonServcie;
	
	@Test
	public void add(){
		
		InnerPerson p=new InnerPerson();
		Card card=new Card();
		card.setNumber("999999");
		p.setCard(card);
		
		p.setCertificate("3715211990090909090");
		p.setName("测试");
		
		Organization organization=organizationDao.findOne(32769L);
		p.setOrganization(organization);
		
		p.setSex("man");
		
		innerPersonServcie.add(p);
	}

}
