# US 012 - As a system administrator, I want to import information from a legacy system that has been in use in several agencies.

## 1. Requirements Engineering


### 1.1. User Story Description


As a system administrator, I want to import information from a legacy system that has been in use in several agencies.


### 1.2. Customer Specifications and Clarifications


**From the specifications document:**

>	The company's systems administrator will be responsible for registering all employees (specifying
the name, the citizen's card number, the tax number, the address, the email address, the contact
telephone number and the agency to which it is assigned) and branches of the network (specifying
the designation, the location, the email address and the contact telephone number) as well as
preparing the system in order to facilitate the insertion of advertisements and facilitate the use of the
application.

> All the images/figures produced during the software development process should be recorded in
SVG format.

**From the client clarifications:**

> **Question:** Can the System Administrator, when wanting to import information from a legacy system, send more than one file at once?
>
>  **Answer:** Only one file at a time.
>
> **Date:** 3 de May de 2023


> **Question:**  In the CSV containing the data from the legacy system, properties classified as Apartment have themselves a value for Sun Exposure. However, the specification document describes this attribute as exclusive to the property type House: "In case the property is a house, the existence of a basement, an inhabitable loft, and sun exposure must be registered as well.". Do we add Sun Exposure to the apartment's attributes or does it stay exclusive to house?
>
>  **Answer:** When loading an apartment from the leagacy system (from the CSV file) you should ignore the attribute Sun Exposure. The CSV file contains data exported from a legacy system. The legacy system does not registers the same information that the system that you are developing now registers. For instance, the legacy system does not associates an agent to a property, therefore, when importing data from a legacy system you should create a agent/employee having:name=Legacy Agent; passport card number=000000000; tax number=000000000, email address=legacy@realstateUSA.com; contact telephone number = 0000000000; and associate this "legacy agent" with each property (make it the property responsable agent). Moreover, you should prepare your system to accept two formats for the passport number, one with 9 numbers and the other starts with the letter C followed by eight numbers.
>
> **Date:** 3 de May de 2023


> **Question:** It has been clearly previously established that there are two types of commissions (fixed and percentage). However, the CSV containing the data from the legacy system only has one column that references any type of commission: column U "commission(%)". Does that mean that there is only one type of commission, or was the fixed type accidentally left out or did I fail to notice the fixed commission type?
>
>  **Answer:** In the past our company only had the type of commissions that you see in the CSV file. Our legacy system has many limitations and this is why we are asking you to develop a new system.
>
> **Date:** 3 de May de 2023


> **Question:** If the System Administrator is an Employee, and the Employee is register by the System Administrator, who registers the System Administrator ?
>
>  **Answer:** When the system starts for the first time, a System Administrator should already be registered in the system.
>
> **Date:** 5 de May de 2023


> **Question:** In the legacy File provided, on the Location, I assume that the structure of the cell is: Address, City, State, and postal code. Assuming that this is the right structure, shouldn't the districts of the cities be there as well?
>
>  **Answer:** Please check carefully the legacy file. For instance, in line 5 we get "71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR,  AK, 99705". When loading the data, you should consider location addresses with and without district info.
>
> **Date:** 8 de May de 2023


> **Question:** From the provided CSV file, our team infers that this feature is meant to import data related to announcements only and not other data like, for example, employee information. Is this correct?
>
>  **Answer:** The new system should allow the System Admininstrator to import CSV files like the one provided in moodle. The legacy system is not able to export any other fields/attributes or formats.
>
> **Date:** 8 de May de 2023


> **Question:** Also, in relation to AC2, can we assume that a CSV file is any file whose filename ends with ".csv"?
>
>  **Answer:** The System Administrator should be able to load any file with the extension csv. The file content must be validated, showing a message to the system
administrator if the file is empty or its content is not in the requested format.
>
> **Date:**  8 de May de 2023


### 1.3. Acceptance Criteria


* **AC1:** The system administrator must be able to choose a file to import.
* **AC2:** The system should only accept CSV files.
* **AC3:** The file content must be validated, showing a message to the system administrator if the file is empty or its content is not in the requested format.
* **AC4:** The import operation, when successful, should trigger a success message to the system administrator.


### 1.4. Found out Dependencies


* There is a dependency to "US004 Submit a request for listing a property sale or rent." since the System Administrator can import a file with Announcements Resquest Information.
* There is a dependency to "US003 As a system administrator, I want to register a store." since System Administrator has to import the information, this System Administrator must be registered in a store in order to do that.
* There is a dependency to "US002 Publish any sale announcement on the system" since the System Administrator can import a file with Published Announcements Information.



### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	
* Selected data:
	* The imported information


**Output Data:**

* The status of the file
* A a success message
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

