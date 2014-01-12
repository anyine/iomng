/**
 * 
 */
package sylarlove.advance.model;


/**
 * 分页上下文，从该类中获取分页数据。
 * 
 * @author 武继明
 * @since 2013年10月16日 下午6:05:54
 * 
 */
public class PageContext {
	/**
	 * 当前页
	 */
	private static ThreadLocal<Integer> currentPage = new ThreadLocal<Integer>();
	/**
	 * 分页的大小
	 */
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	/**
	 * 分页的起始页
	 */
	private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();
	/**
	 * 列表的排序方式，asc,desc
	 */
	private static ThreadLocal<String> sort = new ThreadLocal<String>();

	/**
	 * 列表的排序字段,多个字段用逗号分开
	 */
	private static ThreadLocal<String> order = new ThreadLocal<String>();
	/**
	 * 获取当前页
	 * @return
	 */
	public static Integer getCurrentPage() {
		return currentPage.get();
	}
	
	public static void setCurrentPage(Integer currentPage) {
		PageContext.currentPage.set(currentPage);
	}
	/**
	 * 获取页大小
	 * @return
	 */
	public static Integer getPageSize() {
		return pageSize.get();
	}

	public static void setPageSize(Integer pageSize) {
		PageContext.pageSize.set(pageSize);
	}
	/**
	 * 获取数据偏移量
	 * @return
	 */
	public static Integer getPageOffset() {
		return pageOffset.get();
	}

	public static void setPageOffset(Integer pageOffset) {
		PageContext.pageOffset.set(pageOffset);
	}
	/**
	 * 获取排序字段
	 * @return
	 */
	public static String getSort() {
		return sort.get();
	}

	public static void setSort(String sort) {
		PageContext.sort.set(sort);
	}
/**
 * 获取排序顺序
 * @return
 */
	public static String getOrder() {
		return order.get();
	}

	public static void setOrder(String order) {
		PageContext.order.set(order);
	}

	public static void removeCurrentPage() {
		PageContext.currentPage.remove();
	}

	public static void removePageSize() {
		PageContext.pageSize.remove();
	}

	public static void removePageOffset() {
		PageContext.pageOffset.remove();
	}

	public static void removeOrder() {
		PageContext.order.remove();
	}

	public static void removeSort() {
		PageContext.sort.remove();
	}
	public static void removeAll(){
		removeCurrentPage();
		removeOrder();
		removePageOffset();
		removePageSize();
		removeSort();
	}
}
