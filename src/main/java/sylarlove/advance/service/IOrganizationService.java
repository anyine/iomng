/**
 * 
 */
package sylarlove.advance.service;

import java.util.List;

import sylarlove.advance.exception.ExistedException;
import sylarlove.advance.model.main.Organization;

/**
 * @author 武继明
 *  @since 2013年10月16日  上午9:10:34
 *
 */

public interface IOrganizationService {
	/**
	 * 添加机构
	 * @param organizatioin
	 */
	public void add(Organization organizatioin) throws ExistedException;
	/**
	 * 获取根节点
	 * @return
	 */
	public Organization getRoot();
	/**
	 * @param parentId
	 */
	public List<Organization> getByParentId(Long parentId);
}
