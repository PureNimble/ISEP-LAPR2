### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID                                        | Question: Which class is responsible for...                                         | Answer                     | Justification (with patterns)                                                                                 |
|:------------------------------------------------------|:------------------------------------------------------------------------------------|:---------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1(asks to register an employee)                  | ... interacting with the actor?                                                     | RegisterEmployeeUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                                                       | ... coordinating the US?                                                            | RegisterEmployeeController | Controller                                                                                                    |
|                                                       | ... instantiating a new Employee?                                                   | Store                      | Creator (Rule 1): in the DM Store has a Employee.                                                             |
|                                                       | ...obtaining the roles list?                                                        | RoleRepository             | IE: knows/has its own Employees,Pure Fabrication                                                              |
| Step 2: shows list of roles and asks to select        | ...displaying the list of roles?                                                    | RegisterEmployeeUI         | Pure Fabrication                                                                                              |
|                                                       | ...obtaining the stores list?                                                       | StoreRepository            | Information Expert,Pure Fabrication                                                                           |
| Step 3: selects  roles of an employee                 | ...validating selected data?<br/><br/>...temporarily keeping the role description?  | RegisterEmployeeUI         | Pure Fabrication                                                                                              |
| Step 4: shows list of stores and asks to select one   | ...displaying the list of stores?                                                   | RegisterEmployeeUI         | Pure Fabrication                                                                                              |
|                                                       | ...obtaining the states list?                                                       | StateRepository            | Information Expert,Pure Fabrication                                                                           |
| Step 5: selects a store                               | ...validating selected data?<br/><br/>...temporarily keeping the store description? | RegisterEmployeeUI         | Pure Fabrication                                                                                              |                                                                                                                          |                            |                                                                                                               |
| Step 6: show list of states and asks to select one    | ...displaying the list of states?                                                   | RegisterEmployeeUI         | Pure Fabrication                                                                                              |
|                                                       | ...obtaining the district list?                                                     | StateRepository            | Information Expert,Pure Fabrication                                                                           |
| Step 7: select state                                  | ...validating selected data?<br/><br/>...temporarily keeping the state description? | RegisterEmployeeUI         | Pure Fabrication                                                                                              |
| Step 8: show list of districts and asks to select one | ...displaying the list of districts?                                                | RegisterEmployeeUI         | Pure Fabrication                                                                                              |
|                                                       | ...obtaining the cities list?                                                       | StateRepository            | InormationExpert,Pure Fabrication                                                                             |
| Step 9: select district                               | ...validating selected data?<br/><br/>...temporarily keeping the state description? | RegisterEmployeeUI         | Pure Fabrication                                                                                              |
| Step 10: show list of cities and asks to select one   | ...displaying the list of districts?                                                | RegisterEmployeeUI         | Pure Fabrication                                                                                              |
| Step 11: select city                                  | ...validating selected data?<br/><br/>...temporarily keeping the state description? | RegisterEmployeeUI         | Pure Fabrication                                                                                              |
| Step 12: requests data                                | ...displaying the UI for the actor to input data?                                   | RegisterEmployeeUI         | Pure Fabrication                                                                                              |
| Step 13: types requested data                         | ...validating input data?<br/><br/>...temporarily keeping input date?               | RegisterEmployeeUI         | Pure Fabrication                                                                                              |
| Step 14: shows information of an employee             | ...display all the information before submitting?                                   | RegisterEmployeeUI         | Pure Fabrication                                                                                              |
| Step 15: submits data                                 | ...creating Employee Object                                                         | Store                      | Creator<br/>R: 1,2                                                                                            |
|                                                       | ...obtaining and creating Store Object                                              | StoreRepository            | IE:Kowns/has its own stores,Creator:Contains instances of store                                               |
|                                                       | ...obtaining and creating State Object                                              | StateRepository            | IE:Kowns/has its own states,Creator:Contains instances of states                                              |
|                                                       | ...obtaining and creating District Object                                           | StateRepository            | IE:Kowns/has its own districts,Creator:Contains instances of districts                                        |
|                                                       | ...obtaining and creating City Object                                               | StateRepository            | IE:Knows/has its own cities,Creator:Contains instances of cities                                              |
|                                                       | ...creating and sending a new email                                                 | AuthenticationRepository   | Creator:contains passwordGenerator method                                                                     |
|                                                       | ...validating the data locally(mandatory data)?                                     | Employee                   | IE:knows its own data                                                                                         |
|                                                       | ...adding to a collection<br/>and globally<br/>validating duplicated records        | Store                      | IE:Knows all Employees instances                                                                              |
| Step 16: shows opperation success                     | ...informing operation success?                                                     | RegisterEmployeeUI         | Pure Fabrication                                                                                              |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Store
* Employee

Other software classes (i.e. Pure Fabrication) identified:

* RegisterEmployeeUI
* RegisterEmployeeController


## 3.2. Sequence Diagram (SD)

### Alternative 1 - Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us03-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us03-class-diagram.svg)