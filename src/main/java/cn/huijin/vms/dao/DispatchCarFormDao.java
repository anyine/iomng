/**
 * 
 */
package cn.huijin.vms.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.huijin.vms.model.DispatchCarForm;

/**
 * 派车单
 * @author 武继明
 *  @since 2013年10月31日  下午7:07:16
 *
 */
public interface DispatchCarFormDao extends JpaRepository<DispatchCarForm,Long>{
	@Query("select d from DispatchCarForm d where d.car.id=?1 and ?2 between d.startTime and d.endTime and d.agree=true")
	DispatchCarForm findAgreeDispatchCarForm(Long carId,Date date);
}
