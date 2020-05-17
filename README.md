# Repayment plan generator
Generating repayment plan for loans

## Code review
* **Clean code** using maven plugins (PMD & findbugs) and Sonarqube code analysis
* **Unit testing** using Junit and Mockito
* **Integration testing** using MockMvc
* **Test coverage** Fail build if less than 80%

## Endpoint URI
/api/v1/repayment-plan

## Run prerequisite
* JDK 8
* Maven 3+

## How to run locally?
* **Build the project** mvn clean install 
* **Run the project** mvn spring-boot:run
* **Run the end point using** 
    * **Postman**
    * **Swagger UI** http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/repayment-plan-controller/generateRepaymentPlan

## Request validation notes
* All request fields are mandatory (Loan amount - Yearly interest rate - Number of months - Start date).
* Loan amount value must exceed 99 Euro
* Yearly interest rate value must be between (0-100) (Exclusive)
* Number of repayment months must be a positive number.


## Sample request
```json
{
 "loanAmount": "5000",
 "nominalRate": "5.0",
 "duration": 12,
 "startDate": "2018-01-01T00:00:01Z"
 }
```

## Sample response
```json
{
    "monthlyPayments": [
        {
            "borrowerPaymentAmount": "428.04",
            "date": "2018-01-01T00:00:00",
            "initialOutstandingPrincipal": 5000,
            "interest": 20.83,
            "principal": 407.21,
            "remainingOutstandingPrincipal": 4592.79
        },
        {
            "borrowerPaymentAmount": "428.04",
            "date": "2018-02-01T00:00:00",
            "initialOutstandingPrincipal": 4592.79,
            "interest": 19.14,
            "principal": 408.9,
            "remainingOutstandingPrincipal": 4183.89
        },
        {
            "borrowerPaymentAmount": "428.04",
            "date": "2018-03-01T00:00:00",
            "initialOutstandingPrincipal": 4183.89,
            "interest": 17.43,
            "principal": 410.61,
            "remainingOutstandingPrincipal": 3773.28
        },
        {
            "borrowerPaymentAmount": "428.04",
            "date": "2018-04-01T00:00:00",
            "initialOutstandingPrincipal": 3773.28,
            "interest": 15.72,
            "principal": 412.32,
            "remainingOutstandingPrincipal": 3360.96
        },
        {
            "borrowerPaymentAmount": "428.04",
            "date": "2018-05-01T00:00:00",
            "initialOutstandingPrincipal": 3360.96,
            "interest": 14,
            "principal": 414.04,
            "remainingOutstandingPrincipal": 2946.92
        },
        {
            "borrowerPaymentAmount": "428.04",
            "date": "2018-06-01T00:00:00",
            "initialOutstandingPrincipal": 2946.92,
            "interest": 12.28,
            "principal": 415.76,
            "remainingOutstandingPrincipal": 2531.16
        },
        {
            "borrowerPaymentAmount": "428.04",
            "date": "2018-07-01T00:00:00",
            "initialOutstandingPrincipal": 2531.16,
            "interest": 10.55,
            "principal": 417.49,
            "remainingOutstandingPrincipal": 2113.67
        },
        {
            "borrowerPaymentAmount": "428.04",
            "date": "2018-08-01T00:00:00",
            "initialOutstandingPrincipal": 2113.67,
            "interest": 8.81,
            "principal": 419.23,
            "remainingOutstandingPrincipal": 1694.44
        },
        {
            "borrowerPaymentAmount": "428.04",
            "date": "2018-09-01T00:00:00",
            "initialOutstandingPrincipal": 1694.44,
            "interest": 7.06,
            "principal": 420.98,
            "remainingOutstandingPrincipal": 1273.46
        },
        {
            "borrowerPaymentAmount": "428.04",
            "date": "2018-10-01T00:00:00",
            "initialOutstandingPrincipal": 1273.46,
            "interest": 5.31,
            "principal": 422.73,
            "remainingOutstandingPrincipal": 850.73
        },
        {
            "borrowerPaymentAmount": "428.04",
            "date": "2018-11-01T00:00:00",
            "initialOutstandingPrincipal": 850.73,
            "interest": 3.54,
            "principal": 424.5,
            "remainingOutstandingPrincipal": 426.23
        },
        {
            "borrowerPaymentAmount": "428.01",
            "date": "2018-12-01T00:00:00",
            "initialOutstandingPrincipal": 426.23,
            "interest": 1.78,
            "principal": 426.23,
            "remainingOutstandingPrincipal": 0
        }
    ]
}
```
