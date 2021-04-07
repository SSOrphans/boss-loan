package org.ssor.boss.loan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssor.boss.loan.dto.LoanDto;
import org.ssor.boss.loan.entity.Loan;
import org.ssor.boss.loan.entity.LoanType;
import org.ssor.boss.loan.repository.LoanRepository;
import org.ssor.boss.loan.repository.LoanTypeRepository;

import javassist.NotFoundException;

/**
 * @author Derrian Harris
 */
@Service
public class LoanService {

	@Autowired
	private LoanRepository loanDao;

	@Autowired
	private LoanTypeRepository loanTypeDao;

	public Loan add(LoanDto loanDto) throws IllegalArgumentException, NotFoundException {

		Loan loan = loanDto.convertToLoanEntity();
		Optional<LoanType> loanTypeOpt = loanTypeDao.findById(loanDto.getLoanType());
		if (loanTypeOpt.isEmpty())
			throw new NotFoundException("Resource not found with id: " + loanDto.getLoanType());

		loan.setLoanType(loanTypeOpt.get());

		return loanDao.save(loan);
	}

	public Loan update(LoanDto loanDto) throws IllegalArgumentException, NotFoundException {

		if (loanDto == null) {
			throw new IllegalArgumentException();
		}

		if (!loanDao.existsById(loanDto.getId()))
			throw new NotFoundException("Resource not found with id: " + loanDto.getId());

		Loan loan = loanDao.getOne(loanDto.getId());

		if (loanDto.getAmount() != null) {
			loan.setAmount(loanDto.getAmount());
		}
		if (loanDto.getAmountDue() != null) {
			loan.setAmountDue(loanDto.getAmountDue());
		}
		if (loanDto.getDueBy() != null) {
			loan.setDueBy(loanDto.getDueBy());
		}
		if (loanDto.getInterestRate() != null) {
			loan.setInterestRate(loanDto.getInterestRate());
		}
		if (loanDto.getTakenAt() != null) {
			loan.setTakenAt(loanDto.getTakenAt());
		}
		if (loanDto.getUserId() != null) {
			loan.setUserId(loanDto.getUserId());
		}

		if (loanDto.getBranchId() != null) {
			loan.setBranchId(loanDto.getBranchId());
		}
		if (loanDto.getLoanType() != null) {
			Optional<LoanType> loanTypeOpt = loanTypeDao.findById(loanDto.getLoanType());
			if (loanTypeOpt.isEmpty())
				throw new NotFoundException("Resource not found with id: " + loanDto.getLoanType());
			loan.setLoanType(loanTypeOpt.get());
		}

		return loanDao.save(loan);
	}

	public Loan findById(Integer id) throws IllegalArgumentException, NotFoundException {
		Optional<Loan> result = loanDao.findById(id);
		if (result.isEmpty()) {
			throw new NotFoundException("Resource not found with id: " + id);
		}
		return result.get();
	}

	public List<Loan> findByBranchId(Integer branchId) throws IllegalArgumentException, NotFoundException {
		if (branchId == null) {
			throw new IllegalArgumentException("Invalid Request");
		}

		List<Loan> loans = loanDao.findByBranchId(branchId);

		if (loans.isEmpty()) {
			throw new NotFoundException("Resource not found with branch id: " + branchId);
		}
		return loans;
	}

	public Loan findByUserIdAndId(Integer userId, Integer id) throws IllegalArgumentException, NotFoundException {
		if (userId == null || id == null) {
			throw new IllegalArgumentException("Invalid Request");
		}
		Loan loan = loanDao.findByUserIdAndId(userId, id);
		if (loan == null) {
			throw new NotFoundException("Resource not found with id: " + id);
		}
		return loan;
	}

	public List<Loan> findByUserId(Integer userId) throws IllegalArgumentException, NotFoundException {

		if (userId == null) {
			throw new IllegalArgumentException("Invalid Request");
		}
		List<Loan> loans = loanDao.findByUserId(userId);
		if (loans.isEmpty()) {
			throw new NotFoundException("Resource not found with User id: " + userId);
		}
		return loans;
	}

	public List<Loan> findAllLoans() throws NotFoundException {
		List<Loan> loans = loanDao.findAll();
		if (loans.isEmpty()) {
			throw new NotFoundException("Resource not found");
		}
		return loans;
	}

	public void deleteById(Integer id) throws IllegalArgumentException, NotFoundException {

		if (!loanDao.existsById(id)) {
			throw new NotFoundException("Resource not found with id: " + id);
		}
		loanDao.deleteById(id);
	}
	
	public List<LoanType> findAllLoanTypes() throws NotFoundException{
		List<LoanType> loanTypes = loanTypeDao.findAll();
		if(loanTypes.isEmpty()) {
			throw new NotFoundException("Resource not found");
		}
		return loanTypes;
	}
}
