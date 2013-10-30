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

import sylarlove.advance.exception.ExistedException;
import cn.huijin.vms.dao.InnerCarDao;
import cn.huijin.vms.model.CardType;
import cn.huijin.vms.model.InnerCar;
import cn.huijin.vms.model.StatusType;

/**
 * @author 武继明
 * @since 2013年10月12日 上午9:48:24
 * 
 */
@Service
@Transactional
public class InnerCarService implements IInnerCarService {
	final static Logger logger = LoggerFactory.getLogger(InnerCarService.class);
	@Inject
	private InnerCarDao innerCarDao;

	@Override
	public List<InnerCar> list() {

		return innerCarDao.findAll();
	}

	@Override
	public void add(InnerCar innerCar) throws ExistedException {
		// 检查车辆rfid是否重复，有重复则抛出异常
		InnerCar exist = innerCarDao.findByCardNumber(innerCar.getCard().getNumber());
		if (exist != null) {
			throw new ExistedException("卡号已存在。");
		}
		// 检查车牌号码是否重复，有重复则抛出异常
		exist = innerCarDao.findByLicense(innerCar.getLicense());
		if (exist != null) {
			throw new ExistedException("牌号已存在。");
		}
		innerCar.getCard().setCardType(CardType.CAR);//设置卡类型为车辆卡
		innerCarDao.save(innerCar);
	}

	@Override
	public void delete(Long id) {
		innerCarDao.delete(id);
	}

	@Override
	public InnerCar getOne(Long id) {
		return innerCarDao.findOne(id);
	}

	@Override
	public void update(InnerCar innerCar) {
		// 检查RFID是否重复，如果存在则抛出异常
		InnerCar exist = innerCarDao.findByCardNumberAndLicenseNot(innerCar.getCard().getNumber(), innerCar.getLicense());
		if (exist != null) {
			throw new ExistedException("RFID已存在");
		}
		innerCar.getCard().setCardType(CardType.CAR);//设置卡类型为车辆卡
		innerCarDao.save(innerCar);
	}

	@Override
	public void changeStatus(Long id) {
		InnerCar innerCar = innerCarDao.findOne(id);
		if ("in".equals(innerCar.getStatus())) {
			innerCar.setStatus(StatusType.OUT);
		} else {
			innerCar.setStatus(StatusType.IN);
		}

		innerCarDao.save(innerCar);
	}

	@Override
	public void changeLevel(Long id) {
		InnerCar innerCar = innerCarDao.findOne(id);
		if ("request".equals(innerCar.getLevel())) {
			innerCar.setLevel("direct");
		} else {
			innerCar.setLevel("request");
		}

		innerCarDao.save(innerCar);
	}


}
