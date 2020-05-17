package org.fouda.repayment.controllers;

import org.fouda.repayment.models.requests.RepaymentPlanRequest;
import org.fouda.repayment.services.RepaymentServiceFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RepaymentPlanControllerTest {
    @Mock
    private RepaymentServiceFacade repaymentServiceFacade;

    @InjectMocks
    private RepaymentPlanController repaymentPlanController;

    @Test
    public void testThatVerifiesCallingFacadeServiceLayer() {
        RepaymentPlanRequest request = RepaymentPlanRequest.builder().duration(24).loanAmount(5000d).nominalRate(5d).startDate(LocalDateTime.now()).build();

        repaymentPlanController.generateRepaymentPlan(request);

        verify(repaymentServiceFacade).calculateRepaymentPlan(request);
    }
}
