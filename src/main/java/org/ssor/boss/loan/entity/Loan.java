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

//TODO: Loans need a link to user accounts. Based on the current data scheme this is not possible 
@NamedQuery(name = "Loan.findByUserIdAndId", query = "SELECT l FROM Loan l WHERE l.userId = :userId AND l.id = :id")
@NamedQuery(name = "Loan.findByUserId", query = "SELECT l FROM Loan l WHERE l.userId = :userId")
@NamedQuery(name = "Loan.findByBranchId", query = "SELECT l FROM Loan l WHERE l.branchId = :branchId")
@Table(name = "loan")
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name ="branch_id")
	private Integer branchId;
	
	@Column(name ="user_id")
	private Integer userId;
	
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
	
	@ManyToOne
	@JoinColumn(name="type_id", nullable=false)
	private LoanType loanType;

	public LoanDto convertToLoanDto() {
		LoanDto loanDto = new LoanDto();
		loanDto.setId(id);
		loanDto.setAmount(amount);
		loanDto.setLoanType(loanType.getId());
		loanDto.setUserId(userId);
		loanDto.setBranchId(branchId);
		loanDto.setInterestRate(interestRate);
		loanDto.setTakenAt(takenAt);
		loanDto.setDueBy(dueBy);
		loanDto.setAmountDue(amountDue);
		return loanDto;
	}
}
