# US 03 - Register an employee

## 1. Requirements Engineering

The company´s system administrator will be responsible for registering 
all employees(specifiying the name, the citizen´s card number,the tax number,the adress,
the email adress, the contact telefone number and the agency to which it is assigned).


### 1.1. User Story Description

As a system administrator, I want to register a new employee.


### 1.2. Customer Specifications and Clarifications


**From the specifications document:**

>	Each task is characterized by having a unique reference per organization, a designation, an informal and a technical description, an estimated duration and cost as well as the its classifying task category.


>	As long as it is not published, access to the task is exclusive to the employees of the respective organization.



**From the client clarifications:**

> **Question:** Which is the unit of measurement used to estimate duration?
>
> **Answer:** Duration is estimated in days.

> **Question:** How many alphabetic characters the name of the employee must have?
>
> **Answer:** 

> **Question:** How many digits does the citizen's card number the employee need to have?
>
> **Answer:**

> **Question:** How many digits does the tax number for the employee need to have?
>
> **Answer:**

> **Question:** Could you give an example of how the address should be written? How many alphanumeric characters should it have?
>
> **Answer:** 

> **Question:** How many alphanumeric characters does the email address need to have? Are there any restrictions?
> 
> **Answer:**

> **Question:** How many digits does the contact telephone number for the employee need to have?
>
> **Answer:**

> **Question:** How many alphabetic characters does the agency name need to have?
>
> **Answer:**



### 1.3. Acceptance Criteria


* **AC1:** All required fiels must be filled in.
* **AC2:** Task reference must have at least 5 alphanumeric chars.
* **AC3:** When creating a task with an already existing reference, the system must reject such operation and the user must have the change to modify the typed reference.


### 1.4. Found out Dependencies


* There might be a dependency to “US5 register a store” since the employee might be one working in one.


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

