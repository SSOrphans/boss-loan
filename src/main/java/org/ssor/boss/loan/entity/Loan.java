/**
 * 
 */
package org.ssor.boss.loan.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;

import org.ssor.boss.loan.dto.LoanDto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Derrian Harris
 *
 */

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@NamedNativeQuery(name = "Loan.findByUserIdAndId", query = "SELECT loan.* FROM (((loan INNER JOIN loan_confirmation ON loan.id = loan_confirmation.loan_id) INNER JOIN confirmation ON loan_confirmation.confirmation_id = confirmation.id) INNER JOIN user_confirmation ON confirmation.id = user_confirmation.confirmation_id) WHERE user_confirmation.user_id = :userId AND loan.id = :id", resultClass = Loan.class)
@NamedNativeQuery(name = "Loan.findByUserId", query = "SELECT loan.* FROM (((loan INNER JOIN loan_confirmation ON loan.id = loan_confirmation.loan_id) INNER JOIN confirmation ON loan_confirmation.confirmation_id = confirmation.id) INNER JOIN user_confirmation ON confirmation.id = user_confirmation.confirmation_id) WHERE user_confirmation.user_id = :userId", resultClass = Loan.class)
@NamedNativeQuery(name = "Loan.findByBranchId", query = "SELECT loan.* from loan inner join branch_loans on branch_loans.loan_id = loan.id where branch_loans.branch_id = :branchId", resultClass = Loan.class)
@Table(name = "loan")
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public LoanDto convertToLoanDto() {
		LoanDto loanDto = new LoanDto();
		loanDto.setAmount(amount);
		loanDto.setInterestRate(interestRate);
		loanDto.setTakenAt(takenAt);
		loanDto.setDueBy(dueBy);
		loanDto.setAmountDue(amountDue);
		return loanDto;
	}
}
