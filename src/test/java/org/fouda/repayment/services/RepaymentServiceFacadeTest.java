package org.fouda.repayment.services;

import org.fouda.repayment.models.dto.MonthlyPayment;
import org.fouda.repayment.models.requests.RepaymentPlanRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RepaymentServiceFacadeTest {
    @Mock
    private RepaymentService repaymentService;
    @Mock
    private RepaymentCalculatorBuilderService builderService;
    @InjectMocks
    private RepaymentServiceFacade repaymentServiceFacade;


    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionForMissingInput() {
        repaymentServiceFacade.calculateRepaymentPlan(null);
    }

    @Test
    public void testCalculateRepaymentPlanCallingUnderlyingServiceLayer() {
        RepaymentPlanRequest repaymentPlanRequest = RepaymentPlanRequest.builder().build();
        List<MonthlyPayment> payments = new ArrayList<>();
        Mockito.when(repaymentService.calculateRepaymentPlan(builderService.build(repaymentPlanRequest))).thenReturn(payments);

        repaymentServiceFacade.calculateRepaymentPlan(repaymentPlanRequest);

        verify(builderService, times(2)).build(repaymentPlanRequest);
        verify(repaymentService).calculateRepaymentPlan(builderService.build(repaymentPlanRequest));
        verify(builderService).build(payments);
    }
}
