# US 03 - Register an employee

## 1. Requirements Engineering

The company´s system administrator will be responsible for registering 
all employees(specifiying the name, the citizen´s card number,the tax number,the adress,
the email adress, the contact telefone number and the agency to which it is assigned).


### 1.1. User Story Description

As a system administrator, I want to register a new employee.


### 1.2. Customer Specifications and Clarifications

**From the client clarifications:**

> **Question:** When registering a new employee, all the required data (name, citizen's card number, etc...) have to be filled or exists not mandatory data?
>
> **Answer:** Required/Mandatory data that should be filled when registering an employee: name, the citizen's card number, the tax number, the email address, the contact telephone number and the agency to which it is assigned.

> **Question:** The system administrator cannot add an agent that already exists, the agent has two unique numbers that identify him (Tax number and Citizen's card number) which one should be used to identify the agent?
>
> **Answer:** The tax number.

> **Question:** Could you give an example of how the address should be written? How many alphanumeric characters should it have?
>
> **Answer:** "71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705".\
> Street: 71 ST. NICHOLAS DRIVE;\
> City: NORTH POLE;\
>District: Fairbanks North Star (this is opcional); \
> State: AK;\
> Zipcode:99705;

> **Question:** Must the Tax number and Citizen's card number follow any convention? If so, which?
> 
> **Answer:** You should use the tax identification number used for tax purposes in the US.

> **Question:** How many digits does the contact telephone number for the employee need to have?
>
> **Answer:** An example phone number is (907) 488-6419

> **Question:** How many digits should we go forward for password length validation in your software? And please confirm required special characters, etc.
>
> **Answer:** The password should have seven characters in length including three capital letters and two digits and should be generated automatically


### 1.3. Acceptance Criteria


* **AC1:** Required/Mandatory data that should be filled when registering an employee: name, the citizen's card number, the tax number, the email address, the contact telephone number and the agency to which it is assigned.
* **AC2:** The tax identification number should be the one used for tax purposes in the US .
* **AC3:** The tax number should be used to identify an Employee.
* **AC4:** The ID is an integer number.
* **AC5:** The password should have seven characters in length including three capital letters and two digits and should be generated automatically 

### 1.4. Found out Dependencies


* There is a dependency to “US5 register a store” since the employee might be one working in one.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
    * a name,
    * a citizen´s card number,,
    * a tax number,
    * an adress,
    * an email adress,
    * a telefone number,
    * an agency,

**Output Data:**

* Registered a new employee
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative One](svg/us03-system-sequence-diagram.svg)

