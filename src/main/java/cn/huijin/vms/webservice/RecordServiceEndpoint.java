/**
 * 
 */
package cn.huijin.vms.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.Namespace;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;

import com.mchange.v2.sql.filter.RecreatePackage;

/**
 * 出入记录webservice端点
 * @author 武继明
 *  @since 2013年10月31日  下午10:02:18
 *
 */
@Endpoint
public class RecordServiceEndpoint {
	static final Logger logger=LoggerFactory.getLogger(RecordServiceEndpoint.class);
	/*@Inject
	private IRecordService recordService;*/
	
	public static final String  NAME_SPACE="http://www.sdhuijin.cn/vms/ws/RecordServcie";
	
	@PayloadRoot(localPart="RecordRequest",namespace=NAME_SPACE)
	@Namespace(prefix="rc",uri=NAME_SPACE)
	@ResponsePayload
	public RecordResult record(@XPathParam("//rc:controllerSn")String controllerSn,@XPathParam("//rc:readerNumber")String readerNumber,@XPathParam("//rc:cardNumber")String cardNumber){
		logger.info("controllerSn:{} readerNumber:{} cardNumber:{}",controllerSn,readerNumber,cardNumber);
		//RecordResult recordResult= recordService.add(controllerSn, readerNumber, cardNumber);
		RecordResult recordReponse=new RecordResult("open", "1");
		return recordReponse;
	}
}
