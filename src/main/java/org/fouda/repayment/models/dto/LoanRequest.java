package org.fouda.repayment.models.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class LoanRequest {
    private final Double loanAmount;
    private final Double yearlyInterestRate;
    private final Integer numberOfMonths;
    private final LocalDateTime startDate;
}
