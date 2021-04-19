/**
 * 
 */
package org.ssor.boss.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssor.boss.loan.entity.LoanType;

/**
 * @author Derrian Harris
 *
 */
@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType, Integer>{

}
