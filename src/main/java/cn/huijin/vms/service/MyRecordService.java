/**
 * 
 */
package cn.huijin.vms.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sylarlove.advance.moudle.sms.ISmsService;
import cn.huijin.vms.dao.CardDao;
import cn.huijin.vms.dao.DispatchCarFormDao;
import cn.huijin.vms.dao.InnerCarDao;
import cn.huijin.vms.dao.InnerCarRecordDao;
import cn.huijin.vms.dao.InnerPersonDao;
import cn.huijin.vms.dao.InnerPersonRecordDao;
import cn.huijin.vms.dao.ReaderDao;
import cn.huijin.vms.model.Card;
import cn.huijin.vms.model.DispatchCarForm;
import cn.huijin.vms.model.InnerCar;
import cn.huijin.vms.model.InnerCarRecord;
import cn.huijin.vms.model.InnerPerson;
import cn.huijin.vms.model.InnerPersonRecord;
import cn.huijin.vms.model.Reader;
import cn.huijin.vms.model.StatusType;
import cn.huijin.vms.webservice.RecordResult;

/**
 * @author 武继明
 * @since 2013年10月25日 上午9:47:41
 */
@Service
@Transactional
public class MyRecordService implements IRecordService {
	static final Logger logger = LoggerFactory.getLogger(MyRecordService.class);
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
	@Inject
	private DispatchCarFormDao dispatchCarFormDao;
	@Inject
	private ISmsService smsService;

	@Override
	public RecordResult add(String controllerSn, String readerNumber, String number) {
		logger.debug("controllerSn:{} ,readerNumber:{} ,number:{} ",
				controllerSn, readerNumber, number);
		RecordResult result = null;
		// 读卡器
		Reader reader = readerDao.findByControllerSnAndNumber(controllerSn,
				readerNumber);
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
			result = new RecordResult("close", reader.getGateNumber());
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
	private RecordResult addInnerPersonRecord(InnerPerson person, Reader reader) {
		InnerPersonRecord record = new InnerPersonRecord(reader.getType(),
				person, reader.getController().getDoor());
		// TODO 查询请假申请
		// TODO 执行通知策略
		innerPersonRecordDao.save(record);
		// 修改人员状态
		person.setStatus(reader.getType());
		innerPersonDao.save(person);
		RecordResult result = new RecordResult("none", "0");// 人员返回结果
		return result;
	}

	/**
	 * 内部车辆出入记录
	 * 
	 * @param car
	 * @param reader
	 * @return
	 */
	private RecordResult addInnerCarRecord(InnerCar car, Reader reader) {
		InnerCarRecord record = new InnerCarRecord(reader.getType(), car,
				reader.getController().getDoor());
		innerCarRecordDao.save(record);
		// 执行通知策略
		sendNotify(car, reader, record);
		// 修改车辆状态
		car.setStatus(reader.getType());
		innerCarDao.save(car);
		// 当前 所有车辆都抬杆放行
		RecordResult result = new RecordResult("open", reader.getGateNumber());
		return result;
	}

	/**
	 * 执行通知策略
	 * @param car
	 * @param reader
	 */
	private void sendNotify(InnerCar car, Reader reader, InnerCarRecord record) {
		logger.info("执行通知...");
		// 查询派车申请
		DispatchCarForm dcf = dispatchCarFormDao.findByCarIdAndDate(car.getId(), new Date());
		if (dcf != null) {
			// 未通过
			if (!dcf.getAgree()) {
				logger.info("警告记录:carId:{},readerId{}", car.getId(),reader.getId());
				record.setStatus("warning");// 标记警告记录
				// 发短信
				if (smsService != null) {
					String phoneNumber = car.getUser().getPhone();
					if (StringUtils.isBlank(phoneNumber)) {
						String type = reader.getType() == StatusType.IN ? "进入": "外出";
						String message = "车牌号为 " + car.getLicense() + " 的车辆"
								+ type + ",系统已将该记录标记为警告，可登陆系统通过编号:"
								+ record.getId() + " 查询详细信息。";
						smsService.sendMessage(phoneNumber, message);
					}
				}
			}
		}
	}

	@Override
	public List<InnerCarRecord> listInnerCarRecord() {
		return innerCarRecordDao.findAll();
	}

	@Override
	public List<InnerPersonRecord> listInnerPersonRecord() {
		return innerPersonRecordDao.findAll();
	}
}
