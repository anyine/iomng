/**
 * 
 */
package cn.huijin.vms.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import sylarlove.advance.model.PageContext;

/**
 * @author 武继明
 *  @since 2013年10月16日  下午6:13:16
 *
 */
public class PageUtil {
	public static PageRequest getPageable(){
		
		Integer page=PageContext.getCurrentPage();//页数
		if(page==null)page=1;
		Integer size=PageContext.getPageSize();//每页大小
		if(size==null)size=20;//默认页大小
		String order="id";//排序列表
		Direction direction=Direction.DESC;
		
		PageRequest pageRequest=new PageRequest(page, size, direction,order);
		return pageRequest;
	} 
}
