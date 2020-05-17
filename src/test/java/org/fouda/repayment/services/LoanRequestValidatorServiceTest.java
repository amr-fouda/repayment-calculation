package org.fouda.repayment.services;

import org.fouda.repayment.models.dto.LoanRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.time.LocalDateTime;

@RunWith(BlockJUnit4ClassRunner.class)
public class LoanRequestValidatorServiceTest {
    private final LoanRequestValidatorService loanRequestValidatorService = new LoanRequestValidatorService();

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionOnNullInput() {
        loanRequestValidatorService.validate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionOnMissingAllInput() {
        loanRequestValidatorService.validate(LoanRequest.builder().build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionOnMissingThreeInputs() {
        loanRequestValidatorService.validate(LoanRequest.builder()
                .startDate(LocalDateTime.now())
                .yearlyInterestRate(5d).build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionOnMissingTwoInputs() {
        loanRequestValidatorService.validate(LoanRequest.builder()
                .startDate(LocalDateTime.now())
                .yearlyInterestRate(5d)
                .numberOfMonths(5)
                .build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionOnMissingOneInput() {
        loanRequestValidatorService.validate(LoanRequest.builder()
                .yearlyInterestRate(5d)
                .loanAmount(1000d)
                .numberOfMonths(5)
                .build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionOnLoanAmountValueLessThan100() {
        loanRequestValidatorService.validate(LoanRequest.builder()
                .yearlyInterestRate(5d)
                .startDate(LocalDateTime.now())
                .loanAmount(-100d)
                .numberOfMonths(5)
                .build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionOnYearlyInterestRateWithNegativeValue() {
        loanRequestValidatorService.validate(LoanRequest.builder()
                .yearlyInterestRate(-1d)
                .startDate(LocalDateTime.now())
                .loanAmount(5000d)
                .numberOfMonths(5)
                .build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionOnYearlyInterestRateWithValueGreaterThan100Percentage() {
        loanRequestValidatorService.validate(LoanRequest.builder()
                .yearlyInterestRate(120d)
                .startDate(LocalDateTime.now())
                .loanAmount(5000d)
                .numberOfMonths(5)
                .build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionOnYearlyInterestRateWithNegativeNumberOfMonths() {
        loanRequestValidatorService.validate(LoanRequest.builder()
                .yearlyInterestRate(5d)
                .startDate(LocalDateTime.now())
                .loanAmount(5000d)
                .numberOfMonths(-5)
                .build());
    }
}
