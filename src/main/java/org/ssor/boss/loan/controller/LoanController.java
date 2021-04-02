/**
 * 
 */
package org.ssor.boss.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.ssor.boss.loan.dao.LoanDao;
import org.ssor.boss.loan.dto.LoanDto;
import org.ssor.boss.loan.entity.Loan;
import org.ssor.boss.loan.service.LoanService;

/**
 * @author Derrian Harris
 */
@RestController
public class LoanController {
	@Autowired
	private LoanService loanService;

	@GetMapping(path = "users/{userid}/holder/loans/{loan_id}", produces = { "application/json" })
	public Loan getLoanByUserIdAndId(@PathVariable("userid") String userId,@PathVariable("loan_id") String id) {
		return loanService.findByUserIdAndId(Integer.parseInt(userId), Integer.parseInt(id));
	}

	@GetMapping(path = "users/{userid}/holder/loans", produces = { "application/json" })
	public List<Loan> getLoanByUserId(@PathVariable("userid") String userId) {
		return loanService.findByUserId(Integer.parseInt(userId));
	}
	
	@GetMapping(path = "branches/{branch_id}/loans", produces = { "application/json" })
	public List<Loan> getLoanByBranchId(@PathVariable("branch_id") String branchId) {
		return loanService.findByBranchId(Integer.parseInt(branchId));
	}
	
	@PostMapping(path = "branches/{branch_id}/loans",produces = { "application/json" }, consumes = { "application/json","application/xml" })
	public void addLoanByBranchId(@PathVariable("branch_id") String branchId, @RequestBody LoanDto loanDto) {
		loanService.add(loanDto.convertToLoanEntity());
	}
	
	@PutMapping(path = "branches/{branch_id}/loans",produces = { "application/json" }, consumes = { "application/json","application/xml" })
	public void updateLoanByBranchId(@PathVariable("branch_id") String branchId, @RequestBody Loan loan) {
		loanService.add(loan);
	}
}
