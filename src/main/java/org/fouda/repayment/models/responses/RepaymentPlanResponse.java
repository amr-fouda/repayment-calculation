package org.fouda.repayment.models.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class RepaymentPlanResponse {
    private final List<RepaymentMonthlyPayment> monthlyPayments;
}
