package org.fouda.repayment.utils;

import org.fouda.repayment.models.dto.LoanRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class RepaymentCalculationUtilTest {
    private static final double DELTA = 1e-15;

    @Test
    public void testThatCalculateMonthlyAnnuity() {
        LoanRequest loanRequest = LoanRequest.builder().loanAmount(5000d).numberOfMonths(24).yearlyInterestRate(5d).build();

        double monthlyAnnuity = RepaymentCalculationUtil.calculateMonthlyAnnuity(loanRequest);

        assertEquals(219.35694867034286, monthlyAnnuity, DELTA);
    }

    @Test
    public void testThatCalculateMonthlyInterestRate() {
        double monthlyInterestRate = RepaymentCalculationUtil.calculateMonthlyInterestRate(5);

        assertEquals(0.004166666666666667, monthlyInterestRate, DELTA);
    }

    @Test
    public void testThatCalculateMonthlyInterestValue() {
        double monthlyInterestValue = RepaymentCalculationUtil.calculateMonthlyInterestValue(5d, 30, 5000, 360);

        assertEquals(20.833333333333332, monthlyInterestValue, DELTA);
    }

    @Test
    public void testThatCalculateMonthlyAnnuityUsingLoanRequest() {
        LoanRequest loanRequest = LoanRequest.builder().loanAmount(5000d).numberOfMonths(24).yearlyInterestRate(5d).build();

        double monthlyAnnuity = RepaymentCalculationUtil.calculateMonthlyAnnuity(loanRequest.getLoanAmount(), RepaymentCalculationUtil.calculateMonthlyInterestRate(loanRequest.getYearlyInterestRate()), loanRequest.getNumberOfMonths());

        assertEquals(219.35694867034286d, monthlyAnnuity, DELTA);
    }
}
