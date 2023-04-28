# US 04 - Submit a request for listing a property sale or rent

## 1. Requirements Engineering

### 1.1. User Story Description

As an owner, I intend to submit a request for listing a property sale or rent,choosing the responsible agent.


### 1.2. Customer Specifications and Clarifications


**From the specifications document:**

> The owner will contact Real Estate USA, to sell or rent their properties. From time to time, property owners contact Real Estate USA to sell or rent their properties. The owner provides property characteristics and the requested price and sends the request to an agent. 

**From the client clarifications:**

> **Date:** Wednesday, March 15 of 2023
>
> **Question:** Are there any restrictions on the choice of an Agent?
>
> **Answer:** No.
>
> **Question:** In case the submission of the listing is online may the owner choose any agent?
>
> **Answer:** Yes.
>
> **Question:** In case it is on an agency, must the agent be assigned automatically by the system?
>
> **Answer:** The agent that registers the information in the system can choose to assign any agent
>
> **Asked at:** 22:18
>
> **Question:** Is it possible to submit multiple listing for the same property and type of listing?
>
> **Answer:** No.
>
> **Asked at:** 22:38


> **Question:** In the Project description, there are only specifications for a Sale. What are the required characteristics for a rental?
>
> **Answer:** The caracteristics for a rental are the same as the ones for the sale of a property. The rent value is per month. Additionally, we have to define the contract duration.
>
> **Asked at:** 21:58


> **Date:** Tuesday, March 21 of 2023
>
> **Question:** When publishing a property, if the owner leaves the listing unfinished, can it be saved or stay as as a sketch to be finished later?
>
> **Answer:** No.
>
> **Asked at:** 11:44
>
> **Question:**  Is there a designated currency for this business, or should we use USD?
>
> **Answer:** Please use USD.
>
> **Asked at:** 18:55
>
> **Question:** Does an owner need to be registered in the system to submit a request for a property listing?
>
> **Answer:** No. When making the request to list a property, the owner should introduce his own data. The Owner attributes are: the name, the citizen's card number, the tax number, the address, the email address and the telephone number.
>
> **Asked at:** 23:21


> **Date:** Wednesday, March 22 of 2023
>
> **Question:** When assigning an agent to a property listing, are the available agents shown by the system for the owner to pick? Or does the owner need to provide the agent's information (name, agency,etc)?
>
> **Answer:** The owner should select one agent from a list of agents that work in the selected agency. The owner should select the agency before selecting the agent.
>
> **Asked at:** 12:34


> **Date:** Thursday, March 23 of 2023
>
> **Question:** According to the Project Description, the agent when selling a property can charge a flat price comission or a percentage of the sale value, my question here is wether there is a minimum and/or a maximum to each of these types of comissions?
>
> **Answer:** There is no maximum and the minimum is 0.
>
> **Asked at:** 09:02


> **Date:** Saturday, March 25 of 2023
>
> **Question:** In the case of listing a land property, shall the owner refer if there is a building permit already approved?
>
> **Answer:** No.
>
> **Asked at:** 19:41


> **Date:** Monday, March 27 of 2023
>
> **Question:** If the owner doesn't select an agent will the platform randomly assign one or the request cannot move to revision? If not, must we assume that all information slots must be filled?
>
> **Answer:** Thank you for your suggestion. When filling the property data, the owner should select one agent from the list of agents working in the selected agency. Moreover, the application should include a feature to randomly assign one agent. The address of the owner is not mandatory.
>
> **Question:**  Do requests have any reference/code identifying them with any specific format? What about descriptions (any restrictions, like character limit)? Does that reference carry out with the advertisement?
>
> **Answer:** Please choose appropriate data formats for the request. You are a team of experts and you should choose appropriate formats. In the next sprints I will specify some data formats.
>
> **Question:**  When renting, does the owner have any space to clarify any prohibitions or obligations with the property?
>
> **Answer:** No.
>
> **Question:**  Does the owner have a limit of requests they can do?
>
> **Answer:** No.
>
> **Question:**  Regarding the property’s photographs, is that considered selected data?
>
> **Answer:** The owner should input the URI of each file/photograph.
>
> **Asked at:** 01:01
>
> **Question:** In a previous question, you answered that, regarding US001 filtering/sorting system, the client should be able to select (from a list) the type of business, the type of property, and the number of rooms. Does this apply too to the process of an owner submitting a request? Or does the owner has to type these characteristics?
>
> **Answer:** Yes.
>
> **Asked at:** 21:46


> **Date:** Tuesday, March 28 of 2023
>
> **Question:**  Should it be possible to change each value/detail on its own, or would the only options be to cancel or submit the request?
>
> **Answer:**  If there are any errors, the application should allow the owner to correct the errors.
>
> **Asked at:** 11:27
>
> **Question:**  In one of the previous questions you have stated that for now the only way that an agent can receive the sale announcement request is through a phone call. However, US004 states that "As an owner, I intend to submit a request for listing a property sale or rent, choosing the responsible agent". Isn't submitting a request for listing a property the same as a sale announcement request? If not, can you clarify?
>
> **Answer:**  In my previous answer, when I said "...the only way that an agent can receive the sale announcement..." I was talking about the agent as an actor of the system that introduces in the system property data.
>
> **Asked at:** 11:28

> **Date:** Friday, April 14 of 2023
>
> **Question:**  It was previously stated that an unregistered user could do a property listing request. However, with the introduction of US007, I want to clarify and make sure that now a user needs to be registered in order to buy, sell or rent properties, or if they can still do it unregistered.
>
> **Answer:** In Sprint B we introduce US7 and now, in US4, the owner needs to be registered in the system to submit a request for listing. You should update all artifacts to include this change.
>
> **Asked at:** 19:13


> **Date:** Wednesday, April 19 of 2023
>
> **Question:** In the project description it is mentioned that in the case of a request for the sale of a property, the owner must provide "one or more photographs". Taking that into account, is there a maximum number of photos that can be submitted when publishing an announcement? If so, how many?
>
> **Answer:** The maximum number of photos is 30.
>
> **Asked at:** 10:02

### 1.3. Acceptance Criteria


* **AC1:**  Real Estate USA needs to sell or rent the property for the price requested or as close to it as possible, within a specific timeframe. 


### 1.4. Found out Dependencies

No dependencies were found till this point of the project

### 1.5 Input and Output Data


**Input Data:**

* Typed data:
    * a name,
    * a citizen´s card number,
    * a tax number,
    * an adress,
    * an email adress,
    * a telefone number,
    * an agency,
    * an agent
    * the requested property characteristics

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative One](svg/us04-system-sequence-diagram.svg)