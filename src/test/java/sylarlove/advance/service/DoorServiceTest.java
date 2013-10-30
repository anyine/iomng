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
import cn.huijin.vms.model.Controller;
import cn.huijin.vms.model.Door;
import cn.huijin.vms.model.Reader;
import cn.huijin.vms.model.StatusType;
import cn.huijin.vms.service.IDoorService;

/**
 * 
 * @author 武继明
 *  @since 2013年10月16日  下午12:13:46
 *
 */
@SpringApplicationContext("classpath:applicationContext.xml")
@Transactional(value = TransactionMode.COMMIT, transactionManagerName = "transactionManager")
public class DoorServiceTest extends UnitilsJUnit4{
	@SpringBeanByType
	private OrganizationDao organizationDao;
	@SpringBeanByType
	private IDoorService doorServcie;
	
//	@Test
	public void add(){
		Organization org=organizationDao.findByParentIdAndName(32768L, "司令部");
		//创建读卡器
		Reader reader=new Reader();
		reader.setNumber("1");
		reader.setGateNumber("1");
		reader.setType(StatusType.OUT);
		
		//创建控制器
		Controller controller=new Controller();
		controller.setIp("10.48.79.123");
		controller.setSn("111111");
		
		controller.getReaders().add(reader);
		reader.setController(controller);
		
		Door door=new Door();
		door.setOrganization(org);
		door.setName("正门");
		
		controller.setDoor(door);
		door.getControllers().add(controller);
		door.setOrganization(org);
		
		
		doorServcie.add(door);
		
	}
//	@Test
	public void delete(){
		doorServcie.delete(65536L);
	}
	@Test
	public void findAll(){
	}
}
