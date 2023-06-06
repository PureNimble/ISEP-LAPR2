# US 016 - When viewing a booking request, I want to respond to the user that scheduled the visit.

## 1. Requirements Engineering


### 1.1. User Story Description


As an agent, when viewing a booking request, I want to respond to the user that scheduled the visit.



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

> (...)



**From the client clarifications:**

> **Date:** 29th May, 2023
> 
> > **Question:** In AC2, what is DEI's email service? Are you referring to Outlook?
> >
> >  **Answer:** Different email services can send the message. These services must be configured using a configuration file to enable using
> different platforms (e.g.: gmail, DEI's email service, etc.). DEI email service is an email service like gmail or Outllook.
> These are only examples and you should prepare your system to support any email service.


> **Date:** 30th May, 2023
>
> > **Question:** Our team is having trouble understanding US016's AC2. Until now, the email has been sent in the form of a 
> text file, however, with this AC, a configuration file that allows the use of different platforms has been introduced. 
> How should the sending of emails be carried out then?
> >
> >  **Answer:** The configuration file defines the email service to be used. The URI of the email service should be defined 
> in the configuration file. The URI can be the path of a file. Please discuss this question with your ESOFT teatchers.
> 
> > **Question:** When the agent requests the booking requests list to contact the client, that list should ONLY contain the requests related to that agent?
> >
> >  **Answer:** Yes. Listing is a feature described in US15.
> Important: In US15 the Agent gets a list of booking requests (made to him). Then, the agent, may want to respond to the user (as defined in US16). 
> US15 and US16 are executed sequentially. Even so, the agent should be able to see a list of all booking requests made to him (US15) 
> without answer any booking request.
> 
> > **Question:** When the agent is responding to the user that created the request, what should the answer be? Because accepting or 
> declining the request is already done in US011.
> >
> > **Answer:** In US11 the agent wants to accept or decline a purchase order for a property. In US16 the agent wants to answer visit requests. 
> Pleasae discuss the requirements with your team and professors before making a question.


### 1.3. Acceptance Criteria


* **AC1:** The response is sent by email.
* **AC2:** Different email services can send the message. These services must be configured using a configuration file to enable using different platforms (e.g.: gmail, DEI's email service, etc.)
* **AC3:** The response should include the name and phone number of the responsible Agent.
* **AC4:** The response should include the property identification and location.


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

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us016-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

(...)

### 1.7 Other Relevant Remarks

* N\A