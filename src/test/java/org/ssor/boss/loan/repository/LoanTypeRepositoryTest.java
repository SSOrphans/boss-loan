/**
 * 
 */
package org.ssor.boss.loan.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.ssor.boss.loan.entity.LoanType;

/**
 * @author Derrian Harris
 *
 */
@DataJpaTest
public class LoanTypeRepositoryTest {
	
	@Autowired
	public LoanTypeRepository loanTypeRepository;

	public LoanType loanTypeA;
	@BeforeEach
	public void setup() {
		loanTypeA = new LoanType(1,"Student Loan");
	}
	
	@Test
	public void test_CanFindAllEmpty() {
		List<LoanType> result = loanTypeRepository.findAll();

		assertThat(result).isNotNull().isEmpty();
	}
	
	@Disabled
	@Test
	public void test_CanFindById() {
		loanTypeRepository.save(loanTypeA);
		LoanType result = loanTypeRepository.getOne(1);
		assertThat(result).isNotNull().isEqualTo(loanTypeA);
	}
}
