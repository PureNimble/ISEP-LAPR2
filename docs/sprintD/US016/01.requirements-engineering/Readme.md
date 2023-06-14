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

> **Date:** 6th June, 2023
> 
> > **Question:** The US15 does the listing and in US16 we are already responsing to one booking request. That said, were is the selection part being done?
> >
> > **Answer:** In US15 the Agent gets a list of booking requests (made to him). Then, the agent, may want to respond to the user (as defined in US16). 
> US15 and US16 are executed sequentially. Even so, the agent should be able to see a list of all booking requests made to him (US15) without answer 
> any booking request. In US16 the agent selects the booking request.

> **Date:** 8th June, 2023
> 
> > **Question:** Should the email with the reply be sent as a file, for example txt, or should it be sent as an email?
> >
> > **Answer:** You should sent/write the e-mail message to a file named email.txt. All e-mails to be send should be written to this file. 
> We will not use real world e-mail services and this file is used to replace the real world e-mail services.
> 
> > **Question:** What should be the difference between Gmail and Dei email service?
> >
> > **Answer:** These services must be configured using a configuration file to enable using different platforms. 
> Please study ESOFT and discuss these questions with your ESOFT teachers.

> **Date:** 9th June, 2023
> 
> > **Question:** To send an email, we must have a configuration file with the data: host, port, user, password, in which, the host will have a 
> URL or a path to the text file, or should we have only the host field?
> >
> > **Answer:** In the configuration file you should only define the email service name (example: gmail) and the host address. The host address is a URI. 
> As students did not take a course where they learn how to setup and use and email server, the URI to use is the path to the local email.txt file. 
> All email messages must be written to this file.

> **Date:** 10th June, 2023
> 
> > **Question:** Regarding the AC4, it is stated "The response should include the property identification and location.". 
> Is the property identification the same as the location? If not, how should we handle it in the legacy file since there isn't a specific column for each?
> >
> > **Answer:** The response should include only the property location. US16 is not related with the legacy file!!! I think you have to study a little more ESOFT!





### 1.3. Acceptance Criteria


* **AC1:** The response is sent by email.
* **AC2:** Different email services can send the message. These services must be configured using a configuration file to enable using different platforms (e.g.: gmail, DEI's email service, etc.)
* **AC3:** The response should include the name and phone number of the responsible Agent.
* **AC4:** The response should include the property identification and location.


### 1.4. Found out Dependencies


* There is a dependency on US03 (As a system administrator, I want to register a new employee), because this US is responsible for registering the agent from US016.
* There is a dependency on US07 (As an unregistered user, I want to register in the system to buy, sell or rent properties), because this US is responsible for creating the user that can become a client.
* There is a dependency on US09 (As a client, I want to leave a message to the agent to schedule a visit to a property of my interest), because this US is responsible for make a visit request.
* There is a dependency on US15 (As an agent, I intend to list all booking requests for properties managed by me), because this US is responsible for listing all booking requests of the agent who is logged in.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* client's email
    * reason (if the booking request is denied) 
	
* Selected data:
	* booking request (message)
    * accept or reject the booking request


**Output Data:**

* list of booking requests
* (un)successful operation

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram - Alternative One - Accepts the Booking Request](svg/us016-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two - Denies the Booking Request](svg/us016-system-sequence-diagram-alternative-two.svg)

#### Full

![System Sequence Diagram - Full](svg/us016-system-sequence-diagram-full.svg)


### 1.7 Other Relevant Remarks

* N\A