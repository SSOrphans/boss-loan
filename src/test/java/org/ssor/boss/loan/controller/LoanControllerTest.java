package org.ssor.boss.loan.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.ssor.boss.loan.entity.Loan;
import org.ssor.boss.loan.entity.LoanType;
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
	List<LoanType> loanTypesA;
	List<LoanType> loanTypesE;

	@BeforeEach
	public void setup() {
		loanTypesA = new ArrayList<LoanType>();
		loanTypesA.add(new LoanType(1,"Student Loan"));
		loanTypesA.add(new LoanType(2,"Personal Loan"));
		
		loanTypesE = new ArrayList<LoanType>();
		loanTypesE.add(new LoanType(1,"Student Loan"));
		loanTypesE.add(new LoanType(2,"Personal Loan"));
		
		loanE = new Loan();
		loanE.setId(1);
		loanE.setUserId(1);
		loanE.setBranchId(1);
		loanE.setAmount(1f);
		loanE.setAmountDue(1f);
		loanE.setInterestRate(1f);
		loanE.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
		loanE.setDueBy(LocalDate.of(2022, 1, 1));
		loanE.setLoanType(loanTypesA.get(1));

		loanA = new Loan();
		loanA.setId(1);
		loanA.setUserId(1);
		loanA.setBranchId(1);
		loanA.setAmount(1f);
		loanA.setAmountDue(1f);
		loanA.setInterestRate(1f);
		loanA.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
		loanA.setDueBy(LocalDate.of(2022, 1, 1));
		loanA.setLoanType(loanTypesA.get(1));

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
	
	@Test
	public void test_CanGetAllLoanTypes() throws Exception {
		when(loanService.findAllLoanTypes()).thenReturn(loanTypesA);

		mvc.perform(get("/api/loans/types")).andExpect(status().isOk())
				.andExpect(content().json(mapper.writeValueAsString(loanTypesE)));
	}

}
