package org.ssor.boss.loan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssor.boss.loan.dao.LoanDao;
import org.ssor.boss.loan.entity.Loan;
/**
 * @author Derrian Harris
 */
@Service
public class LoanService {

	
	@Autowired
	private LoanDao loanDao;
	
	public Loan add(Loan loan) {
		return loanDao.save(loan);
	}
	
	public Loan findById(Integer id) {
		Optional<Loan> result = loanDao.findById(id);
		if(result.isEmpty()) {
			return null;
		}
		return result.get();
	}

	public List<Loan> findByBranchId(Integer branchId) {
		return loanDao.findByBranchId(branchId);
	}

	public Loan findByUserIdAndId(Integer userId, Integer id) {
		return loanDao.findByUserIdAndId(userId,id);
	}

	public List<Loan> findByUserId(Integer userId) {
		return loanDao.findByUserId(userId);
	}
	
	public List<Loan> findAllLoans() {
		return loanDao.findAll();
	}

}
