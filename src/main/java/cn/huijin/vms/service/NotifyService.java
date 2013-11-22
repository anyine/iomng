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
import cn.huijin.vms.dao.NotifyDao;
import cn.huijin.vms.model.Notify;

/**
 * 
 * @author 武继明
 *  @since 2013年11月17日  下午9:09:09
 *
 */
@Service
@Transactional
public class NotifyService implements INotifyService{
	@Inject
	private NotifyDao notifyDao;

	@Override
	public List<Notify> list() {
		return notifyDao.findAll(new Sort(Direction.ASC, "id"));
	}
	@Override
	public void add(Notify notify) {
		Notify exist=notifyDao.findByName(notify.getName());
		if(exist!=null){
			throw new ExistedException("通知策略已存在。");
		}
		notifyDao.save(notify);
	}
	@Override
	public Notify getOne(Long id) {
		return notifyDao.findOne(id);
	}
	@Override
	public void update(Notify notify) {
		//检查是否有重复的名字，有则抛出异常
		Notify exist=notifyDao.findByNameAndIdNot(notify.getName(),notify.getId());
		if(exist!=null){
			throw new ExistedException("名字已存在。");
		}
		notifyDao.save(notify);
	}
	@Override
	public void delete(Long id) {
		//检查类型个数，至少要保留一个类型
		Long count=notifyDao.count();
		if(count<=1){
			throw new ServiceException("不能再删了，这是最后一个了。");
		}
		notifyDao.delete(id);
	}
	
}
