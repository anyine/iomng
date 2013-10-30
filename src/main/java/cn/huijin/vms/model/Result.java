/**
 * 
 */
package cn.huijin.vms.model;

import java.io.Serializable;

/**
 * 添加出入记录后的返回结果类
 * @author 武继明
 *  @since 2013年10月25日  上午10:25:01
 *
 */
public class Result implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 命令，open 开门，close 关门
	 */
	private String command="open";
	private String gateNumber;
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getGateNumber() {
		return gateNumber;
	}
	public void setGateNumber(String gateNumber) {
		this.gateNumber = gateNumber;
	}
	public Result(String command, String gateNumber) {
		super();
		this.command = command;
		this.gateNumber = gateNumber;
	}
	
	
}
