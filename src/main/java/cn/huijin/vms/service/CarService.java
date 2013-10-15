/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sylarlove.advance.exception.ExistedException;
import cn.huijin.vms.dao.CarDao;
import cn.huijin.vms.model.Car;

/**
 * @author 武继明
 *  @since 2013年10月12日  上午9:48:24
 *
 */
@Service
@Transactional
public class CarService implements ICarService {
	@Inject
	private CarDao carDao;
	
	@Override
	public List<Car> list(){
		
		return carDao.findAll();
	}
	@Override
	public void add(Car car) throws ExistedException {
		//检查车辆rfid是否重复，有重复则抛出异常
		Car exist =carDao.findByRfid(car.getRfid());
		if(exist!=null){
			throw new ExistedException("RFID已存在。");
		}
		//检查车牌号码是否重复，有重复则抛出异常
		exist =carDao.findByLicense(car.getLicense());
		if(exist!=null){
			throw new ExistedException("牌号已存在。");
		}
		carDao.save(car);
	}
	@Override
	public void delete(Long id) {
		carDao.delete(id);
	}
	@Override
	public Car getOne(Long id) {
		return carDao.findOne(id);
	}
	@Override
	public void update(Car car) {
		//检查RFID是否重复，如果存在则抛出异常
		Car exist=carDao.findByRfidAndLicenseNot(car.getRfid(), car.getLicense());
		if(exist!=null){
			throw new ExistedException("RFID已存在");
		}
		carDao.save(car);
	}
	@Override
	public void changeStatus(Long id) {
		Car car=carDao.findOne(id);
		if("in".equals(car.getStatus())){
			car.setStatus("out");
		} else{
			car.setStatus("in");
		}
		
		carDao.save(car);
	}
	
}
