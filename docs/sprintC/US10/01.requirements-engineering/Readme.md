# US 0010 - Place an order to purchase the property, submitting the order amount

## 1. Requirements Engineering


### 1.1. User Story Description


As a client, I place an order to purchase the property, submitting the order
amount.


### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

> When the client decides to buy/rent the property, he sends a request for the purchase/lease of the property to the agent. 
> After being appreciated by the agent, he accepts or rejects the order. If the request is accepted, the offer will not be shown 
> again to clients using the application.

> (...)


**From the client clarifications:**

> **Question:** What data is required, in addition to the order value, so that a client can place an order to purchase a property? 
> **(Tuesday, 2nd May, 2023 at 11:44)**
>  
> **Answer:** Only the order amount **(Wednesday, 3rd May, 2023 at 17:46)**



> **Question:** When the client intends to place a purchase order, should the list of properties (announcements) be presented initially and 
> then asked to select a property? **(Wednesday, 3rd May, 2023 at 10:56)**
>  
> **Answer:** The system should show a list of properties to the client. **(Friday, 5th May, 2023 at 10:01)**



> **Question:** In US10, can the client remove an offer they made at any point, in order to replace it with a different one? 
> **(Wednesday, 3rd May, 2023 at 11:54)**
> 
> **Answer:** No. **(Friday, 5th May, 2023 at 10:13)**



> **Question:** Should this User Story be implemented as an addition to US001 (Users can display properties, and select one to make an order), 
> or should it be completely separate, with a separate section of the app dedicated to it? **(Wednesday, 3rd May, 2023 at 13:24)**
> 
> **Answer:** To place an order the actor should be registered in the system. **(Friday, 5th May, 2023 at 11:35)**



> **Question:** To order a purchase of a property, should the client be able to filter the properties by type of property, city, 
> district....so that it's easier to find the wanted property, or should the system show the entire list of properties to sale? 
> **(Friday, 5th May, 2023 at 11:43)**
>
> **Answer:** The system should show a list of properties to the client. Filtering is a useful feature of the system, please 
> prepare a user friendly and effective filtering to show the properties to the client. **(Tuesday, 9th May, 2023 at 15:49)**



> **Question:** When showing the other order on the screen, what data should be shown (eg client name, published date, order status)?
> **(Friday, 5th May, 2023 at 15:58)**
>
> **Answer:** If the order amount submitted by the client has already been posted for the property (by another request 
> from this client or any other client), the system must state that on the screen. The system should show the message 
> "The order amount submitted has already been posted for this property. Please contact the agent that is responsible for this property.". 
> **(Tuesday, 9th May, 2023 at 15:59)**


### 1.3. Acceptance Criteria


* **AC1:** The order amount submitted by the client must be equal to or lower than
the price set by the owner for the property.
* **AC2:** If the order amount submitted by the client has already been posted for
the property (by another request from this client or any other client), the
system must state that on the screen and the order placed previously should be
considered first when selling the property.
* **AC3:** A client can only submit a new order to purchase the same property after
the previous one is declined.

### 1.4. Found out Dependencies


* (...)

### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* (...)
	
* Selected data:
	* (...)


**Output Data:**

* (...)
* (...)

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram](svg/us010-system-sequence-diagram.svg)


### 1.7 Other Relevant Remarks

* (...)