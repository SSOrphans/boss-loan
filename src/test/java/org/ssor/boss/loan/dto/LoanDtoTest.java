package org.ssor.boss.loan.dto;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.ssor.boss.loan.dto.LoanDto;
import org.ssor.boss.loan.entity.Loan;

public class LoanDtoTest {
	@Test
	  void test_CanCreateEmptyLoanDto(){
		LoanDto loanDto = new LoanDto();
	    assertThat(loanDto).isNotNull();
	  }
	
	@Test
	  void test_CanEvalEqual(){
		LoanDto loanDtoA = new LoanDto();
		LoanDto loanDtoB = new LoanDto();
		loanDtoA.setAmount(1f);
		loanDtoA.setAmountDue(1f);
		loanDtoA.setInterestRate(1f);
		loanDtoA.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
		loanDtoA.setDueBy(LocalDateTime.of(2022, 1, 1, 0, 0));

		loanDtoB.setAmount(1f);
		loanDtoB.setAmountDue(1f);
		loanDtoB.setInterestRate(1f);
		loanDtoB.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
		loanDtoB.setDueBy(LocalDateTime.of(2022, 1, 1, 0, 0));
	    assertThat(loanDtoA).isEqualTo(loanDtoB);
	  }
	
	@Test
	  void test_CanConvertToLoan(){
		
		
		LoanDto loanDtoA = new LoanDto();
		loanDtoA.setAmount(1f);
		loanDtoA.setAmountDue(1f);
		loanDtoA.setInterestRate(1f);
		loanDtoA.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
		loanDtoA.setDueBy(LocalDateTime.of(2022, 1, 1, 0, 0));
		
		Loan loanB = new Loan();
		loanB.setId(null);
		loanB.setAmount(1f);
		loanB.setAmountDue(1f);
		loanB.setInterestRate(1f);
		loanB.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
		loanB.setDueBy(LocalDateTime.of(2022, 1, 1, 0, 0));
		
	    assertThat(loanDtoA.convertToLoanEntity()).isEqualTo(loanB);
	  }
}
