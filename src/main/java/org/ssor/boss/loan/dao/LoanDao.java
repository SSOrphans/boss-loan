/**
 * 
 */
package org.ssor.boss.loan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ssor.boss.loan.entity.Loan;

/**
 * @author Derrian Harris
 */
@Repository
public interface LoanDao extends JpaRepository<Loan, Integer>{
	public Loan findByUserIdAndId(@Param("userId") Integer userId, @Param("id")Integer id);
	public List<Loan> findByUserId(@Param("userId") Integer userId);
	public List<Loan> findByBranchId(@Param("branchId") Integer branchId);
}
