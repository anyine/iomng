/**
 * 
 */
package cn.huijin.vms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import sylarlove.advance.model.IdEntity;

/**
 * 读卡器
 * @author 武继明
 *  @since 2013年10月16日  上午10:48:04
 *
 */
@Entity
@Table(name="t_reader")
public class Reader extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="读卡器编号不能为空。")
	private String number;
	
	@Enumerated(EnumType.STRING)
	private StatusType type=StatusType.OUT;
	
	@Column(name="gate_number")
	private String gateNumber;
	
	@ManyToOne
	@JoinColumn(name="controle_id")
	private Controller controller;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}
	public StatusType getType() {
		return type;
	}
	public void setType(StatusType type) {
		this.type = type;
	}
	public String getGateNumber() {
		return gateNumber;
	}
	public void setGateNumber(String gateNumber) {
		this.gateNumber = gateNumber;
	}
	
	
}
