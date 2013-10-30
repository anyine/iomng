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

import sylarlove.advance.model.main.Organization;

/**
 * @author 武继明
 *  @since 2013年10月16日  上午9:19:34
 *
 */
@SpringApplicationContext("classpath:applicationContext.xml")
@Transactional(value = TransactionMode.COMMIT, transactionManagerName = "transactionManager")
public class OrganizationServiceTest extends UnitilsJUnit4{
	@SpringBeanByType
	private IOrganizationService organizationServcie;
	
	@Test
	public void add(){
		Organization parent=new Organization();
		Organization root=new Organization();
		root.setId(0L);
		parent.setParent(root);
		parent.setIndex(1);
		parent.setName("济宁公安消防支队");
		organizationServcie.add(parent);
		
		Organization c1=new Organization();
		c1.setIndex(1);
		c1.setName("司令部");
		c1.setParent(parent);
		organizationServcie.add(c1);
		Organization c2=new Organization();
		c2.setIndex(2);
		c2.setName("政治部");
		c2.setParent(parent);
		organizationServcie.add(c2);
		Organization c3=new Organization();
		c3.setIndex(3);
		c3.setName("后勤部");
		c3.setParent(parent);
		organizationServcie.add(c3);
		Organization c4=new Organization();
		c4.setIndex(4);
		c4.setName("防火部");
		c4.setParent(parent);
		organizationServcie.add(c4);
		Organization c5=new Organization();
		c5.setIndex(5);
		c5.setName("特勤中队");
		c5.setParent(parent);
		organizationServcie.add(c5);
	}
}
