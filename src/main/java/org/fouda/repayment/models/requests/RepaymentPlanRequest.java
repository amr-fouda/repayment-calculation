package org.fouda.repayment.models.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@RequiredArgsConstructor
public class RepaymentPlanRequest {
    @NotNull(message = "{loan.amount.not.null}")
    @Min(value = 100, message = "{loan.amount.min.value}")
    private final Double loanAmount;

    @NotNull(message = "{nominal.rate.not.null}")
    @Max(value = 100, message = "{nominal.rate.max.value}")
    @Positive(message = "{nominal.rate.positive.value}")
    private final Double nominalRate;

    @Min(value = 1, message = "{duration.min.value}")
    @NotNull(message = "{duration.not.null}")
    private final Integer duration;

    @NotNull(message = "{start.date.not.null}")
    private final LocalDateTime startDate;
}
