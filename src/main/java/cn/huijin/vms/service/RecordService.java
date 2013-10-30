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

import cn.huijin.vms.dao.CardDao;
import cn.huijin.vms.dao.InnerCarDao;
import cn.huijin.vms.dao.InnerCarRecordDao;
import cn.huijin.vms.dao.InnerPersonDao;
import cn.huijin.vms.dao.InnerPersonRecordDao;
import cn.huijin.vms.dao.ReaderDao;
import cn.huijin.vms.model.Card;
import cn.huijin.vms.model.InnerCar;
import cn.huijin.vms.model.InnerCarRecord;
import cn.huijin.vms.model.InnerPerson;
import cn.huijin.vms.model.InnerPersonRecord;
import cn.huijin.vms.model.Reader;
import cn.huijin.vms.model.Result;

/**
 * @author 武继明
 * @since 2013年10月25日 上午9:47:41
 */
@Service
@Transactional
public class RecordService implements IRecordService {
	static final Logger logger = LoggerFactory.getLogger(RecordService.class);
	@Inject
	private ReaderDao readerDao;
	@Inject
	private CardDao cardDao;
	@Inject
	private InnerCarDao innerCarDao;
	@Inject
	private InnerPersonDao innerPersonDao;
	@Inject
	private InnerCarRecordDao innerCarRecordDao;
	@Inject
	private InnerPersonRecordDao innerPersonRecordDao;

	@Override
	public Result add(String controllerSn, String readerNumber, String number) {
		logger.debug("controllerSn:{} ,readerNumber:{} ,number:{} ",	controllerSn, readerNumber, number);
		Result result = null;
		// 读卡器
		Reader reader = readerDao.findByControllerSnAndNumber(controllerSn,readerNumber);
		// 卡
		Card card = cardDao.findByNumber(number);
		if (card != null) {
			logger.debug("carType:{}", card.getCardType());
			switch (card.getCardType()) {
			case CAR:// 内部车辆卡
				InnerCar car = innerCarDao.findByCardNumber(card.getNumber());
				result = addInnerCarRecord(car, reader);
				break;
			case PERSON:// 内部人员卡
				InnerPerson person = innerPersonDao.findByCardNumber(card
						.getNumber());
				result = addInnerPersonRecord(person, reader);
				break;
			default:
				logger.info("未支持的实现。");
				break;
			}
		} else {
			logger.debug("未注册的卡号：{}", number);
			result=new Result("close", reader.getGateNumber());
		}

		// 保存记录
		return result;
	}

	/**
	 * 内部人员出入记录
	 * 
	 * @param person
	 * @param reader
	 * @return
	 */
	private Result addInnerPersonRecord(InnerPerson person, Reader reader) {
		InnerPersonRecord record=new InnerPersonRecord(reader.getType(),person,reader.getController().getDoor());
		// TODO 查询请假申请
		//TODO 执行通知策略
		innerPersonRecordDao.save(record);
		//修改人员状态
		person.setStatus(reader.getType());
		innerPersonDao.save(person);
		Result result=new Result("close", "0");//人员返回结果
		return result;
	}

	/**
	 * 内部车辆出入记录
	 * 
	 * @param car
	 * @param reader
	 * @return
	 */
	private Result addInnerCarRecord(InnerCar car, Reader reader) {
		//TODO 查询派车申请
		//TODO 执行通知策略
		InnerCarRecord record = new InnerCarRecord(reader.getType(), car, reader.getController().getDoor());
		innerCarRecordDao.save(record);
		//修改车辆状态
		car.setStatus(reader.getType());
		innerCarDao.save(car);

		Result result = new Result("open", reader.getGateNumber());
		return result;
	}

	@Override
	public List<InnerCarRecord> listInnerCarRecord() {
		return innerCarRecordDao.findAll();
	}
}
