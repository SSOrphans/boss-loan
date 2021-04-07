/**
 * 
 */
package org.ssor.boss.loan.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.ssor.boss.loan.entity.Loan;
import org.ssor.boss.loan.entity.LoanType;

import lombok.Data;

/**
 * @author Derrian Harris
 *
 */
@Data
public class LoanDto {
	private Integer id;
	private Float amount;
	private Float interestRate;
	private Integer userId;
	private Integer branchId;
	private Integer LoanType;
	private LocalDateTime takenAt;
	private LocalDate dueBy;
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
