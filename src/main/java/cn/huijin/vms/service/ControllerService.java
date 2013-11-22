/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.huijin.vms.dao.ControllerDao;
import cn.huijin.vms.model.Controller;

/**
 * 
 * @author 武继明
 *  @since 2013年10月29日  下午8:47:48
 *
 */
@Service
@Transactional
public class ControllerService implements IControllerService{
	@Inject
	private ControllerDao controllerDao;

	@Override
	public List<Controller> getControllersByDoorId(Long doorId) {
		return controllerDao.findByDoorId(doorId);
	}

	@Override
	public Controller getOne(Long id) {
		return controllerDao.findOne(id);
	}

	@Override
	public List<Controller> list() {
		return controllerDao.findAll();
	}

}
