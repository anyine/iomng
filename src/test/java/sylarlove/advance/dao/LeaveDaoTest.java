package sylarlove.advance.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import cn.huijin.vms.dao.LeaveDao;
import cn.huijin.vms.model.InnerPerson;
import cn.huijin.vms.model.Leave;

@SpringApplicationContext("classpath:applicationContext.xml")
@Transactional(value = TransactionMode.COMMIT, transactionManagerName = "transactionManager")
public class LeaveDaoTest extends UnitilsJUnit4 {
	@SpringBeanByType
	private LeaveDao leaveDao;

	@Test
	public void save() {
		Leave l=new Leave();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			l.setStartTime(sdf.parse("2013-11-13 17:30"));
			l.setEndTime(sdf.parse("2013-11-13 20:30"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		InnerPerson innerPerson=new InnerPerson();
		innerPerson.setId(32768L);
		l.setPerson(innerPerson);
		l.setReason("回家看媳妇");
		leaveDao.save(l);
		
	}

	@Test
	public void findAgree() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date;
		try {
			date = sdf.parse("2013-11-13 17:40");
			Leave l=leaveDao.findAgreeLeave(32768L, date);
			System.out.println(l.getPerson().getName());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}
