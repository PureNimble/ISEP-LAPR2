# US 04 - Submit a request for listing a property sale or rent

## 1. Requirements Engineering

### 1.1. User Story Description

As an owner, I intend to submit a request for listing a property sale or rent,choosing the responsible agent.


### 1.2. Customer Specifications and Clarifications


**From the specifications document:**

> The owner will contact Real Estate USA, to sell or rent their properties. From time to time, property owners contact Real Estate USA to sell or rent their properties. The owner provides property characteristics and the requested price and sends the request to an agent. 

**From the client clarifications:**

> **Question:** Should it be possible to change each value/detail on its own, or would the only options be to cancel or submit the request?

> **Answer:** If there are any errors, the application should allow the owner to correct the errors.

> **Question:** In the case of listing a land property, shall the owner refer if there is a building permit already approved?

> **Answer:** No.

> **Question:** According to the Project Description, the agent when selling a property can charge a flat price comission or a percentage of the sale value, my question here is wether there is a minimum and/or a maximum to each of these types of comissions?

> **Answer:** There is no maximum and the minimum is 0.

> **Question:** When assigning an agent to a property listing, are the available agents shown by the system for the owner to pick? Or does the owner need to provide the agent's information (name, agency,etc)?

> **Answer:** The owner should select one agent from a list of agents that work in the selected agency. The owner should select the agency before selecting the agent.

> **Question:** Does an owner need to be registered in the system to submit a request for a property listing?

> **Answer:** No. When making the request to list a property, the owner should introduce his own data. The Owner attributes are: the name, the citizen's card number, the tax number, the address, the email address and the telephone number.

> **Question:** Is it possible to submit multiple listing for the same property and type of listing?

> **Answer:** No.

> **Question:** In the Project description, there are only specifications for a Sale. What are the required characteristics for a rental?

> **Answer:** The caracteristics for a rental are the same as the ones for the sale of a property. The rent value is per month. Additionally, we have to define the contract duration.


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

