package org.ssor.boss.loan.service;

import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssor.boss.core.entity.Loan;
import org.ssor.boss.core.repository.LoanRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.ssor.boss.core.entity.LoanType.LOAN_STUDENT;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

    @Mock
    LoanRepository loanRepository;

    LocalDateTime currTime;
    @InjectMocks
    private LoanService loanService;
    private Loan loanE;
    private List<Loan> loanListE;
    private Loan loanA;
    private List<Loan> loanListA;

    @BeforeEach
    public void setup() {
        currTime = LocalDateTime.now();
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
        loanA.setLoanType(LOAN_STUDENT);

        loanE.setId(1);
        loanE.setUserId(1);
        loanE.setBranchId(1);
        loanE.setAmount(1f);
        loanE.setAmountDue(1f);
        loanE.setInterestRate(1f);
        loanE.setTakenAt(currTime);
        loanE.setDueBy(LocalDate.of(2022, 1, 1));
        loanE.setLoanType(LOAN_STUDENT);

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

}
