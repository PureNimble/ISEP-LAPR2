# US 007 - Register in the system to buy, sell or rent properties

## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for... | Answer               | Justification (with patterns)                                                                                 |
|:-------------  |:--------------------- |:---------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		 |	... interacting with the actor? | UnregisteredUserUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		 |	... coordinating the US? | UnregisteredUserController | Controller                                                                                                    |
| 			  		 |	... instantiating a new user? | UnregisteredUser         | Creator (Rule 1): in the DM the user is created by the Unregistered User                                                          |
| 			  		 |							 | repository         | IE: knows/has the StateRepository                                                                              |
| 			  		 | ... obtaining the states list?  | StateRepository          | IE: cf. A&A component documentation.                                                                          |
| 			  		 |							 | State             | IE: knows its own data (e.g. name)                                                                           |
| Step 2		 |	...temporarily saving the inputted data? | UnregisteredUserUI                 | Pure Fabrication                                                                |
|   		 |	 | StateRepository               | 	IE: has a list of districts for each state.                                                        |
|   		 |	 | District               | IE: knows its own data (e.g. name)                                                        |
| Step 3	 |	...temporarily saving the inputted data? | UnregisteredUserUI                 | Pure Fabrication                                                   |
|  		 |			...knowing the store city?					 |             StateRepository         |         IE: has a list of cities for each district.                                                                                                    |              
|   		 |   | City                 | IE: knows its own data (e.g. name)                                                                                            |              
| Step 4  		 |	... validating all data (local validation)? | Person                 | IE: owns its data.                                                                                            | 
| 			  		 |	... saving the created Person? | Person         | IE: owns all its persons info.                                                                                       |
| 			  		 |							 | repository         | IE: knows/has the AutheticationRepository                                                                              |
| 			  		 | ... obtaining the password ?  | AutheticationRepository          | Creator:contains passwordGenerator method.                                                                          |
| 			  		 |							 | password             | IE: knows its own data (e.g. name)                                                                           |
| 			  		 |	... saving the created User? | User         | IE: owns all its users info.                                                                                       |
| Step 5  		 |	... informing operation success?| UnregisteredUserUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Person
 * User

Other software classes (i.e. Pure Fabrication) identified: 

 * UnregisteredUserUI  
 * UnregisteredUserController


## 3.2. Sequence Diagram (SD)

### Alternative 1 - Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us07-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us07-class-diagram.svg)