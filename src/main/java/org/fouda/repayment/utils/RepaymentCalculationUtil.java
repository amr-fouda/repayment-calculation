package org.fouda.repayment.utils;

import org.fouda.repayment.constants.LoanConstants;
import org.fouda.repayment.models.dto.LoanRequest;

public class RepaymentCalculationUtil {
    private RepaymentCalculationUtil() {
    }

    public static double calculateMonthlyAnnuity(LoanRequest loanRequest) {
        return calculateMonthlyAnnuity(loanRequest.getLoanAmount(), calculateMonthlyInterestRate(loanRequest.getYearlyInterestRate()), loanRequest.getNumberOfMonths());
    }

    public static double calculateMonthlyInterestValue(double yearlyPercentage, int daysInMonth, double lastOutstandingPrincipal, int daysInYear) {
        return (yearlyPercentage / 100) * daysInMonth * lastOutstandingPrincipal / daysInYear;
    }

    public static double calculateMonthlyInterestRate(double yearlyPercentage) {
        return yearlyPercentage / LoanConstants.MONTHS_COUNT_IN_YEAR / 100;
    }

    public static double calculateMonthlyAnnuity(double loanAmount, double monthlyInterestRate, int repaymentPeriod) {
        return loanAmount / ((1 - Math.pow(1 + monthlyInterestRate, -1 * (double) repaymentPeriod)) / monthlyInterestRate);
    }
}
