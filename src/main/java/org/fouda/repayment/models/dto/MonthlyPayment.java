package org.fouda.repayment.models.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode(exclude = "startDate")
@RequiredArgsConstructor
public class MonthlyPayment {
    private final Double monthlyAnnuity;
    private final Double monthlyInterestValue;
    private final Double principal;
    private final Double initialOutstandingPrincipal;
    private final Double remainingOutstandingPrincipal;
    private final LocalDateTime startDate;
}
