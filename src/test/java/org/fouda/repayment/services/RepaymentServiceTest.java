package org.fouda.repayment.services;

import org.fouda.repayment.models.dto.LoanRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RepaymentServiceTest {
    @Mock
    private RepaymentCalculatorService calculatorService;
    @Mock
    private LoanRequestValidatorService validatorService;
    @InjectMocks
    private RepaymentService repaymentService;


    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionForMissingInput() {
        repaymentService.calculateRepaymentPlan(null);
    }

    @Test
    public void testCalculateRepaymentPlanCallingUnderlyingServiceLayer() {
        LoanRequest loanRequest = LoanRequest.builder().build();

        repaymentService.calculateRepaymentPlan(loanRequest);

        verify(validatorService).validate(loanRequest);
        verify(calculatorService).calculateRepaymentPlan(loanRequest);
    }
}
