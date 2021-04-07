/**
 * 
 */
package org.ssor.boss.loan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.ssor.boss.loan.dto.LoanDto;
import org.ssor.boss.loan.entity.Loan;
import org.ssor.boss.loan.service.LoanService;

import javassist.NotFoundException;

/**
 * @author Derrian Harris
 */
@RestController
public class LoanController {
	@Autowired
	private LoanService loanService;

	@GetMapping(path = "api/users/{userid}/holder/loans/{loan_id}", produces = { "application/json" })
	public ResponseEntity<Object> getLoanByUserIdAndId(@PathVariable("userid") String userId,@PathVariable("loan_id") String id) {
		Loan loan = new Loan();
		try {
			loan = loanService.findByUserIdAndId(Integer.parseInt(userId), Integer.parseInt(id));
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}catch (NotFoundException e) {
			return new ResponseEntity<Object>(loan, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(loan,HttpStatus.OK);
	}

	@GetMapping(path = "api/users/{userid}/holder/loans", produces = { "application/json" })
	public ResponseEntity<Object> getLoanByUserId(@PathVariable("userid") String userId) {
		
		List<Loan> loans = new ArrayList<Loan>();
		try {
			loans = loanService.findByUserId(Integer.parseInt(userId));
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}catch (NotFoundException e) {
			return new ResponseEntity<Object>(loans, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(loans,HttpStatus.OK);
	}
	
	@GetMapping(path = "api/branches/{branch_id}/loans", produces = { "application/json" })
	public ResponseEntity<Object> getLoanByBranchId(@PathVariable("branch_id") String branchId) {
		List<Loan> loans = new ArrayList<Loan>();
		try {
			loans = loanService.findByBranchId(Integer.parseInt(branchId));
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}catch (NotFoundException e) {
			return new ResponseEntity<Object>(loans, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(loans,HttpStatus.OK);
	}
	
	@PostMapping(path = "api/branches/{branch_id}/loans",produces = { "application/json" }, consumes = { "application/json","application/xml" })
	public ResponseEntity<Object> addLoanByBranchId(@PathVariable("branch_id") String branchId, @RequestBody LoanDto loanDto) {
		Loan loan;
		try
	    {
			loan = loanService.add(loanDto);
	    }
	    catch (IllegalArgumentException | NotFoundException e)
	    {
	      return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	    }
		return new ResponseEntity<Object>(loan, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "api/branches/{branch_id}/loans",produces = { "application/json" }, consumes = { "application/json","application/xml" })
	public ResponseEntity<Object> updateLoanByBranchId(@PathVariable("branch_id") String branchId, @RequestBody LoanDto loanDto) {
		Loan newLoan;
		try
	    {
			newLoan = loanService.update(loanDto);
	    }
	    catch (IllegalArgumentException e)
	    {
	      return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	    } catch (NotFoundException e) {
	    	return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(newLoan, HttpStatus.OK);
		
	}
	
	@DeleteMapping(path = "api/branches/{branch_id}/loans", consumes = { "application/json","application/xml" })
	public  ResponseEntity<Object> deleteLoanById(@RequestBody Loan loan) {
		try
	    {
			loanService.deleteById(loan.getId());
	    }
		catch (IllegalArgumentException e)
	    {
	      return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	    } catch (NotFoundException e) {
	    	return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	
}
