/**
 * 
 */
package cn.huijin.vms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import cn.huijin.vms.webservice.RecordServiceEndpoint;
import sylarlove.advance.model.IdEntity;

/**
 * 控制器
 * @author 武继明
 *  @since 2013年10月16日  上午10:46:53
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name="t_controller")
public class Controller extends IdEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(namespace=RecordServiceEndpoint.NAME_SPACE)
	@NotEmpty(message="sn不能为空。")
	@Length(max=32,message="sn长度最大为{max}")
	@Column(length=32,unique=true)
	private String sn;
	
	//TODO ip地址验证
	@XmlElement(namespace=RecordServiceEndpoint.NAME_SPACE)
	@NotEmpty(message="ip地址不能为空。")
	@Column(unique=true)
	private String ip;
	
	@NotEmpty(message="读卡器不能为空。")
	@OneToMany(mappedBy="controller",cascade={CascadeType.ALL},orphanRemoval=true)
	private List<Reader> readers=new ArrayList<Reader>();
	
	@ManyToOne
	@JoinColumn(name="door_id")
	private Door door;
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public List<Reader> getReaders() {
		return readers;
	}
	public void setReaders(List<Reader> readers) {
		this.readers = readers;
	}
	public Door getDoor() {
		return door;
	}
	public void setDoor(Door door) {
		this.door = door;
	}
	@Override
	@XmlElement(namespace=RecordServiceEndpoint.NAME_SPACE)
	public Long getId() {
		return super.getId();
	}
	
}
