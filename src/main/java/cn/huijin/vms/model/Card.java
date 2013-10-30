/**
 * 
 */
package cn.huijin.vms.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import sylarlove.advance.model.IdEntity;

/**
 * 卡
 * @author 武继明
 *  @since 2013年10月15日  上午10:39:42
 *
 */
@Entity
@Table(name="t_card")
public class Card extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="{Card.number.NotEmpty}")
	@Length(max=32,message="{Card.number,Length}")
	private String number;
	
	@Enumerated(EnumType.STRING)
	private CardType cardType;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	
}
