/**
 * 
 */
package cn.huijin.vms.webservice;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.Namespace;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;

import cn.huijin.vms.service.IControllerService;
import cn.huijin.vms.service.IRecordService;

/**
 * 出入记录webservice端点
 * 
 * @author 武继明
 * @since 2013年10月31日 下午10:02:18
 * 
 */
//@Endpoint
public class TestRecordServiceEndpoint {
	static final Logger logger = LoggerFactory
			.getLogger(TestRecordServiceEndpoint.class);
	@Inject
	private IRecordService recordService;
	@Inject
	private IControllerService controllerService;

	public static final String NAME_SPACE = "http://www.sdhuijin.cn/record/schema/v1";

	@PayloadRoot(localPart = "RecordRequest", namespace = NAME_SPACE)
	@Namespace(prefix = "rc", uri = NAME_SPACE)
	@ResponsePayload
	public RecordResult record(
			@XPathParam("/rc:requestRequest/@controllerSn") String controllerSn,
			@XPathParam("/rc:requestRequest/@readerNumber") String readerNumber,
			@XPathParam("/rc:requestRequest/@cardNumber") String cardNumber) {
		logger.info("controllerSn:{} readerNumber:{} cardNumber:{}",
				controllerSn, readerNumber, cardNumber);
		RecordResult recordResult=new RecordResult("open", "1");
		return recordResult;
	}

	@PayloadRoot(localPart = "ControllerListRequest", namespace = NAME_SPACE)
	@Namespace(prefix = "tns", uri = NAME_SPACE)
	@ResponsePayload
	public ControllerList getControllerList() {
		logger.info("getControllerList...");
		 ControllerList list=new ControllerList();
		 list.setControllers(controllerService.list());
		return list;
	}

}
