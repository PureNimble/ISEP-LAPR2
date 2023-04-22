# US 006 - To specify states, districts and cities in the system

## 1. Requirements Engineering


### 1.1. User Story Description


As a system administrator, I want to specify states, districts and cities in the system.


### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	Each task is characterized by having a unique reference per organization, a designation, an informal and a technical description, an estimated duration and cost as well as the its classifying task category. 


**From the client clarifications:**

> **Question:** As System Administrator, who wants to specify districts, municipalities and parishes, what specifically he wants to do? Create new locations? Control existent locations? **Asked Thursday, March 23 of 2023 at 17:35**
>  
> **Note:** The addresses/locations that will be registered in our system do not include municipalities and parishes. Therefore, we rewrote US6. Please check the pdf file, available in moodle, that introduces the requirements for sprints A and B. Municipalities and parishes could be replaced by states and cicties.
> 
> **Answer:** The System Administrator wants to specify information in the system that can be used/selected to introduce the location. Remember that this is an extra US.


### 1.3. Acceptance Criteria


* **AC1:** All required fiels must be filled in.
* **AC2:** The district must be part of the state.
* **AC3:** The city must be a part of the district.
* **AC4:** The city, district and state cant already exist in the system.


### 1.4. Found out Dependencies


* There are no dependecies to other USs.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* a state, 
	* a district,
	* a city

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us06-system-sequence.svg)

### 1.7 Other Relevant Remarks
