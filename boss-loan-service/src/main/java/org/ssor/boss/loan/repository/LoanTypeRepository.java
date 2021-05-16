package org.ssor.boss.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssor.boss.loan.entity.LoanTypeEntity;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanTypeEntity, Integer> {
}
