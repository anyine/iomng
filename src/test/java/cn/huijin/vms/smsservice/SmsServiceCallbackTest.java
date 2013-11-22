/**
 * 
 */
package cn.huijin.vms.smsservice;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import sylarlove.advance.moudle.sms.IReciveCallBack;
import sylarlove.advance.moudle.sms.SmsService;

/**
 * 
 * @author 武继明
 *  @since 2013年11月21日  下午4:00:59
 *
 */
@SpringApplicationContext(value={"classpath:applicationContext.xml","applicationContext-sms.xml"})
public class SmsServiceCallbackTest extends UnitilsJUnit4{
	@SpringBeanByType
	private SmsService smsService;

	@Test
	public void callBack(){
		
		for(IReciveCallBack callBack: smsService.getReciveCallBacks()){
			callBack.process("15288843691","196611*1");
		}
	}
}
