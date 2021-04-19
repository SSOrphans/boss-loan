/**
 * 
 */
package org.ssor.boss.loan.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.ssor.boss.loan.entity.Loan;
import org.ssor.boss.loan.entity.LoanType;

import lombok.Data;

/**
 * @author Derrian Harris
 *
 */
@Data
public class LoanDto {
	@NotNull
	private Integer id;
	@NotNull
	private Float amount;
	@NotNull
	private Float interestRate;
	@NotNull
	private Integer userId;
	@NotNull
	private Integer branchId;
	@NotNull
	private Integer LoanType;
	@NotNull
	private LocalDateTime takenAt;
	@NotNull
	private LocalDate dueBy;
	@NotNull
	private Float amountDue;
	
	public Loan convertToLoanEntity() {
		Loan loan = new Loan();
		LoanType loanType = new LoanType();
		loanType.setId(LoanType);
		loan.setId(id);
		loan.setAmount(amount);
		loan.setUserId(userId);
		loan.setBranchId(branchId);
		loan.setInterestRate(interestRate);
		loan.setTakenAt(takenAt);
		loan.setLoanType(loanType);
		loan.setAmountDue(amountDue);
		loan.setDueBy(dueBy);
		return loan;
	}
}
