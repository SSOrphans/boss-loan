package org.ssor.boss.loan.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.ssor.boss.loan.entity.Loan;
import org.ssor.boss.loan.service.LoanService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LoanController.class)
public class LoanControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	private LoanService loanService;

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
	public void test_CanGetLoanByUserIdandId() throws Exception {
		when(loanService.findByUserIdAndId(1, 1)).thenReturn(loanA);

		mvc.perform(get("/users/1/holder/loans/1")).andExpect(status().isOk())
				.andExpect(content().json(mapper.writeValueAsString(loanE)));
	}

	@Test
	public void test_CanGetLoanByUserId() throws Exception {
		when(loanService.findByUserId(1)).thenReturn(loanListA);

		mvc.perform(get("/users/1/holder/loans")).andExpect(status().isOk())
				.andExpect(content().json(mapper.writeValueAsString(loanListE)));
	}

	@Test
	public void test_CanGetLoanByBranchId() throws Exception {
		when(loanService.findByBranchId(1)).thenReturn(loanListA);

		mvc.perform(get("/branches/1/loans")).andExpect(status().isOk())
				.andExpect(content().json(mapper.writeValueAsString(loanListE)));
	}

	@Test
	public void test_CanAddLoanByBranchId() throws Exception {
		when(loanService.add(loanA)).thenReturn(loanA);

		mvc.perform(post("/branches/1/loans").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(loanA))).andExpect(status().isOk());
	}

	@Test
	public void test_CanUpdateLoanByBranchId() throws Exception {
		when(loanService.add(loanA)).thenReturn(loanA);

		mvc.perform(put("/branches/1/loans").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(loanA))).andExpect(status().isOk());
	}

}
