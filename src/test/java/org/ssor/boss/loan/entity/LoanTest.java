package org.ssor.boss.loan.entity;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.ssor.boss.loan.dto.LoanDto;
import org.ssor.boss.loan.entity.Loan;

public class LoanTest {
	@Test
	  void test_CanCreateEmptyLoan(){
		Loan loan = new Loan();
	    assertThat(loan).isNotNull();
	  }
	
	
	@Test
	  void test_CanCreateAllArgsLoan(){
		Loan loan = new Loan(1,1f,1f,LocalDateTime.of(2021, 1, 1, 0, 0),LocalDate.of(2022, 1, 1),1f);
	    assertThat(loan).isNotNull();
	  }
	
	@Test
	  void test_CanEvalEqual(){
		Loan loanA = new Loan();
		Loan loanB = new Loan();
		loanA.setId(1);
		loanA.setAmount(1f);
		loanA.setAmountDue(1f);
		loanA.setInterestRate(1f);
		loanA.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
		loanA.setDueBy(LocalDate.of(2022, 1, 1));

		loanB.setId(1);
		loanB.setAmount(1f);
		loanB.setAmountDue(1f);
		loanB.setInterestRate(1f);
		loanB.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
		loanB.setDueBy(LocalDate.of(2022, 1, 1));
	    assertThat(loanA).isEqualTo(loanB);
	  }
	
	@Test
	  void test_CanConvertToLoanDto(){
		Loan loanA = new Loan();
		loanA.setId(1);
		loanA.setAmount(1f);
		loanA.setAmountDue(1f);
		loanA.setInterestRate(1f);
		loanA.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
		loanA.setDueBy(LocalDate.of(2022, 1, 1));
		
		LoanDto loanB = new LoanDto();
		loanB.setAmount(1f);
		loanB.setAmountDue(1f);
		loanB.setInterestRate(1f);
		loanB.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
		loanB.setDueBy(LocalDate.of(2022, 1, 1));
		
	    assertThat(loanA.convertToLoanDto()).isEqualTo(loanB);
	  }
}
