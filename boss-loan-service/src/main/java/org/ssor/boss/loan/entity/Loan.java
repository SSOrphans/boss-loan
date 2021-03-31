/**
 * 
 */
package org.ssor.boss.loan.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;

/**
 * @author Derrian Harris
 *
 */

@Entity

@NamedNativeQuery(name = "Loan.findByUserIdandId", query = "SELECT loan.* FROM (((loan INNER JOIN loan_confirmation ON loan.id = loan_confirmation.loan_id) INNER JOIN confirmation ON loan_confirmation.confirmation_id = confirmation.id) INNER JOIN user_confirmation ON confirmation.id = user_confirmation.confirmation_id) WHERE user_confirmation.user_id = :userId AND loan.id = :id",resultClass = Loan.class)
@NamedNativeQuery(name = "Loan.findByUserId", query = "SELECT loan.* FROM (((loan INNER JOIN loan_confirmation ON loan.id = loan_confirmation.loan_id) INNER JOIN confirmation ON loan_confirmation.confirmation_id = confirmation.id) INNER JOIN user_confirmation ON confirmation.id = user_confirmation.confirmation_id) WHERE user_confirmation.user_id = :userId",resultClass = Loan.class)
@Table(name = "loan")
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "amount")
	private Float amount;

	@Column(name = "interest_rate")
	private Float interestRate;

	@Column(name = "taken_at")
	private LocalDateTime takenAt;

	@Column(name = "due_by")
	private LocalDate dueBy;

	@Column(name = "amount_due")
	private Float amountDue;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the amount
	 */
	public Float getAmount() {
		return this.amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Float amount) {
		this.amount = amount;
	}

	/**
	 * @return the interestRate
	 */
	public Float getInterestRate() {
		return this.interestRate;
	}

	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(Float interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * @return the takenAt
	 */
	public LocalDateTime getTakenAt() {
		return this.takenAt;
	}

	/**
	 * @param takenAt the takenAt to set
	 */
	public void setTakenAt(LocalDateTime takenAt) {
		this.takenAt = takenAt;
	}

	/**
	 * @return the dueBy
	 */
	public LocalDate getDueBy() {
		return this.dueBy;
	}

	/**
	 * @param dueBy the dueBy to set
	 */
	public void setDueBy(LocalDate dueBy) {
		this.dueBy = dueBy;
	}

	/**
	 * @return the amountDue
	 */
	public Float getAmountDue() {
		return this.amountDue;
	}

	/**
	 * @param amountDue the amountDue to set
	 */
	public void setAmountDue(Float amountDue) {
		this.amountDue = amountDue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Loan other = (Loan) obj;
		return Objects.equals(this.id, other.id);
	}

}
