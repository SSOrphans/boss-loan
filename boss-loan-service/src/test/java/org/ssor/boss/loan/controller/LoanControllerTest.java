package org.ssor.boss.loan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.ssor.boss.core.entity.Loan;
import org.ssor.boss.core.repository.LoanRepository;
import org.ssor.boss.loan.service.LoanService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.ssor.boss.core.entity.LoanType.LOAN_STUDENT;

@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerTest {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LoanService loanService;

    @MockBean
    private LoanRepository loanDao;

    private Loan loanE;
    private List<Loan> loanListE;
    private Loan loanA;
    private List<Loan> loanListA;

    @BeforeEach
    public void setup() {
        loanE = new Loan();
        loanE.setId(1);
        loanE.setUserId(1);
        loanE.setBranchId(1);
        loanE.setAmount(1f);
        loanE.setAmountDue(1f);
        loanE.setInterestRate(1f);
        loanE.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
        loanE.setDueBy(LocalDate.of(2022, 1, 1));
        loanE.setLoanType(LOAN_STUDENT);

        loanA = new Loan();
        loanA.setId(1);
        loanA.setUserId(1);
        loanA.setBranchId(1);
        loanA.setAmount(1f);
        loanA.setAmountDue(1f);
        loanA.setInterestRate(1f);
        loanA.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
        loanA.setDueBy(LocalDate.of(2022, 1, 1));
        loanA.setLoanType(LOAN_STUDENT);

        loanListA = new ArrayList<Loan>();
        loanListE = new ArrayList<Loan>();
        loanListA.add(loanA);
        loanListE.add(loanE);
    }

    @Test
    public void test_CanGetLoanId() throws Exception {
        when(loanService.findById(Mockito.anyInt())).thenReturn(loanA);

        mvc.perform(get("/api/loans/1")).andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(loanE)));
    }

    @Test
    public void test_CanGetLoanByUserId() throws Exception {
        when(loanService.findByUserId(1)).thenReturn(loanListA);

        mvc.perform(get("/api/users/1/loans")).andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(loanListE)));
    }

    @Test
    public void test_CanGetLoanByBranchId() throws Exception {
        when(loanService.findByBranchId(1)).thenReturn(loanListA);

        mvc.perform(get("/api/branches/1/loans")).andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(loanListE)));
    }

    @Test
    public void test_CanAddLoanByBranchId() throws Exception {
        when(loanService.add(loanA.convertToLoanDto())).thenReturn(loanA);

        mvc.perform(post("/api/loans").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(loanA.convertToLoanDto()))).andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(loanE)));
    }

    @Test
    public void test_CanUpdateLoanById() throws Exception {
        when(loanService.update(loanA.convertToLoanDto())).thenReturn(loanA);

        mvc.perform(put("/api/loans/1").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(loanA.convertToLoanDto()))).andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(loanE)));
    }

    @Test
    public void test_CanDeleteLoanById() throws Exception {
        doNothing().when(loanService).deleteById(any(Integer.class));

        mvc.perform(delete("/api/loans/1").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(loanA))).andExpect(status().isOk());
    }

}
