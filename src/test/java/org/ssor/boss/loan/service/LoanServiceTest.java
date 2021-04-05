package org.ssor.boss.loan.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import org.ssor.boss.loan.entity.Loan;
import org.ssor.boss.loan.repository.LoanRepository;

/**
 * @author Derrian Harris
 *
 */
@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

	@InjectMocks
	private LoanService loanService;

	@Mock
	LoanRepository loanRepository;

	private Loan loanE;
	private List<Loan> loanListE;
	private Loan loanA;
	private List<Loan> loanListA;

	@BeforeEach
	public void setup() {

		loanE = new Loan();
		loanE.setId(null);
		loanE.setAmount(1f);
		loanE.setAmountDue(1f);
		loanE.setInterestRate(1f);
		loanE.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
		loanE.setDueBy(LocalDate.of(2022, 1, 1));

		loanA = new Loan();
		loanA.setId(null);
		loanA.setAmount(1f);
		loanA.setAmountDue(1f);
		loanA.setInterestRate(1f);
		loanA.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
		loanA.setDueBy(LocalDate.of(2022, 1, 1));

		loanListA = new ArrayList<Loan>();
		loanListE = new ArrayList<Loan>();
		loanListA.add(loanA);
		loanListE.add(loanE);
	}

	@Test
	public void test_CanFindById() {
		when(loanRepository.findById(1)).thenReturn(Optional.of(loanA));
		Loan result = loanService.findById(1);
		assertThat(result).isNotNull().isEqualTo(loanE);
	}

	@Test
	public void test_CanFindByBranchId() {
		when(loanRepository.findByBranchId(1)).thenReturn(loanListA);
		List<Loan> result = loanService.findByBranchId(1);
		assertThat(result).isNotNull().isNotEmpty().isEqualTo(loanListE);
	}

	@Test
	public void test_CanFindByUserIdAndId() {
		when(loanRepository.findByUserIdAndId(1, 1)).thenReturn(loanA);
		Loan result = loanService.findByUserIdAndId(1, 1);
		assertThat(result).isNotNull().isEqualTo(loanE);
	}

	@Test
	public void test_CanFindByUserId() {
		when(loanRepository.findByUserId(1)).thenReturn(loanListA);
		List<Loan> result = loanService.findByUserId(1);
		assertThat(result).isNotNull().isNotEmpty().isEqualTo(loanListE);
	}

	@Test
	public void test_CanFindAllLoans() {
		when(loanRepository.findAll()).thenReturn(loanListA);
		List<Loan> result = loanService.findAllLoans();
		assertThat(result).isNotNull().isNotEmpty().isEqualTo(loanListE);
	}

	@Test
	public void test_CanAddLoan() {
		when(loanRepository.save(loanA)).thenReturn(loanA);
		Loan result = loanService.add(loanA.convertToLoanDto());
		assertThat(result).isNotNull().isEqualTo(loanE);
	}
	
	@Test
	public void test_CanDeleteLoanById() {
		doNothing().when(loanRepository).deleteById(any(Integer.class));
		loanService.deleteById(1);
		verify(loanRepository, atLeast(1)).deleteById(any(Integer.class));
	}

}
