/**
 * 
 */
package org.ssor.boss.loan;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import org.ssor.boss.loan.controller.LoanControllerTest;
import org.ssor.boss.loan.dto.LoanDtoTest;
import org.ssor.boss.loan.entity.LoanTest;
import org.ssor.boss.loan.entity.LoanTypeTest;
import org.ssor.boss.loan.repository.LoanRepositoryTest;
import org.ssor.boss.loan.repository.LoanTypeRepositoryTest;
import org.ssor.boss.loan.service.LoanServiceTest;

/**
 * @author Derrian Harris
 *
 */
@RunWith(JUnitPlatform.class)
@SelectClasses({LoanRepositoryTest.class,LoanTypeRepositoryTest.class,LoanControllerTest.class,LoanServiceTest.class,BossLoanServiceApplicationTests.class,LoanTypeTest.class,LoanTest.class,LoanDtoTest.class})
public class BossLoanServiceTestSuite {

}
