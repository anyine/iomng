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
		Integer size=PageContext.getPageSize();//每页大小
		String sort=PageContext.getOrder();//顺序 asc  desc
		String order=PageContext.getSort();//排序列表
		Direction direction=Direction.ASC;
		if("desc".equalsIgnoreCase(sort)){
			direction=Direction.DESC;
		}
		//TODO 分页对象生成工具
		PageRequest pageRequest=new PageRequest(page, size,direction,"ss");
		return pageRequest;
	} 
}
