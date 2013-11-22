/**
 * 
 */
package cn.huijin.vms.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sylarlove.advance.model.main.User;
import sylarlove.advance.moudle.sms.ISmsService;
import cn.huijin.vms.dao.CardDao;
import cn.huijin.vms.dao.InnerCarDao;
import cn.huijin.vms.dao.InnerCarRecordDao;
import cn.huijin.vms.dao.InnerPersonDao;
import cn.huijin.vms.dao.InnerPersonRecordDao;
import cn.huijin.vms.dao.LeaveDao;
import cn.huijin.vms.dao.ReaderDao;
import cn.huijin.vms.model.Card;
import cn.huijin.vms.model.InnerCar;
import cn.huijin.vms.model.InnerCarRecord;
import cn.huijin.vms.model.InnerPerson;
import cn.huijin.vms.model.InnerPersonRecord;
import cn.huijin.vms.model.Leave;
import cn.huijin.vms.model.Notify;
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
	private ISmsService smsService;
	@Inject
	private LeaveDao leaveDao;

	@Override
	public RecordResult add(String controllerSn, String readerNumber,
			String number) {
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
			result = new RecordResult("none", "0");
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
		// 在通知段时间内，没有申请或者申请未通过的向其请假人发送短信通知
		Leave leave = leaveDao.findAgreeLeave(person.getId(), new Date());
		innerPersonRecordDao.save(record);
		// 执行通知策略

		if (leave == null && isNotifTime(person.getNotify())) {
			record.setStatus("warning");// 标记警告记录
			String type = reader.getType() == StatusType.IN ? "进入" : "外出";
			String message = person.getName() + " 违规" + type + ",记录编号："
					+ record.getId() + "，系统已将该条记录标记为警告，" + "可回复此编号取消警告，也可不回复。";
			for (User user : person.getUsers()) {
				if (user.getPhone() != null) {
					smsService.sendMessage(user.getPhone(), message);
				} else {
					logger.warn("用户：[{}]没有绑定手机号码。", user.getRealname());
				}
			}
		}
		// 修改人员状态
		person.setStatus(reader.getType());
		innerPersonDao.save(person);
		RecordResult result = new RecordResult("none", "0");// 人员返回结果
		return result;
	}

	/**
	 * 判断此时是否在通知时间段内
	 * 
	 * @param notify
	 * @return
	 */
	private boolean isNotifTime(Notify notify) {
		if (notify == null) {
			return false;
		} else {
			Calendar currentTime = Calendar.getInstance();
			// 将时间转换为同一天
			Date startTime = DateUtils.setDays(DateUtils.setMonths(
					DateUtils.setYears(notify.getStartTime(),
							currentTime.get(Calendar.YEAR)),
					currentTime.get(Calendar.MONTH)), currentTime
					.get(Calendar.DAY_OF_MONTH));
			Date endTime = DateUtils.setDays(DateUtils.setMonths(
					DateUtils.setYears(notify.getEndTime(),
							currentTime.get(Calendar.YEAR)),
					currentTime.get(Calendar.MONTH)), currentTime
					.get(Calendar.DAY_OF_MONTH));
			// 起始时间比截止时间大为同一天,否则为隔天
			if (startTime.before(endTime)) {
				// 同一天
				return currentTime.getTime().after(startTime)
						&& currentTime.getTime().before(endTime);
			} else {
				// 隔天
				return currentTime.getTime().after(startTime)
						&& currentTime.getTime().before(DateUtils.addDays(endTime, 1));
			}
		}
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
		// 再通知时间段向车辆负责人发送短信通知
		if (isNotifTime(car.getNotify())) {
			User user = car.getUser();
			if (user != null) {
				if (user.getPhone() != null) {
					String type = reader.getType() == StatusType.IN ? "进入"
							: "外出";
					String message = "车牌号为"
							+ car.getLicense()
							+ "的车辆"
							+ type
							+ ",记录编号："
							+ record.getId()
							+ "。时间："
							+ DateFormatUtils.format(record.getDate(),
									"yyyy-MM-dd HH:mm:ss");
					smsService.sendMessage(car.getUser().getPhone(), message);
				} else {
					logger.warn("用户：【{}】 没有绑定手机号码。", user.getRealname());
				}
			} else {
				logger.warn("车辆：【{}】 没有绑定负责人。", car.getLicense());
			}
		}
		// 修改车辆状态
		car.setStatus(reader.getType());
		innerCarDao.save(car);
		// 内部管理车辆全部放行，干部私家车外出不放行
		//干部私家车外出不放行
		String command="open";
		if("request".equals(car.getLevel())&&reader.getType()==StatusType.OUT){
			command="none";
		}
		RecordResult result = new RecordResult(command, reader.getGateNumber());
		return result;
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
