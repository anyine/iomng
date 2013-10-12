/**
 * 
 */
package cn.huijin.vms.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import sylarlove.advance.exception.ExistException;
import cn.huijin.vms.dao.CarDao;
import cn.huijin.vms.model.Car;

/**
 * @author 武继明
 *  @since 2013年10月12日  上午9:48:24
 *
 */
@Service
public class CarService {
	@Inject
	private CarDao carDao;

	/**
	 * 添加车辆信息
	 * @param car
	 */
	public void add(Car car) {
		//检查车辆rfid是否重复，有重复则抛出异常
		Car exist =carDao.findByRfid(car.getRfid());
		if(exist!=null){
			throw new ExistException();
		}
		//检查车牌号码是否重复，有重复则抛出异常
		carDao.save(car);
	}
	
	
}
