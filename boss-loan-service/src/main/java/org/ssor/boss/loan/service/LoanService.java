package org.ssor.boss.loan.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.ssor.boss.core.entity.Loan;
import org.ssor.boss.core.repository.LoanRepository;
import org.ssor.boss.core.transfer.LoanDto;

import java.time.LocalDateTime;
import java.util.Optional;

/*
 *@author Derrian Harris
 */


@Service
public class LoanService {

    @Autowired
    private LoanRepository loanDao;

    public Loan add(LoanDto loanDto) throws IllegalArgumentException, NotFoundException {

        Loan loan = loanDto.convertToLoanEntity();
        loan.setTakenAt(LocalDateTime.now());

        return loanDao.save(loan);
    }

    public Loan update(LoanDto loanDto) throws IllegalArgumentException, NotFoundException {
        Optional<Loan> loanOptional = loanDao.findById(loanDto.getId());
        if (loanOptional.isEmpty()) {
            throw new NotFoundException("Resource not found with id: " + loanDto.getId());
        }

        Loan loan = loanOptional.get();

        loan.setAmount(loanDto.getAmount());

        loan.setAmountDue(loanDto.getAmountDue());

        loan.setDueBy(loanDto.getDueBy());

        loan.setInterestRate(loanDto.getInterestRate());

        loan.setTakenAt(loanDto.getTakenAt());

        loan.setUserId(loanDto.getUserId());

        loan.setBranchId(loanDto.getBranchId());

        loan.setLoanType(loanDto.getLoanType());

        return loanDao.save(loan);
    }

    public Loan findById(Integer id) throws IllegalArgumentException, NotFoundException {
        Optional<Loan> result = loanDao.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException("Resource not found with id: " + id);
        }
        return result.get();
    }

    public Page<Loan> findByBranchId(Integer branchId, Integer page, Integer limit, String sortBy) throws IllegalArgumentException, NotFoundException {
        if (branchId == null) {
            throw new IllegalArgumentException("Invalid Request");
        }
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortBy.split(",")));
        Page<Loan> loans = loanDao.findByBranchId(branchId, pageable);

        if (loans.isEmpty()) {
            throw new NotFoundException("Resource not found with branch id: " + branchId);
        }
        return loans;
    }

    public Page<Loan> findByUserId(Integer userId, Integer page, Integer limit, String sortBy) throws IllegalArgumentException, NotFoundException {
        if (userId == null) {
            throw new IllegalArgumentException("Invalid Request");
        }
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortBy.split(",")));
        Page<Loan> loans = loanDao.findByUserId(userId, pageable);
        if (loans.isEmpty()) {
            throw new NotFoundException("Resource not found with User id: " + userId);
        }
        return loans;
    }

    public Page<Loan> findAllLoans(Integer page, Integer limit, String sortBy) throws NotFoundException {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortBy.split(",")));
        Page<Loan> loans = loanDao.findAll(pageable);
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
}
