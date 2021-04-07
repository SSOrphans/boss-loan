/**
 * 
 */
package org.ssor.boss.loan.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * @author Derrian Harris
 *
 */
public class LoanTypeTest {
	@Test
	  void test_CanCreateEmptyLoan(){
		LoanType loanType = new LoanType();
	    assertThat(loanType).isNotNull();
	  }
	
	
	@Test
	  void test_CanCreateAllArgsLoan(){
		LoanType loanType = new LoanType(1,"Student Loan");
	    assertThat(loanType).isNotNull();
	    
	  }
	
	@Test
	  void test_CanEvalEqual(){
		LoanType loanTypeA = new LoanType(1, "Student Loan");
		LoanType loanTypeB = new LoanType(1, "Student Loan");
		LoanType loanTypeC = new LoanType(2, "Personal Loan");
		assertThat(loanTypeA).isEqualTo(loanTypeB);
		assertThat(loanTypeA).isNotEqualTo(loanTypeC);
	  }
}
