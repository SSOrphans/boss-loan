/**
 * 
 */
package org.ssor.boss.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.ssor.boss.loan.dao.LoanDao;
import org.ssor.boss.loan.entity.Loan;

/**
 * @author Derrian Harris
 *
 */
@RestController
public class LoanController {
	@Autowired
	private LoanDao loanDao;

	@GetMapping(path = "users/{userid}/holder/loans/{loan_id}", produces = { "application/json" })
	public Loan getLoanByUserIdandId(@PathVariable("userid") String userId,@PathVariable("loan_id") String id) {
		return loanDao.findByUserIdandId(Integer.parseInt(userId), Integer.parseInt(id));
	}

	@GetMapping(path = "users/{userid}/holder/loans", produces = { "application/json" })
	public List<Loan> getLoanByUserId(@PathVariable("userid") String userId) {
		return loanDao.findByUserId(Integer.parseInt(userId));
	}
}
