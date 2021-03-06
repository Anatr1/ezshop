# Integration and API Test Documentation

Authors: Martin CAM / Alessio CORVAGLIA / Alessandro IANDOLI / Federico MUSTICH

Date: 7 June 2021

Version: 1.1

* Added test for Change2

# Contents

- [Dependency graph](#dependency graph)

- [Integration approach](#integration)

- [Tests](#tests)

- [Scenarios](#scenarios)

- [Coverage of scenarios and FR](#scenario-coverage)
- [Coverage of non-functional requirements](#nfr-coverage)



# Dependency graph 

![Dependency Graph](http://www.plantuml.com/plantuml/png/TSz1Qa8n4CRnVKwH2tW7hQYugU9xKgY4CYGVDfXCmcIoUBSl50f2utBu_GbVrar9jETasYDo9Q5iVScrC5WdNHAim2lnFS6Os-vp-Yxrwt0zEhjQsRzxSpuwytpyuXibOezJXP36mix-RxQ247LB4eRQcvOC6Tb9mXZEKaBtEYyLxgKEv0BjmhCGD_AlzawKy9oOsnpz0xfZbMMGZ5-vjl7q0nX2YlUU8T5FAf7lmt1_6FnW7nCke03PSFYGg11drk3GS_e1)
     
# Integration approach
    
<b>Approach used: Bottom-Up</b>
     
<b>Step 1:</b> Unit testing
<b>Step 2:</b> API testing

#  Tests

## Step 1
| Classes  | JUnit test cases |
|--|--|
|ProductTypeClass.java|VerifyBarCodeTest.verifyNull(), VerifyBarCodeTest.verifyFalseString(), VerifyBarCodeTest.verifyTrueString() testUnitsMethods.testProductTypeClass()|
|CardHandler.java|checkLuhnTest.testNull(), checkLuhnTest.testInvalidStrings(), checkLuhnTest.testValidStrings()|
|BalanceOperationClass.java|testUnitsMethods.testBalanceOperationClass()|
|CreditCardClass.java|testUnitsMethods.testCreditCardClass()|
|Customer.java|testUnitsMethods.testCustomerClass()|
|OrderClass.java|testUnitsMethods.testOrderClass()|
|UserClass.java|testUnitsMethods.testUserClass()|
|TicketEntryClass.java|testUnitsMethods.testTicketEntryClass()|
|SaleTransactionClass.java|testUnitsMethods.testSaleTransactionClass()|
|ReturnTransactionClass.java|testUnitsMethods.testReturnTransactionClass()|


## Step 2
| Classes  | JUnit test cases |
|--|--|
|EZShop.java (in data package)| All test cases included in the following classes: FunReq1Test.java, FunReq3Test.java, FunReq4Test.java, FunReq5Test.java, FunReq6Test.java, FunReq7Test.java, FunReq8Test.java, Change2Test.java All these classes are included in the IntegrationTesting package|


# Scenarios


If needed, define here additional scenarios for the application. Scenarios should be named
referring the UC in the OfficialRequirements that they detail


## Scenario UC2-4

| Scenario |  List all users |
| ------------- |:-------------:| 
|  Precondition     | Admin A exists and is logged in |
|  Post condition     |  List of users is returned |
| Step#        | Description  |
|  1     |  A asks the application to retrieve the user list |  

## Scenario UC9-2

| Scenario |  Record a balance operation |
| ------------- |:-------------:| 
|  Precondition     | User U exists and is logged in |
|  Post condition     | Balance operation is recorded |
|   | The system balance is modified |
| Step#        | Description  |
|  1     | User U pays for an order, issue a sale or a return and the balance is updated  |  



# Coverage of Scenarios and FR

Report in the following table the coverage of  scenarios (from official requirements and from above) vs FR. 
Report also for each of the scenarios the (one or more) API JUnit tests that cover it.


| Scenario ID | Functional Requirements covered | JUnit  Test(s) | 
| ----------- | ------------------------------- | ----------- | 
|  1-1         | FR3  | FunReq3Test.testCreateProductType() |
|  1-2         | FR4  | FunReq4Test.testUpdatePosition()    |
|  1-3         | FR3  | FunReq3Test.testUpdateProductType() |
|  2-1         | FR1                            |      FunReq1Test.testCreateUser()       |             
|  2-2         | FR1                             |       FunReq1Test.testDeleteUser()       |             
|  2-3         |    FR1                             |        FunReq1Test.testUpdateUserRights()      |             
| 2-4    |      FR1       | FunReq1Test.testGetAllUsers(), FunReq1Test.testGetUser() | 
|  3-1         | FR4  | FunReq4Test.testIssueOrder() |
|  3-2         | FR4  | FunReq4Test.testPayOrder() |
|  3-3         | FR4  | FunReq4Test.testRecordOrderArrival() Change2Test.testRecordOrderArrivalRFID() |
| 4-1         |                   FR5              |        FunReq5Test.defineCustomerTest()     |   
| 4-2         |                   FR5              |        FunReq5Test.attachCardToCustomerTest(), FunReq5Test.createCardTest()   |   
| 4-3         |                   FR5              |        FunReq5Test.modifyCustomerTest()     |   
| 4-4         |                   FR5              |        FunReq5Test.modifyCustomerTest()     |         
| 5-1         |                   FR1              |        FunReq1Test.testLogin()     |      
| 5-2         |                   FR1              |        FunReq1Test.testLogout()     |     
| 6-1         |                   FR6              |        FunReq6Test.testStartSaleTransaction(),  FunReq6Test.testAddProductToSale(), FunReq6Test.testDeleteProductFromSale(), FunReq6Test.testEndSaleTransaction(), FunReq6Test.testGetSaleTransaction(), Change2Test.testAddProductToSaleRFID(), Change2Test.testDeleteProductFromSaleRFID()     |  
| 6-2         |                   FR6              |        Same as scenario 6.1 + FunReq6Test.testApplyDiscountRateToProduct()     |  
| 6-3         |                   FR6              |        Same as scenario 6.1 + FunReq6Test.testApplyDiscountRateToSale()     |  
| 6-4         |                   FR6              |        Same as scenario 6.1 + FunReq6Test.testComputePointsForSale()    |  
| 6-5         |                   FR6              |        Same as scenario 6.1 + FunReq6Test.testDeleteSaleTransaction()    |  
| 6-6         |                   FR6              |        Same as scenario 6.1   |  
| 7-1         |                   FR7              |   FunReq7Test.receiveCreditCardPaymentTest()        |  
| 7-2         |                   FR7              |   FunReq7Test.receiveCreditCardPaymentTest()        |  
| 7-3         |                   FR7              |   FunReq7Test.receiveCreditCardPaymentTest()        |  
| 7-4         |                   FR7              |   FunReq7Test.receiveCashPaymentTest()       |  
| 8-1 | FR6 | FunReq7Test.startReturnTransactionTest(), FunReq7Test.returnProductTest(), FunReq7Test.endReturnTransactionTest(), FunReq7Test.returnCreditCardPaymentTest() Change2Test.testReturnProductRFID() |
| 8-2 | FR6 | FunReq7Test.startReturnTransactionTest(), FunReq7Test.returnProductTest(), FunReq7Test.endReturnTransactionTest(), FunReq7Test.returnCashPaymentTest() Change2Test.testReturnProductRFID() |
|  9-1           |       FR8   |  FunReq8Test.testGetCreditsAndDebits()   FunReq8Test.testComputeBalance()   |
|  9-2           |       FR8          |             FunReq8Test.testRecordBalanceUpdate()     |
|  10-1          |       FR7           |            FunReq7Test.returnCreditCardPaymentTest()                 |
|  10-2          |       FR7          |                FunReq7Test.returnCashPaymentTest()               |


# Coverage of Non Functional Requirements


<Report in the following table the coverage of the Non Functional Requirements of the application - only those that can be tested with automated testing frameworks.>


### 

| Non Functional Requirement | Test name |
| -------------------------- | --------- |
|               NFR2         |  Load ~1000 products, ~1000 customers, ~10000 transactions, ~1000 orders to test if the application is able to perform the operation in less than 0.5 sec         |
| NFR4 | VerifyBarCodeTest.verifyNull(), VerifyBarCodeTest.verifyFalseString(), VerifyBarCodeTest.verifyTrueString() |
| NFR5 | checkLuhnTest.testNull(), checkLuhnTest.testInvalidStrings(), checkLuhnTest.testValidStrings() |


