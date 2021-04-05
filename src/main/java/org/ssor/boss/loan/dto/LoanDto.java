/**
 * 
 */
package org.ssor.boss.loan.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.ssor.boss.loan.entity.Loan;

import lombok.Data;

/**
 * @author Derrian Harris
 *
 */
@Data
public class LoanDto {
	private Float amount;
	private Float interestRate;
	private LocalDateTime takenAt;
	private LocalDate dueBy;
	private Float amountDue;
	
	public Loan convertToLoanEntity() {
		Loan loan = new Loan();
		loan.setAmount(amount);
		loan.setInterestRate(interestRate);
		loan.setTakenAt(takenAt);
		loan.setAmountDue(amountDue);
		loan.setDueBy(dueBy);
		return loan;
	}
}
