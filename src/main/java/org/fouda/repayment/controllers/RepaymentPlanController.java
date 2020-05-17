package org.fouda.repayment.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fouda.repayment.models.requests.RepaymentPlanRequest;
import org.fouda.repayment.models.responses.RepaymentPlanResponse;
import org.fouda.repayment.services.RepaymentServiceFacade;
import org.fouda.repayment.validators.RepaymentPlanRequestValidator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Rest controller for generating repayment plan
 *
 * @author Amr Fouda
 * @version 1.0
 * @since 2020-05-16
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class RepaymentPlanController {
    private final RepaymentServiceFacade facade;
    private final RepaymentPlanRequestValidator repaymentPlanRequestValidator;

    @PostMapping(value = "repayment-plan", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RepaymentPlanResponse> generateRepaymentPlan(@RequestBody @Valid @Validated final RepaymentPlanRequest request) {
        log.info("Repayment plan calculation - received repayment plan request {} ", request);
        return ResponseEntity.ok(facade.calculateRepaymentPlan(request));
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(repaymentPlanRequestValidator);
    }
}
