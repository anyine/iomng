package sylarlove.advance.moudle.sms.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.Service;
import org.smslib.Service.ServiceStatus;

/**
 * 短信服务生命周期监听器
 * @author 武继明
 *  @since 2013年10月31日  下午6:52:31
 *
 */
public class SmsLifecycleListener implements ServletContextListener {
	final static Logger logger=LoggerFactory.getLogger(SmsLifecycleListener.class);

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
    	logger.info("短信服务生命周期监听器初始化。");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
    	ServiceStatus status=Service.getInstance().getServiceStatus();
    	logger.info("短信服务状态："+status);
		if ( status== ServiceStatus.STARTED||status == ServiceStatus.STARTING) {
			logger.info("正在结束短信服务...");
			try {
				Service.getInstance().stopService();
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("短信猫服务已结束。");
		}
    }
	
}
