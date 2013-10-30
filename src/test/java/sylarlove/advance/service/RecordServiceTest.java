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

import cn.huijin.vms.service.IRecordService;

/**
 * 
 * @author 武继明
 * @since 2013年10月25日 下午3:00:13
 * 
 */
@SpringApplicationContext("classpath:applicationContext.xml")
@Transactional(value = TransactionMode.COMMIT, transactionManagerName = "transactionManager")
public class RecordServiceTest extends UnitilsJUnit4 {
	@SpringBeanByType
	private IRecordService recordService;

	@Test
	public void add() {
		recordService.add("111111", "2", "111111");
	}
}
