package org.ssor.boss.loan.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.ssor.boss.loan.entity.Loan;
import org.ssor.boss.loan.repository.LoanRepository;

@DataJpaTest
public class LoanRepositoryTest {

	@Autowired
	public LoanRepository loanRepository;

	public Loan loanA;
	@BeforeEach
	public void setup() {
		loanA = new Loan();
		loanA.setId(1);
		loanA.setAmount(1f);
		loanA.setAmountDue(1f);
		loanA.setInterestRate(1f);
		loanA.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
		loanA.setDueBy(LocalDateTime.of(2022, 1, 1, 0, 0));
	}
	
	@Test
	public void test_CanFindAllEmpty() {
		List<Loan> result = loanRepository.findAll();

		assertThat(result).isNotNull().isEmpty();
		;
	}
	@Test
	public void test_CanFindById() {
		loanRepository.save(loanA);
		Loan result = loanRepository.findById(1).get();

		assertThat(result).isNotNull().isEqualTo(loanA);
	}
}
