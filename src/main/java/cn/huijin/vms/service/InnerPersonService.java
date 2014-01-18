/**
 * 
 */
package cn.huijin.vms.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sylarlove.advance.exception.ExistedException;
import sylarlove.advance.model.main.User;
import cn.huijin.vms.dao.InnerPersonDao;
import cn.huijin.vms.model.CardType;
import cn.huijin.vms.model.InnerPerson;
import cn.huijin.vms.model.StatusType;

/**
 * 
 * @author 武继明
 * @since 2013年10月22日 下午3:43:47
 * 
 */
@Service
@Transactional
public class InnerPersonService implements IInnerPersonService {
	final static Logger logger = LoggerFactory
			.getLogger(InnerPersonService.class);
	@Inject
	private InnerPersonDao innerPersonDao;

	@Override
	public List<InnerPerson> list() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		return innerPersonDao.findByUsersId(user.getId());
	}

	@Override
	public void add(InnerPerson innerPerson) throws ExistedException {
		// 检查卡号是否重复，有重复则抛出异常
		InnerPerson exist = innerPersonDao.findByCardNumber(innerPerson
				.getCard().getNumber());
		if (exist != null) {
			throw new ExistedException("卡号已存在。");
		}
		// 检查证件号是否存在
		exist = innerPersonDao.findByCertificate(innerPerson.getCertificate());
		if (exist != null) {
			throw new ExistedException("证件号已存在。");
		}
		innerPerson.getCard().setCardType(CardType.PERSON);// 设置卡类型为人员卡
		innerPersonDao.save(innerPerson);
	}

	@Override
	public void delete(Long id) {
		innerPersonDao.delete(id);
	}

	@Override
	public InnerPerson getOne(Long id) {
		return innerPersonDao.findOne(id);
	}

	@Override
	public void update(InnerPerson innerPerson) {
		// 检查RFID是否重复，如果存在则抛出异常
		InnerPerson exist = innerPersonDao.findByCardNumberAndIdNot(innerPerson
				.getCard().getNumber(), innerPerson.getId());
		if (exist != null) {
			throw new ExistedException("RFID已存在");
		}
		exist = innerPersonDao.findByCertificateAndIdNot(
				innerPerson.getCertificate(), innerPerson.getId());
		if (exist != null) {
			throw new ExistedException("证件号已存在。");
		}
		innerPerson.getCard().setCardType(CardType.PERSON);// 设置卡类型为人员卡
		innerPersonDao.save(innerPerson);
	}

	@Override
	public void changeStatus(Long id) {
		InnerPerson innerPerson = innerPersonDao.findOne(id);
		if ("in".equals(innerPerson.getStatus())) {
			innerPerson.setStatus(StatusType.OUT);
		} else {
			innerPerson.setStatus(StatusType.IN);
		}

		innerPersonDao.save(innerPerson);
	}

}
