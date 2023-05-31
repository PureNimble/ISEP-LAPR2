# US 20 - To read the response for the appointment request, to accept or reject it. 

## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for... | Answer               | Justification (with patterns)                                                                                 |
|:-------------  |:--------------------- |:---------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		 |	... interacting with the actor? | CreateTaskUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		 |	... coordinating the US? | CreateTaskController | Controller                                                                                                    |
| 			  		 |	... instantiating a new Task? | Organization         | Creator (Rule 1): in the DM Organization has a Task.                                                          |
| 			  		 | ... knowing the user using the system?  | UserSession          | IE: cf. A&A component documentation.                                                                          |
| 			  		 |							 | Organization         | IE: knows/has its own Employees                                                                               |
| 			  		 |							 | Employee             | IE: knows its own data (e.g. email)                                                                           |
| Step 2  		 |							 |                      |                                                                                                               |
| Step 3  		 |	...saving the inputted data? | Task                 | IE: object created in step 1 has its own data.                                                                |
| Step 4  		 |	...knowing the task categories to show? | System               | IE: Task Categories are defined by the Administrators.                                                        |
| Step 5  		 |	... saving the selected category? | Task                 | IE: object created in step 1 is classified in one Category.                                                   |
| Step 6  		 |							 |                      |                                                                                                               |              
| Step 7  		 |	... validating all data (local validation)? | Task                 | IE: owns its data.                                                                                            | 
| 			  		 |	... validating all data (global validation)? | Organization         | IE: knows all its tasks.                                                                                      | 
| 			  		 |	... saving the created task? | Organization         | IE: owns all its tasks.                                                                                       | 
| Step 8  		 |	... informing operation success?| CreateTaskUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Organization
 * Task

Other software classes (i.e. Pure Fabrication) identified: 

 * CreateTaskUI  
 * CreateTaskController


## 3.2. Sequence Diagram (SD)

### Alternative 1 - Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us006-sequence-diagram-full.svg)

### Alternative 2 - Split Diagram

This diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses interaction ocurrence.

![Sequence Diagram - split](svg/us006-sequence-diagram-split.svg)

**Get Task Category List Partial SD**

![Sequence Diagram - Partial - Get Task Category List](svg/us006-sequence-diagram-partial-get-task-category-list.svg)

**Get Task Category Object**

![Sequence Diagram - Partial - Get Task Category Object](svg/us006-sequence-diagram-partial-get-task-category.svg)

**Get Employee**

![Sequence Diagram - Partial - Get Employee](svg/us006-sequence-diagram-partial-get-employee.svg)

**Create Task**

![Sequence Diagram - Partial - Create Task](svg/us006-sequence-diagram-partial-create-task.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us006-class-diagram.svg)