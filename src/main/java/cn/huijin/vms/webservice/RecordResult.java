/**

 * 
 */

package cn.huijin.vms.webservice;

import java.io.Serializable;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 添加出入记录后的返回结果类
 * @author 武继明
 *  @since 2013年10月25日  上午10:25:01
 *
 */

@XmlRootElement(name="RecordResponse")
public class RecordResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 命令，open 开门，close 关门, none 没有动作
	 */
	private String command="open";
	private String gateNumber;
	@XmlElement
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	@XmlElement
	public String getGateNumber() {
		return gateNumber;
	}
	public void setGateNumber(String gateNumber) {
		this.gateNumber = gateNumber;
	}
	public RecordResult(String command, String gateNumber) {
		super();
		this.command = command;
		this.gateNumber = gateNumber;
	}
	public RecordResult() {
		super();
	}
}
