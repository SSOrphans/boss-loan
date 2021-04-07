/**
 * 
 */
package org.ssor.boss.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssor.boss.loan.entity.LoanType;

/**
 * @author Derrian Harris
 *
 */
public interface LoanTypeRepository extends JpaRepository<LoanType, Integer>{

}
