package org.fouda.repayment.services;

import org.fouda.repayment.models.dto.LoanRequest;
import org.fouda.repayment.models.dto.MonthlyPayment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(BlockJUnit4ClassRunner.class)
public class RepaymentCalculatorServiceTest {
    final RepaymentCalculatorService repaymentCalculatorService = new RepaymentCalculatorService();

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatingRepaymentPlanWithNullInput() {
        repaymentCalculatorService.calculateRepaymentPlan(null);
    }

    @Test
    public void testThatGenerateRepaymentPlanWithValidInputForTwoYearsDuration() {
        LoanRequest request = LoanRequest.builder().startDate(LocalDateTime.now()).yearlyInterestRate(5d).numberOfMonths(24).loanAmount(5000d).build();

        List<MonthlyPayment> monthlyPayments = repaymentCalculatorService.calculateRepaymentPlan(request);

        assertNotNull(monthlyPayments);
        assertEquals(24, monthlyPayments.size());
        MonthlyPayment firstPayment = monthlyPayments.get(0);
        MonthlyPayment lastPayment = monthlyPayments.get(monthlyPayments.size() - 1);
        assertEquals(firstPayment, MonthlyPayment.builder().monthlyAnnuity(219.36).monthlyInterestValue(20.83d).principal(198.53d).initialOutstandingPrincipal(5000d).remainingOutstandingPrincipal(4801.47d).build());
        assertEquals(lastPayment, MonthlyPayment.builder().monthlyAnnuity(219.28).monthlyInterestValue(0.91d).principal(218.37d).initialOutstandingPrincipal(218.37d).remainingOutstandingPrincipal(0.0d).build());
    }

    @Test
    public void testThatGenerateRepaymentPlanWithValidInputForOneYear() {
        LoanRequest request = LoanRequest.builder().startDate(LocalDateTime.now()).yearlyInterestRate(5d).numberOfMonths(12).loanAmount(5000d).build();

        List<MonthlyPayment> monthlyPayments = repaymentCalculatorService.calculateRepaymentPlan(request);

        assertNotNull(monthlyPayments);
        assertEquals(12, monthlyPayments.size());
    }
}
