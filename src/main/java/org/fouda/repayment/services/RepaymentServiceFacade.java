package org.fouda.repayment.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.fouda.repayment.models.dto.MonthlyPayment;
import org.fouda.repayment.models.requests.RepaymentPlanRequest;
import org.fouda.repayment.models.responses.RepaymentPlanResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Entry point for repayment module that can be called from
 * different input adapters like rest controllers or messaging adapters.
 * @author  Amr Fouda
 * @version 1.0
 * @since   2020-05-16
 */
@Service
@RequiredArgsConstructor
public class RepaymentServiceFacade {

    private final RepaymentService repaymentService;
    private final RepaymentCalculatorBuilderService builderService;

    public RepaymentPlanResponse calculateRepaymentPlan(@NonNull final RepaymentPlanRequest request) {
        final List<MonthlyPayment> monthlyPayments = repaymentService.calculateRepaymentPlan(builderService.build(request));
        return builderService.build(monthlyPayments);
    }
}
