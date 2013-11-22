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

import cn.huijin.vms.dao.DispatchCarFormDao;
import cn.huijin.vms.model.Car;
import cn.huijin.vms.model.DispatchCarForm;
import cn.huijin.vms.model.InnerCar;

@SpringApplicationContext("classpath:applicationContext.xml")
@Transactional(value = TransactionMode.COMMIT, transactionManagerName = "transactionManager")
public class DispatchCarFormDaoTest extends UnitilsJUnit4 {
	@SpringBeanByType
	private DispatchCarFormDao dispatchCarFormDao;

	@Test
	public void save() {
		DispatchCarForm dcf = new DispatchCarForm();
		Car car = new InnerCar();
		car.setId(262144L);
		dcf.setCar(car);
		dcf.setCarManager("车管干部");
		dcf.setDriver("驾驶员");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			dcf.setStartTime(sdf.parse("2013-10-31 17:30"));
			dcf.setEndTime(sdf.parse("2013-10-31 20:30"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dcf.setLine("从中国到日本。");
		dcf.setTarget("东京");
		dcf.setUser("武继明");
		dcf.setReason("没有理由，没有原因。");
		dispatchCarFormDao.save(dcf);
	}

	@Test
	public void find() {
		DispatchCarForm dcf=dispatchCarFormDao.findAgreeDispatchCarForm(262144L, new Date());
		System.out.println(dcf.getUser());
	}

}
