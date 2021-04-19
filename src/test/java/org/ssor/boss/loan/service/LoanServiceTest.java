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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.ssor.boss.loan.entity.Loan;
import org.ssor.boss.loan.entity.LoanType;
import org.ssor.boss.loan.repository.LoanRepository;
import org.ssor.boss.loan.repository.LoanTypeRepository;

import javassist.NotFoundException;

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

	@Mock
	LoanTypeRepository loanTypeRepository;

	private Loan loanE;
	private List<Loan> loanListE;
	private Loan loanA;
	private List<Loan> loanListA;

	List<LoanType> loanTypesA;
	List<LoanType> loanTypesE;
	LocalDateTime currTime;

	@BeforeEach
	public void setup() {
		currTime = LocalDateTime.now();
		loanTypesA = new ArrayList<LoanType>();
		loanTypesA.add(new LoanType(1, "Student Loan"));
		loanTypesA.add(new LoanType(2, "Personal Loan"));

		loanTypesE = new ArrayList<LoanType>();
		loanTypesE.add(new LoanType(1, "Student Loan"));
		loanTypesE.add(new LoanType(2, "Personal Loan"));

		loanA = new Loan();
		loanE = new Loan();

		loanA.setId(1);
		loanA.setUserId(1);
		loanA.setBranchId(1);
		loanA.setAmount(1f);
		loanA.setAmountDue(1f);
		loanA.setInterestRate(1f);
		loanA.setTakenAt(currTime);
		loanA.setDueBy(LocalDate.of(2022, 1, 1));
		loanA.setLoanType(loanTypesA.get(1));

		loanE.setId(1);
		loanE.setUserId(1);
		loanE.setBranchId(1);
		loanE.setAmount(1f);
		loanE.setAmountDue(1f);
		loanE.setInterestRate(1f);
		loanE.setTakenAt(currTime);
		loanE.setDueBy(LocalDate.of(2022, 1, 1));
		loanE.setLoanType(loanTypesA.get(1));

		loanListA = new ArrayList<Loan>();
		loanListE = new ArrayList<Loan>();
		loanListA.add(loanA);
		loanListE.add(loanE);
	}

	@Test
	public void test_CanFindById() throws IllegalArgumentException, NotFoundException {
		when(loanRepository.findById(1)).thenReturn(Optional.of(loanA));
		Loan result = loanService.findById(1);
		assertThat(result).isNotNull().isEqualTo(loanE);
	}

	@Test
	public void test_CanFindByBranchId() throws IllegalArgumentException, NotFoundException {
		when(loanRepository.findByBranchId(1)).thenReturn(loanListA);
		List<Loan> result = loanService.findByBranchId(1);
		assertThat(result).isNotNull().isNotEmpty().isEqualTo(loanListE);
	}

	@Test
	public void test_CanFindByUserId() throws IllegalArgumentException, NotFoundException {
		when(loanRepository.findByUserId(1)).thenReturn(loanListA);
		List<Loan> result = loanService.findByUserId(1);
		assertThat(result).isNotNull().isNotEmpty().isEqualTo(loanListE);
	}

	@Test
	public void test_CanFindAllLoans() throws NotFoundException {
		when(loanRepository.findAll()).thenReturn(loanListA);
		List<Loan> result = loanService.findAllLoans();
		assertThat(result).isNotNull().isNotEmpty().isEqualTo(loanListE);
	}

	@Disabled
	@Test
	public void test_CanAddLoan() throws IllegalArgumentException, NotFoundException {
		when(loanTypeRepository.findById(any(Integer.class))).thenReturn(Optional.of(loanTypesA.get(1)));
		when(loanRepository.save(loanA)).thenReturn(loanA);
		Loan result = loanService.add(loanA.convertToLoanDto());
		assertThat(result).isNotNull().isEqualTo(loanE);
	}

	@Test
	public void test_CanDeleteLoanById() throws IllegalArgumentException, NotFoundException {
		when(loanRepository.existsById(any(Integer.class))).thenReturn(true);
		doNothing().when(loanRepository).deleteById(any(Integer.class));
		loanService.deleteById(1);
		verify(loanRepository, atLeast(1)).deleteById(any(Integer.class));
	}

	@Test
	public void test_CanFindAllLoanTypes() throws NotFoundException {
		when(loanTypeRepository.findAll()).thenReturn(loanTypesA);
		List<LoanType> result = loanService.findAllLoanTypes();
		assertThat(result).isNotNull().isNotEmpty().isEqualTo(loanTypesE);
	}
}
