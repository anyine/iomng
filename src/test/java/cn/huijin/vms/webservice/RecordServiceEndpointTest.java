/**
 * 
 */
package cn.huijin.vms.webservice;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

import java.io.StringWriter;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import cn.huijin.vms.model.Controller;

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
	
	@Test
	public void  getControllerList(){
//		JAXBContext context = null;
//		try {
//			context=JAXBContext.newInstance(ControllerList.class);
//			Marshaller mar=context.createMarshaller();
//			ControllerList list=new ControllerList();
//			Controller c1=new Controller();
//			c1.setSn("1111");
//			c1.setIp("192.168.1.1");
//			Controller c2=new Controller();
//			c2.setSn("222222");
//			c2.setIp("192.168.1.2");
//			list.setControllers(Arrays.asList(c1,c2));
//			StringWriter writer = new StringWriter();
//			mar.marshal(list, writer);
//			System.out.println(writer);
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}
		
		MockWebServiceClient mockClient=MockWebServiceClient.createClient(applicationContext);
		Source requestPayload=new StringSource("<ControllerListRequest xmlns='"+RecordServiceEndpoint.NAME_SPACE+"'>"
				+ "</ControllerListRequest>");
		Source responsePayload=new StringSource("<ControllerListResponse xmlns:rc='"+RecordServiceEndpoint.NAME_SPACE+"'>"
				+ "<controller><sn>1111</sn><ip>192.168.1.1</ip></controller>"
				+ "<controller><sn>222222</sn><ip>192.168.1.2</ip></controller>"
				+ "</ControllerListResponse>");
//		
		mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(responsePayload));
	}
}
