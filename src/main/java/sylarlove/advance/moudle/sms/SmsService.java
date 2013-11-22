/**
 * 
 */
package sylarlove.advance.moudle.sms;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.IInboundMessageNotification;
import org.smslib.IOutboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message.MessageEncodings;
import org.smslib.Message.MessageTypes;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.helper.CommPortIdentifier;
import org.smslib.helper.SerialPort;
import org.smslib.modem.SerialModemGateway;

/**
 * @author 武继明
 * @since 2013年10月31日 下午2:58:52
 * 
 */
public class SmsService implements ISmsService {
	static final Logger logger = LoggerFactory.getLogger(SmsService.class);
	private List<IReciveCallBack> reciveCallBacks=new ArrayList<IReciveCallBack>();
	private List<ISendCallBack> sendCallBacks=new ArrayList<ISendCallBack>();
	private SerialModemGateway gateway;
	static boolean started = false;

	private class ComAndBaud {
		private String com;
		private Integer baud;

		public ComAndBaud(String com, Integer baud) {
			this.com = com;
			this.baud = baud;
		}

		public String getCom() {
			return com;
		}

		public Integer getBaud() {
			return baud;
		}
	}

	public SmsService(String smscNumber, String simPin) {
		if(this.isStarted()){//如果启动了则不在启动
			return;
		}
		ComAndBaud cab = this.getCom();
		if (cab == null) {
			logger.info("没有找到短信猫设备");
			return;
		}
		gateway = new SerialModemGateway("短信猫", cab.getCom(), cab.getBaud(),
				"Huawei", "");
		 gateway.setInbound(true);
		 gateway.setOutbound(true);
		gateway.setSimPin(simPin);
		gateway.setSmscNumber("+86" + smscNumber);
		Service.getInstance().setInboundMessageNotification(new IInboundMessageNotification() {
			//接收信息回调
			@Override
			public void process(AGateway gateway, MessageTypes messageTypes, InboundMessage message) {
				if(!reciveCallBacks.isEmpty()){
					for(IReciveCallBack callBack: reciveCallBacks){
						callBack.process(message.getOriginator(), message.getText());
					}
				}
			}
		});
		Service.getInstance().setOutboundMessageNotification(new IOutboundMessageNotification() {
			//发送回调
			@Override
			public void process(AGateway gateway, OutboundMessage message) {
				if(!sendCallBacks.isEmpty()){
					for(ISendCallBack callBack: sendCallBacks){
						callBack.process(message.getFrom(), message.getText());
					}
				}
			}
		});
		try {
			Service.getInstance().addGateway(gateway);
			Service.getInstance().startService();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SmsService.started = true;
	}

	/**
	 * 自动获取连接设备的串口信息
	 * 
	 * @return
	 */
	private ComAndBaud getCom() {
		ComAndBaud result = null;
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier
				.getPortIdentifiers();
		int bauds[] = { 9600, 14400, 19200, 28800, 33600, 38400, 56000, 57600,
				115200 };
		CommPortIdentifier portId = null;
		while (portList.hasMoreElements()) {
			portId = portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				for (int i = 0; i < bauds.length; i++) {
					SerialPort serialPort = null;
					logger.info("检测串口：{},{}", portId.getName(), bauds[i]);
					try {
						InputStream inStream;
						OutputStream outStream;
						int c;
						String response;
						serialPort = portId.open("SMSLibCommTester", 1971);
						serialPort
								.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
						serialPort.setSerialPortParams(bauds[i],
								SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
								SerialPort.PARITY_NONE);
						inStream = serialPort.getInputStream();
						outStream = serialPort.getOutputStream();
						serialPort.enableReceiveTimeout(1000);
						c = inStream.read();
						while (c != -1)
							c = inStream.read();
						outStream.write('A');
						outStream.write('T');
						outStream.write('\r');
						Thread.sleep(1000);
						response = "";
						StringBuilder sb = new StringBuilder();
						c = inStream.read();
						while (c != -1) {
							sb.append((char) c);
							c = inStream.read();
						}
						response = sb.toString();
						if (response.indexOf("OK") >= 0) {
							logger.info("发现短信猫设备，正在获取配置信息");
							try {
								outStream.write('A');
								outStream.write('T');
								outStream.write('+');
								outStream.write('C');
								outStream.write('G');
								outStream.write('M');
								outStream.write('M');
								outStream.write('\r');
								response = "";
								c = inStream.read();
								while (c != -1) {
									response += (char) c;
									c = inStream.read();
								}
								result = new ComAndBaud(portId.getName(),
										bauds[i]);
								logger.info(" 设备信息: "
										+ response.replaceAll("\\s+OK\\s+", "")
												.replaceAll("\n", "")
												.replaceAll("\r", ""));
								break;
							} catch (Exception e) {
								logger.info("没有发现短信猫设备");
							}
						} else {
							logger.info("没有发现短信猫设备");
						}
					} catch (Exception e) {
						logger.info("没有发现短信猫设备");
						Throwable cause = e;
						while (cause.getCause() != null) {
							cause = cause.getCause();
						}
						logger.info("附加原因：" + cause.getMessage());
					} finally {
						if (serialPort != null) {
							serialPort.close();
						}
					}
				}
			}

		}
		return result;
	}

	@Override
	public void sendMessage(String phoneNumber, String message) {
		logger.info("发送短信：to:{} ,msg: {}",phoneNumber,message);
		if (!SmsService.started) {
			logger.info("短信服务没有启动。");
			return;
		}
		OutboundMessage msg = new OutboundMessage("+86" + phoneNumber, message);
		msg.setEncoding(MessageEncodings.ENCUCS2);
		Service.getInstance().queueMessage(msg, gateway.getGatewayId());
	}

	@Override
	public boolean isStarted() {
		return started;
	}

	@Override
	public void closeService() {
		try {
			Service.getInstance().stopService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<IReciveCallBack> getReciveCallBacks() {
		return reciveCallBacks;
	}

	public void setReciveCallBacks(List<IReciveCallBack> reciveCallBacks) {
		this.reciveCallBacks = reciveCallBacks;
	}

	public List<ISendCallBack> getSendCallBacks() {
		return sendCallBacks;
	}

	public void setSendCallBacks(List<ISendCallBack> sendCallBacks) {
		this.sendCallBacks = sendCallBacks;
	}
	
}
