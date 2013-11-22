/**
 * 
 */
package sylarlove.advance.service;

import java.text.ParseException;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import cn.huijin.vms.model.Notify;
import cn.huijin.vms.service.INotifyService;

/**
 * 
 * @author 武继明
 *  @since 2013年11月17日  下午9:19:12
 *
 */
@SpringApplicationContext("classpath:applicationContext.xml")
@Transactional(value = TransactionMode.COMMIT, transactionManagerName = "transactionManager")
public class NotifyTest extends UnitilsJUnit4{
	@SpringBeanByType
	private INotifyService notifyService;
	
	@Test
	public void add(){
		Notify notify=new Notify();
		notify.setName("车辆短信通知时间配置");
		try {
			notify.setStartTime(DateUtils.parseDate("18:45", "HH:mm"));
			notify.setEndTime(DateUtils.parseDate("8:45", "HH:mm"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		notifyService.add(notify);
	}

	@Test
	public void findAll(){
	}
}
