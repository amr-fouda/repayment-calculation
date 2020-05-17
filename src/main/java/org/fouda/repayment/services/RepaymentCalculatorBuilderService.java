package org.fouda.repayment.services;

import org.fouda.repayment.models.dto.LoanRequest;
import org.fouda.repayment.models.dto.MonthlyPayment;
import org.fouda.repayment.models.requests.RepaymentPlanRequest;
import org.fouda.repayment.models.responses.RepaymentMonthlyPayment;
import org.fouda.repayment.models.responses.RepaymentPlanResponse;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.fouda.repayment.utils.DateUtil.formatDate;
/**
 * Mapping between Input adapters data model to internal service
 * layer business models.
 * @author  Amr Fouda
 * @version 1.0
 * @since   2020-05-16
 */
@Service
public class RepaymentCalculatorBuilderService {

    public LoanRequest build(RepaymentPlanRequest repaymentPlanRequest) {
        return LoanRequest.builder()
                .loanAmount(repaymentPlanRequest.getLoanAmount())
                .numberOfMonths(repaymentPlanRequest.getDuration())
                .yearlyInterestRate(repaymentPlanRequest.getNominalRate())
                .startDate(repaymentPlanRequest.getStartDate())
                .build();
    }

    public RepaymentPlanResponse build(List<MonthlyPayment> monthlyPayments) {
        return RepaymentPlanResponse.builder()
                .monthlyPayments(monthlyPayments.stream().map(this::buildRepaymentMonthlyPayments).collect(toList()))
                .build();
    }

    private RepaymentMonthlyPayment buildRepaymentMonthlyPayments(MonthlyPayment payment) {
        return RepaymentMonthlyPayment.builder()
                .initialOutstandingPrincipal(payment.getInitialOutstandingPrincipal())
                .borrowerPaymentAmount(payment.getMonthlyAnnuity().toString())
                .date(formatDate(payment.getStartDate()))
                .interest(payment.getMonthlyInterestValue())
                .principal(payment.getPrincipal())
                .remainingOutstandingPrincipal(payment.getRemainingOutstandingPrincipal())
                .build();
    }
}
