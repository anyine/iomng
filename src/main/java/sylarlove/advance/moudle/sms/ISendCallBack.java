/**
 * 
 */
package sylarlove.advance.moudle.sms;

/**
 * 发送短信后的回调函数
 * @author 武继明
 *  @since 2013年10月31日  下午6:17:54
 *
 */
public interface ISendCallBack {
	/**
	 * 回调调用函数
	 * @param toPhoneNumber 目标手机号码
	 * @param message 发送信息内容
	 */
	public void process(String toPhoneNumber,String message);
}
