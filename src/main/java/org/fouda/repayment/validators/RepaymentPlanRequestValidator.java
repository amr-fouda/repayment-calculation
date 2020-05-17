package org.fouda.repayment.validators;

import org.fouda.repayment.models.requests.RepaymentPlanRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RepaymentPlanRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return RepaymentPlanRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        //Any extra validation beyond JSR 303 can go here
    }

}
