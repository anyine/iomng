/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sylarlove.advance.exception.ExistedException;
import sylarlove.advance.exception.ServiceException;
import cn.huijin.vms.dao.CarTypeDao;
import cn.huijin.vms.model.CarType;

/**
 * @author 武继明
 *  @since 2013年10月12日  下午1:32:54
 *
 */
@Service
@Transactional
public class CarTypeService implements ICarTypeService{
	@Inject
	private CarTypeDao carTypeDao;

	@Override
	public List<CarType> list() {
		return carTypeDao.findAll(new Sort(Direction.ASC, "id"));
	}
	@Override
	public void add(CarType carType) {
		CarType exist=carTypeDao.findByName(carType.getName());
		if(exist!=null){
			throw new ExistedException("车辆类型已存在。");
		}
		carTypeDao.save(carType);
	}
	@Override
	public CarType getOne(Long id) {
		return carTypeDao.findOne(id);
	}
	@Override
	public void update(CarType carType) {
		//检查是否有重复的名字，有则抛出异常
		CarType exist=carTypeDao.findByNameAndIdNot(carType.getName(),carType.getId());
		if(exist!=null){
			throw new ExistedException("车辆类型已存在。");
		}
		carTypeDao.save(carType);
	}
	@Override
	public void delete(Long id) {
		//检查类型个数，至少要保留一个类型
		Long count=carTypeDao.count();
		if(count<=1){
			throw new ServiceException("不能再删了，这是最后一个类型了。");
		}
		carTypeDao.delete(id);
	}
	
}
