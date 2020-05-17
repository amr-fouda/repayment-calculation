package org.fouda.repayment.models.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class RepaymentMonthlyPayment {
    private final String borrowerPaymentAmount;
    private final String date;
    private final Double initialOutstandingPrincipal;
    private final Double interest;
    private final Double principal;
    private final Double remainingOutstandingPrincipal;
}
