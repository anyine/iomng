/**
 * 
 */
package cn.huijin.vms.webservice;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import cn.huijin.vms.model.Controller;

/**
 * @author 武继明
 *  @since 2013年11月16日  下午2:00:30
 *
 */
@XmlRootElement(name="GetControllerListResponse")
public class ControllerList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Controller> controllers;
	  @XmlElements({
	        @XmlElement(name = "Controller", type = Controller.class,namespace = RecordServiceEndpoint.NAME_SPACE)
	    })
	public List<Controller> getControllers() {
		return controllers;
	}
	public void setControllers(List<Controller> controllers) {
		this.controllers = controllers;
	}
	
	
}
