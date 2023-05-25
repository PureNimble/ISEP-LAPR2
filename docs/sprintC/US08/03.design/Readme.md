# US 006 - To create a Task 

## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID                                                                                 | Question: Which class is responsible for...                                                        | Answer                               | Justification (with patterns)                                                                                 |
|:-----------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------|:-------------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1(asks to see the list of property announcement request)                                  | ... interacting with the actor?                                                                    | PublishAnnouncementRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                                                                                                | ... coordinating the US?                                                                           | PublishAnnouncementRequestController | Controller                                                                                                    |
|                                                                                                | ...knowing and obtaining the email of the agent responsible for that announcement request?         | User session                         | IE: knows/has the users on the system                                                                         |
|                                                                                                | ...obtaining the employee by email?                                                                | EmployeeRepository                   | IE:knows/has the employees                                                                                    |
|                                                                                                | ...obtaining the announcement request list?                                                        | AnnouncementRequestRepository        | IE: knows/has its own AnnouncementRequests,Pure Fabrication                                                   |
|                                                                                                | ...obtaining the announcement request Dto list                                                     | AnnouncementRequestMapper            | IE:Kowns/has its own AnnouncementRequestDto,Pure Fabrication,High coesion Low Coupling                        |
|                                                                                                | ...obtaining the announcement request list?                                                        | AnnouncementRequestRepository        | IE: knows/has its own Employees,Pure Fabrication                                                              |
| Step 2: (shows list of announcement request responsible for that agent and asks to select one) | ...displaying the list of announcements?                                                           | PublishAnnouncementRequestUI         | Pure Fabrication                                                                                              |
|                                                                                                | ...obtaining the comission list?                                                                   | ComissionRepository                  | Information Expert,Pure Fabrication                                                                           |
| Step 3: (selects the announcement request)                                                     | ...validating selected data?<br/><br/>...temporarily keeping the announcement request description? | PublishAnnouncementRequestUI         | Pure Fabrication                                                                                              |
| Step 4: (shows the information of the announcement request selected and asks for confirmation) | ...display all the information before submitting?                                                  | PublishAnnouncementRequestUI         | Pure Fabrication                                                                                              |
| Step 5: (submits data)                                                                         | ...obtaining and creating the announcement request Dto by description                              | AnnouncementRequestMapper            | IE,Creator                                                                                                    |
|                                                                                                | ...obtaining and creating the comission by description                                             | ComissionRepository                  | IE,Creator                                                                                                    |
|                                                                                                | ...creating the publish announcement                                                               | PublishAnnouncementRepository        | IE,Creator                                                                                                    |
|                                                                                                | ...validating the data locally(mandatory data)?                                                    | PublishAnnouncement                  | IE                                                                                                            |
|                                                                                                | ...adding to a collection<br/>and globally<br/>validating duplicated records                       | PublishAnnouncementRepository        | IE                                                                                                            |
| Step 6: (shows opperation success)                                                             | ...informing operation success?                                                                    | PublishAnnouncementRequestUI         | Pure Fabrication                                                                                              |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * PublishAnnouncement

Other software classes (i.e. Pure Fabrication) identified: 

 * PublishAnnouncementRequestUI
 * PublishAnnouncementRequestController
 * AnnouncementRequestMapper

## 3.2. Sequence Diagram (SD)

### Alternative 1 - Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/US08-sequence-diagram-full.svg)

### Alternative 2 - Split Diagram

This diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses interaction ocurrence.

![Sequence Diagram - split](svg/US08-sequence-diagram-split.svg)

**Get Comission Partial SD**

![Sequence Diagram - Partial - Comission](svg/US08-sequence-diagram-partial-getComission.svg)

**Create Published Announcement Partial SD**

![Sequence Diagram - Partial - Create Published Announcement](svg/US08-sequence-diagram-partial-createPublishedAnnouncement.svg)

**Get Announcement Request Partial SD**

![Sequence Diagram - Partial - Get Announcement Request](svg/us08-sequence-diagram-partial-getAnnouncementRequest.svg)

**Get Announcement Request List By Most Recent Partial SD**

![Sequence Diagram - Partial - Get Announcement Request List By Most Recent](svg/us08-sequence-diagram-partial-getAnnouncementRequestListByMostRecent.svg)

**Get Announcement Request Dto List Patial SD**

![Sequence Diagram - Partial - Get Announcement Request Dto List](svg/us08-sequence-diagram-partial-getAnnouncementRequestListDTO.svg)


**Get Comission List Partial SD**

![Sequence Diagram - Partial - Get Comission List](svg/us08-sequence-diagram-partial-getComissionList.svg)

**Get Employee By Email Partial SD**

![Sequence Diagram - Partial - Get Employee By Email](svg/us08-sequence-diagram-partial-getEmployeeByEmail.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us08-class-diagram.svg)