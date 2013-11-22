/**
 * 
 */
package sylarlove.advance.service;

import java.io.IOException;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import sylarlove.advance.moudle.sms.ISmsService;
import sylarlove.advance.moudle.sms.SmsService;
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
//		recordService.add("111111", "2", "000000");
//		ISmsService sms=new SmsService("13010171500", "1234");
	}
	
	@Test
	public void sms() {
		ISmsService sms=new SmsService("13010171500", "1234");
		sms.sendMessage("15853725352", "短信测试。");
		System.out.println("Now Sleeping - Hit <enter> to terminate.");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			sms.closeService();
		}
	}
}
