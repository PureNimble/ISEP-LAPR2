# US 009 - As a client, I want to leave a message to the agent to schedule a visit to a property of my interest. 

## 1. Requirements Engineering


### 1.1. User Story Description


As a client, I want to leave a message to the agent to schedule a visit to a property of my interest.



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	After consulting a list of properties, the client can request to schedule a visit to the real estate agent for a specific property to verify its conditions. The agent receives the request, checks the availability and sends the response.

>	



**From the client clarifications:**

> **Question:** Does the client provide (by typing) their name and phone number for the message, regardless of whether or not that information is already available to the system?
>
>  **Answer:** The information available in the system should be used. The client does not need to type the name and phone number.
>
> **Date:** 3 de May de 2023

> **Question:** Is all the required data for the message typed, or is any of it selected?
> 
>  **Answer:** For now the information should be typed.
>
> **Date:** 3 de May de 2023

> **Question:** Can the customer visit the same property more than once?
>
>  **Answer:** A client may post multiple visit requests, but only if those do not overlap each other. In this US the client is only scheduling a visit to a property. In the beginning of Sprint D we will introduce more USs.
>
> **Date:** 9 de May de 2023

> **Question:** "AC2. The message must also include the client's name, phone number, preferred date and time slot (from x hour to y hour) for the property visit." In the message what is the characteristic to identify the property to visit? Can we use the location? 
>
>  **Answer:** The message should be associated with a property. Please check AC1. The client should select a property that he wants to visit before making the visit request.
>
> **Date:**  9 de May de 2023

> **Question:** "AC4. The client must receive a success message when the request is valid and registered in the system." When sending the message, is the visit immediately scheduled after being validated by the system, or is it necessary for the agent to approve it?
>
>  **Answer:** No, the client is only making a visit request.
>
> **Date:**  9 de May de 2023

> **Question:** In AC2, when the suggestion of date and time is sent, is there a standard duration for the visit or is it mandatory to fill in a start time and an end time for the visit?
>
>  **Answer:**
>
> **Date:** 6 de May de 2023

> **Question:** In AC2, can any time be used or are we limited to certain hours?
>
>  **Answer:**
>
> **Date:** 6 de May de 2023

> **Question:** Also in AC2, should we use the 12am/pm or 24-hour time format?
>
>  **Answer:** 
>
> **Date:** 6 de May de 2023

> **Question:** "AC1. A list of available properties must be shown, sorted from the most recent entries to the oldest." Assuming that this is done so the client can see the available properties in order to select one and given that in a previous question you've stated that the required information for the message should be typed then, for this US, is the only selected data the property that the client wishes to visit?
>
>  **Answer:**
>
> **Date:** 9 de May de 2023

> **Question:** On US9 AC1 it is says: "AC1. A list of available properties must be shown, sorted from the most recent entries to the oldest." Does this mean that we can only have the option to make contact in this type of sort? When we list in another way, should not be possible to have the option to schedule a visit?
>
>  **Answer:**
>
> **Date:** 9 de May de 2023

> **Question:** The client can make multiple schedules in a single message?
>
>  **Answer:**
>
> **Date:** 12 de May de 2023

> **Question:** On US9 AC1 it is says: "AC1. A list of available properties must be shown, sorted from the most recent entries to the oldest." Does this mean that we can only have the option to make contact in this type of sort?
>
>  **Answer:** No. AC1 is the default sorting method.
>
> **Date:** 15 de May de 2023

> **Question:** When we list in another way, should not be possible to have the option to schedule a visit?
>
>  **Answer:** After sorting the properties, the cliente can still schedule a visit.
>
> **Date:** 15 de May de 2023

> **Question:** The client can make multiple schedules in a single message?
>
>  **Answer:** Every time the client makes use of this feature of the system, the client can specify multiple date/time slots.
>
> **Date:** 16 de May de 2023

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