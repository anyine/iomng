/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sylarlove.advance.exception.ExistedException;
import cn.huijin.vms.dao.DoorDao;
import cn.huijin.vms.model.Controller;
import cn.huijin.vms.model.Door;
import cn.huijin.vms.model.Reader;

/**
 * @author 武继明
 *  @since 2013年10月16日  下午12:08:20
 *
 */
@Service
@Transactional
public class DoorService implements IDoorService {
	@Inject
	private DoorDao doorDao;
	
	@Override
	public void add(Door door) {
		Door exist=doorDao.findByName(door.getName());
		if(exist!=null){
			throw new ExistedException("该门已存在。");
		}
		doorDao.save(door);
		//维护控制器和门的关系
		for(Controller c : door.getControllers()){
			c.setDoor(door);
			for(Reader r:c.getReaders()){
				r.setController(c);
			}
		}
	}
	@Override
	public void delete(Long id) {
		doorDao.delete(id);
	}
	@Override
	public List<Door> list() {
		return doorDao.findAll();
	}
	@Override
	public void update(Door door) {
		//维护控制器和门的关系
				for(Controller c : door.getControllers()){
					c.setDoor(door);
					for(Reader r:c.getReaders()){
						r.setController(c);
					}
				}
				doorDao.save(door);
	}
	@Override
	public Door getOne(Long id) {
		return doorDao.findOne(id);
	}

}
