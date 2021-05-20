package org.ssor.boss.loan.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoanTypeEntityTest {
    @Test
    void test_CanCreateEmptyLoanTypeEntity() {
        LoanTypeEntity loanType = new LoanTypeEntity();
        assertThat(loanType).isNotNull();
    }


    @Test
    void test_CanCreateAllArgsLoanTypeEntity() {
        LoanTypeEntity loanType = new LoanTypeEntity(1, "LOAN_STUDENT");
        assertThat(loanType).isNotNull();
    }

    @Test
    void test_CanEvalEqual() {
        LoanTypeEntity loanTypeA = new LoanTypeEntity(1, "LOAN_STUDENT");
        LoanTypeEntity loanTypeB = new LoanTypeEntity(1, "LOAN_STUDENT");
        LoanTypeEntity loanTypeC = new LoanTypeEntity(2, "LOAN_PERSONAL");

        assertThat(loanTypeA).isEqualTo(loanTypeB);
        assertThat(loanTypeA).isNotEqualTo(loanTypeC);
    }
}
