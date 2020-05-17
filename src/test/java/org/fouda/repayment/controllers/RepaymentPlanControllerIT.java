package org.fouda.repayment.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class RepaymentPlanControllerIT {
    public static final String API_V_1_REPAYMENT_PLAN = "/api/v1/repayment-plan";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testThatUsingNegativeLoanAmount() throws Exception {
        String jsonRequest = "{\n" +
                "\"loanAmount\": \"-5000\",\n" +
                "\"nominalRate\": \"5.0\",\n" +
                "\"duration\": 24,\n" +
                "\"startDate\": \"2018-01-01T00:00:01Z\"\n" +
                "}";
        expectBadRequest(jsonRequest);
    }

    @Test
    public void testThatUsingNominalRateValueGreaterThan100() throws Exception {
        String jsonRequest = "{\n" +
                "\"loanAmount\": \"5000\",\n" +
                "\"nominalRate\": \"120\",\n" +
                "\"duration\": 24,\n" +
                "\"startDate\": \"2018-01-01T00:00:01Z\"\n" +
                "}";
        expectBadRequest(jsonRequest);
    }

    @Test
    public void testThatUsingZeroDuration() throws Exception {
        String jsonRequest = "{\n" +
                "\"loanAmount\": \"5000\",\n" +
                "\"nominalRate\": \"5\",\n" +
                "\"duration\": 0,\n" +
                "\"startDate\": \"2018-01-01T00:00:01Z\"\n" +
                "}";
        expectBadRequest(jsonRequest);
    }

    @Test
    public void testThatGeneratesRepaymentPlanWithValidInput() throws Exception {
        String jsonRequest = "{\n" +
                "\"loanAmount\": \"5000\",\n" +
                "\"nominalRate\": \"5.0\",\n" +
                "\"duration\": 24,\n" +
                "\"startDate\": \"2018-01-01T00:00:01Z\"\n" +
                "}";
        mockMvc.perform(post(API_V_1_REPAYMENT_PLAN).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("monthlyPayments", hasSize(24)));
    }

    private void expectBadRequest(String jsonRequest) throws Exception {
        mockMvc.perform(post(API_V_1_REPAYMENT_PLAN).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("errors", hasSize(1)));
        //Check the response content
        //.andExpect(jsonPath("errors", contains("Loan amount value must be greater than or equal to 100 Euro")));
    }


}
