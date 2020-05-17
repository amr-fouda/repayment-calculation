package org.fouda.repayment.services;

import lombok.NonNull;
import org.fouda.repayment.models.dto.LoanRequest;
import org.springframework.stereotype.Service;

import static org.fouda.repayment.constants.LoanConstants.MINIMUM_LOAN_AMOUNT;
/**
 * Internal business layer validation for business model.
 * @author  Amr Fouda
 * @version 1.0
 * @since   2020-05-16
 */
@Service
public class LoanRequestValidatorService {
    public void validate(@NonNull final LoanRequest loanRequest) {
        validateMandatoryFields(loanRequest);
        validateLoanAmount(loanRequest.getLoanAmount());
        validateYearlyInterestRate(loanRequest.getYearlyInterestRate());
        validateNumberOfMonths(loanRequest.getNumberOfMonths());
    }

    private void validateNumberOfMonths(final Integer numberOfMonths) {
        if (numberOfMonths <= 0) {
            throw new IllegalArgumentException("Number of months must be a positive value");
        }
    }

    private void validateYearlyInterestRate(final Double yearlyInterestRate) {
        if (yearlyInterestRate > 100 || yearlyInterestRate < 0) {
            throw new IllegalArgumentException("Interest rate value must be within 100% range");
        }
    }

    private void validateLoanAmount(final Double loanAmount) {
        if (loanAmount < MINIMUM_LOAN_AMOUNT) {
            throw new IllegalArgumentException("Loan amount value can't be less than 100");
        }
    }

    private void validateMandatoryFields(final LoanRequest loanRequest) {
        if (loanRequest.getLoanAmount() == null || loanRequest.getYearlyInterestRate() == null || loanRequest.getNumberOfMonths() == null || loanRequest.getStartDate() == null) {
            throw new IllegalArgumentException("All mandatory ");
        }
    }
}
