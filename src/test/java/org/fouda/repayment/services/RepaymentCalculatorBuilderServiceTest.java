package org.fouda.repayment.services;

import org.fouda.repayment.models.dto.LoanRequest;
import org.fouda.repayment.models.dto.MonthlyPayment;
import org.fouda.repayment.models.requests.RepaymentPlanRequest;
import org.fouda.repayment.models.responses.RepaymentMonthlyPayment;
import org.fouda.repayment.models.responses.RepaymentPlanResponse;
import org.fouda.repayment.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(BlockJUnit4ClassRunner.class)
public class RepaymentCalculatorBuilderServiceTest {
    private final RepaymentCalculatorBuilderService repaymentCalculatorBuilderService = new RepaymentCalculatorBuilderService();

    @Test
    public void testThatBuildLoadRequest() {
        RepaymentPlanRequest repaymentPlanRequest = RepaymentPlanRequest.builder().startDate(LocalDateTime.now()).nominalRate(5d).loanAmount(5000d).duration(24).build();

        LoanRequest loanRequest = repaymentCalculatorBuilderService.build(repaymentPlanRequest);

        assertEquals(repaymentPlanRequest.getLoanAmount(), loanRequest.getLoanAmount());
        assertEquals(repaymentPlanRequest.getDuration(), loanRequest.getNumberOfMonths());
        assertEquals(repaymentPlanRequest.getNominalRate(), loanRequest.getYearlyInterestRate());
        assertEquals(repaymentPlanRequest.getStartDate(), loanRequest.getStartDate());
    }

    @Test
    public void testThatBuildRepaymentPlanResponse() {
        MonthlyPayment monthlyPayment = MonthlyPayment.builder().startDate(LocalDateTime.now()).initialOutstandingPrincipal(4000d).monthlyAnnuity(5d).monthlyInterestValue(10d).principal(1000d).remainingOutstandingPrincipal(2000d).build();
        List<MonthlyPayment> monthlyPayments = Stream.of(monthlyPayment).collect(toList());

        RepaymentPlanResponse repaymentPlanResponse = repaymentCalculatorBuilderService.build(monthlyPayments);

        assertNotNull(repaymentPlanResponse);
        assertNotNull(repaymentPlanResponse.getMonthlyPayments());
        RepaymentMonthlyPayment repaymentMonthlyPayment = repaymentPlanResponse.getMonthlyPayments().get(0);
        assertEquals(repaymentMonthlyPayment.getBorrowerPaymentAmount(), monthlyPayment.getMonthlyAnnuity().toString());
        assertEquals(repaymentMonthlyPayment.getInitialOutstandingPrincipal(), monthlyPayment.getInitialOutstandingPrincipal());
        assertEquals(repaymentMonthlyPayment.getInterest(), monthlyPayment.getMonthlyInterestValue());
        assertEquals(repaymentMonthlyPayment.getRemainingOutstandingPrincipal(), monthlyPayment.getRemainingOutstandingPrincipal());
        assertEquals(repaymentMonthlyPayment.getDate(), DateUtil.formatDate(monthlyPayment.getStartDate()));
    }
}
