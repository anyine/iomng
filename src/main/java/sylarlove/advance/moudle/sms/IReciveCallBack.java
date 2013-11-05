/**
 * 
 */
package sylarlove.advance.moudle.sms;

/**
 * 收到短信的回调
 * @author 武继明
 *  @since 2013年10月31日  下午6:18:36
 *
 */
public interface IReciveCallBack {
	/**
	 * 回调执行函数
	 * @param fromPhoneNumber 短信来源号码
	 * @param message 短信内容
	 */
	public void process(String fromPhoneNumber,String message);
}
