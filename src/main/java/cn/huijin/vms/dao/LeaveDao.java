/**
 * 
 */
package cn.huijin.vms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.huijin.vms.model.Leave;

/**
 * 请假条
 * @author 武继明
 *  @since 2013年11月13日  下午8:15:03
 *
 */
public interface LeaveDao extends JpaRepository<Leave,Long>{
	@Query("select l from Leave l where l.person.id=?1 and ?2 between l.startTime and l.endTime and l.agree=true ")
	Leave findAgreeLeave(Long personId,Date date);

	List<Leave> findBySimpleId(Long leaveId, Sort sort);
}
