package org.fouda.repayment.services;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.fouda.repayment.models.dto.LoanRequest;
import org.fouda.repayment.models.dto.MonthlyPayment;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.fouda.repayment.constants.LoanConstants.DAYS_IN_MONTH;
import static org.fouda.repayment.constants.LoanConstants.DAYS_IN_YEAR;
import static org.fouda.repayment.utils.MathUtil.round;
import static org.fouda.repayment.utils.RepaymentCalculationUtil.calculateMonthlyAnnuity;
import static org.fouda.repayment.utils.RepaymentCalculationUtil.calculateMonthlyInterestValue;

/**
 * Business layer for calculating repayments.
 *
 * @author Amr Fouda
 * @version 1.0
 * @since 2020-05-16
 */

@Slf4j
@Service
public class RepaymentCalculatorService {

    @Cacheable("loans")
    public List<MonthlyPayment> calculateRepaymentPlan(@NonNull final LoanRequest request) {
        log.info("Calculating repayment plan for {}", request);
        List<MonthlyPayment> monthlyPayments = new ArrayList<>();
        double currentOutstandingPrincipal = request.getLoanAmount();
        double monthlyAnnuity = round(calculateMonthlyAnnuity(request));
        LocalDateTime currentMonth = request.getStartDate();
        while (isLoanAmountNotCoveredFully(currentOutstandingPrincipal)) {
            double initialOutstandingPrincipal = currentOutstandingPrincipal;
            double monthlyInterestValue = round(calculateMonthlyInterestValue(request.getYearlyInterestRate(), DAYS_IN_MONTH, currentOutstandingPrincipal, DAYS_IN_YEAR));
            double principal = round(monthlyAnnuity - monthlyInterestValue);
            if (isLastMonthlyPayment(principal, initialOutstandingPrincipal)) {
                principal = initialOutstandingPrincipal;
                monthlyAnnuity = principal + monthlyInterestValue;
            }
            double remainingOutstandingPrincipal = round(initialOutstandingPrincipal - principal);
            currentOutstandingPrincipal = remainingOutstandingPrincipal;
            monthlyPayments.add(MonthlyPayment.builder().monthlyAnnuity(monthlyAnnuity)
                    .principal(principal).monthlyInterestValue(monthlyInterestValue).initialOutstandingPrincipal(initialOutstandingPrincipal)
                    .remainingOutstandingPrincipal(remainingOutstandingPrincipal).startDate(currentMonth)
                    .build());
            currentMonth = currentMonth.plusMonths(1);
        }
        return monthlyPayments;
    }

    private boolean isLoanAmountNotCoveredFully(double outstandingPrincipal) {
        return outstandingPrincipal > 0;
    }

    private boolean isLastMonthlyPayment(double principal, double initialOutstandingPrincipal) {
        return principal > initialOutstandingPrincipal;
    }

}
