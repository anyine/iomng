/**
 * 
 */
package sylarlove.advance.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 统一的model基类
 * 
 * 统一定义id属性名称呼，数据类型，列明映射及生成策略。
 * 子类可以重载getId()函数冲定义id的列明映射和生成策略。
 * @author 武继明
 *  @since 2013年10月11日  上午11:05:42
 *
 */
@MappedSuperclass
public abstract class IdEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)//有利于数据库移植
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
