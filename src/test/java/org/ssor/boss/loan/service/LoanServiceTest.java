package org.ssor.boss.loan.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssor.boss.loan.dao.LoanDao;
import org.ssor.boss.loan.entity.Loan;

/**
 * @author Derrian Harris
 *
 */
@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

	@InjectMocks
	private LoanService loanService;

	@Mock
	LoanDao loanDao;

	private Loan loanE;
	private List<Loan> loanListE;
	private Loan loanA;
	private List<Loan> loanListA;

	@BeforeEach
	public void setup() {

		loanE = new Loan();
		loanE.setId(1);
		loanE.setAmount(1f);
		loanE.setAmountDue(1f);
		loanE.setInterestRate(1f);
		loanE.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
		loanE.setDueBy(LocalDateTime.of(2022, 1, 1, 0, 0));

		loanA = new Loan();
		loanA.setId(1);
		loanA.setAmount(1f);
		loanA.setAmountDue(1f);
		loanA.setInterestRate(1f);
		loanA.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
		loanA.setDueBy(LocalDateTime.of(2022, 1, 1, 0, 0));

		loanListA = new ArrayList<Loan>();
		loanListE = new ArrayList<Loan>();
		loanListA.add(loanA);
		loanListE.add(loanE);
	}

	@Test
	public void test_CanFindById() {
		when(loanDao.findById(1)).thenReturn(Optional.of(loanA));
		Loan result = loanService.findById(1);
		assertThat(result).isNotNull().isEqualTo(loanE);
	}

	@Test
	public void test_CanFindByBranchId() {
		when(loanDao.findByBranchId(1)).thenReturn(loanListA);
		List<Loan> result = loanService.findByBranchId(1);
		assertThat(result).isNotNull().isNotEmpty().isEqualTo(loanListE);
	}

	@Test
	public void test_CanFindByUserIdAndId() {
		when(loanDao.findByUserIdAndId(1, 1)).thenReturn(loanA);
		Loan result = loanService.findByUserIdAndId(1, 1);
		assertThat(result).isNotNull().isEqualTo(loanE);
	}

	@Test
	public void test_CanFindByUserId() {
		when(loanDao.findByUserId(1)).thenReturn(loanListA);
		List<Loan> result = loanService.findByUserId(1);
		assertThat(result).isNotNull().isNotEmpty().isEqualTo(loanListE);
	}

	@Test
	public void test_CanFindAllLoans() {
		when(loanDao.findAll()).thenReturn(loanListA);
		List<Loan> result = loanService.findAllLoans();
		assertThat(result).isNotNull().isNotEmpty().isEqualTo(loanListE);
	}

	@Test
	public void test_CanAddLoan() {
		when(loanDao.save(loanA)).thenReturn(loanA);
		Loan result = loanService.add(loanA);
		assertThat(result).isNotNull().isEqualTo(loanE);
	}

}
