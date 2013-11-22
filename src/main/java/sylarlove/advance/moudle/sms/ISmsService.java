/**
 * 
 */
package sylarlove.advance.moudle.sms;

/**
 * 短信服务模块接口
 * @author 武继明
 *  @since 2013年10月31日  下午2:25:04
 *
 */
public interface ISmsService {
	/**
	 * 服务是否启动
	 * @return
	 */
	public boolean isStarted();
	
	/**
	 * 关闭服务
	 */
	public void closeService();
	/**
	 * 发送短信
	 * @param phoneNumber
	 * @param message
	 */
	public void sendMessage(String phoneNumber,String message);
	
}
