# US 07 - To register in the system to buy, sell or rent properties

## 1. Requirements Engineering


### 1.1. User Story Description


As an unregistered user, I want to register in the system to buy, sell or rent properties.


### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	No.


**From the client clarifications:**

> **Question:** Does the user also receive the password via email or can he choose a password when registering?
>  
> **Answer:** The owner can choose a password when registering.


> **Question:** When an unregistered user wants to register a new account in the system, the set of parameters that are asked are the following: name, citizen card number, tax number, email, phone number, and password. Do you want any extra parameters/requirements to be asked or just the ones specified above? If so, which ones are mandatory?
>  
> **Answer:** The Owner attributes are: the name, the citizen's card number, the tax number, the address, the email address and the contact telephone number. The address of the owner is not mandatory.


> **Question:** It was previously stated that an unregistered user could do a property listing request. However, with the introduction of US007, I want to clarify and make sure that now a user needs to be registered in order to buy, sell or rent properties, or if they can still do it unregistered.
>  
> **Answer:** In Sprint B we introduce US7 and now, in US4, the owner needs to be registered in the system to submit a request for listing. You should update all artifacts to include this change.


### 1.3. Acceptance Criteria


* **AC1:** The owner can choose a password when registering.
* **AC2:** The address of the owner is not mandatory.


### 1.4. Found out Dependencies


* There are no dependencies to other US.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* a name, 
	* a citizen's card number, 
	* a tax number,
	* an address,
	* an email address,
	* a phone number


**Output Data:**

* Registration of the user
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)


![System Sequence Diagram - Alternative One](svg/us07-system-sequence-diagram.svg)
