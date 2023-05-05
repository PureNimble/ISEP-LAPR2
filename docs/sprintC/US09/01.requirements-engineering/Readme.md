# US 009 - As a client, I want to leave a message to the agent to schedule a visit to a property of my interest. 

## 1. Requirements Engineering


### 1.1. User Story Description


As a client, I want to leave a message to the agent to schedule a visit to a property of my interest.



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	After consulting a list of properties, the client can request to schedule a visit to the real estate agent for a specific property to verify its conditions. The agent receives the request, checks the availability and sends the response.

>	



**From the client clarifications:**

> **Question:**
>
>  **Answer:** 
>
> **Date:**

> **Question:** 
> 
>  **Answer:** 
>
> **Date:**

### 1.3. Acceptance Criteria


* **AC1:** A list of available properties must be shown, sorted from the most recent entries to the oldest.
* **AC2:** The message must also include the client's name, phone number, preferred date and time slot (from x hour to y hour) for the property visit.
* **AC3:** A client may post multiple visit requests, but only if those do not overlap each other.
* **AC4:** The client must receive a success message when the request is valid and registered in the system.


### 1.4. Found out Dependencies


* There is a dependency to "US004 Submit a request for listing a property sale or rent." since the client has to choose a property of his interest before scheduling a visit to that property.
* There is a dependency to "US003 As a system administrator, I want to register a store." since the client must leave a message to the agent, this agent must be registered in a store in order to schedule a visit for the client.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* A message from the client
	
* Selected data:
	* The property that the client has interest in


**Output Data:**

* A a success message
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.