/**
 * 
 */
package sylarlove.advance.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.huijin.vms.util.OrgUtils;
import sylarlove.advance.dao.OrganizationDao;
import sylarlove.advance.exception.ExistedException;
import sylarlove.advance.model.main.Organization;
import sylarlove.advance.model.main.User;

/**
 * @author 武继明
 * @since 2013年10月16日 上午9:12:21
 * 
 */
@Service
@Transactional
public class OrganizationService implements IOrganizationService {
	@Inject
	private OrganizationDao organizationDao;

	@Override
	public void add(Organization organization) {

		Organization exist = null;
		if (organization.getParent() != null) {
			exist = organizationDao.findByParentIdAndName(organization
					.getParent().getId(), organization.getName());
		}
		if (exist != null) {
			throw new ExistedException("机构已存在。");
		}
		organizationDao.save(organization);
	}

	@Override
	public Organization getRoot() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		return organizationDao.findOne(user.getOrganization().getId());
	}

	@Override
	public List<Organization> getByParentId(Long parentId) {
		parentId = parentId == null ? 0L : parentId;
		return organizationDao.findByParentId(parentId);
	}

	@Override
	public void update(Organization organization) {
		Organization exist = organizationDao.findByParentIdAndNameAndIdNot(
				organization.getParent().getId(), organization.getName(),
				organization.getId());
		if (exist != null) {
			throw new ExistedException("机构已存在。");
		}
		Organization oldOrganization = organizationDao.findOne(organization
				.getId());
		// 只改index 序号， 机构名称
		oldOrganization.setIndex(organization.getIndex());
		oldOrganization.setName(organization.getName());
		organizationDao.save(oldOrganization);
	}

	@Override
	public void delete(Long id) {
		organizationDao.delete(id);
	}

}
