/**
 * 
 */
package cn.huijin.vms.webservice;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

import javax.xml.transform.Source;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

/**
 * @author 武继明
 *  @since 2013年11月1日  上午12:48:40
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:applicationContext.xml"})   
public class RecordServiceEndpointTest {
	@Autowired
	private ApplicationContext applicationContext;
	@Test
	public void record(){
		MockWebServiceClient mockClient=MockWebServiceClient.createClient(applicationContext);
		Source requestPayload=new StringSource("<RecordRequest xmlns='"+RecordServiceEndpoint.NAME_SPACE+"'>"
				+ "<controllerSn>111111</controllerSn>"
				+ "<readerNumber>2</readerNumber>"
				+ "<cardNumber>123456</cardNumber>"
				+ "</RecordRequest>");
		Source responsePayload=new StringSource("<RecordResponse xmlns:rc='"+RecordServiceEndpoint.NAME_SPACE+"'>"
				+ "<command>open</command>"
				+ "<gateNumber>1</gateNumber>"
				+ "</RecordResponse>");
//		
		mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(responsePayload));
	}
}
