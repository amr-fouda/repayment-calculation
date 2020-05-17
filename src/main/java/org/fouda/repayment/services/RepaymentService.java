package org.fouda.repayment.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fouda.repayment.models.dto.LoanRequest;
import org.fouda.repayment.models.dto.MonthlyPayment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Internal business layer for repayment calculation.
 * @author  Amr Fouda
 * @version 1.0
 * @since   2020-05-16
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RepaymentService {
    private final RepaymentCalculatorService calculatorService;
    private final LoanRequestValidatorService validatorService;

    public List<MonthlyPayment> calculateRepaymentPlan(@NonNull LoanRequest request) {
        validatorService.validate(request);
        return calculatorService.calculateRepaymentPlan(request);
    }


}
