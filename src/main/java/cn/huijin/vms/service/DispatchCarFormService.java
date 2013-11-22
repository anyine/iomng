/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.huijin.vms.dao.DispatchCarFormDao;
import cn.huijin.vms.model.DispatchCarForm;

/**
 * 
 * @author 武继明
 *  @since 2013年11月12日  下午4:03:41
 *
 */
@Service
@Transactional
public class DispatchCarFormService implements IDispatchCarFormService {
	final static Logger logger = LoggerFactory.getLogger(DispatchCarFormService.class);
	@Inject
	private DispatchCarFormDao dispatchCarFormDao;
	@Override
	public void add(DispatchCarForm dispatchCarForm) {
		dispatchCarFormDao.save(dispatchCarForm);
	}
	@Override
	public List<DispatchCarForm> list() {
		return dispatchCarFormDao.findAll();
	}


}
