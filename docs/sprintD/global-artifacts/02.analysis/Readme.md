# Analysis

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used. 

## Rationale to identify domain conceptual classes ##
To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development". 


### _Conceptual Class Category List_ ###

**Business Transactions**

* Bussiness
* Offer

---

**Transaction Line Items**

* House
* Property
* Residence


---

**Product/Service related to a Transaction or Transaction Line Item**

* Offer
* Message
* PublishedAnnouncement
* AnnouncementRequest


---


**Transaction Records**

*

---


**Roles of People or Organizations**

* Unregistered User
* Client
* Person
* Employee
* Agent
* SystemAdministrator
* StoreNetworkManager
* StoreManager


---


**Places**

* State
* District
* City
* Adderss


---

**Noteworty Events**

*

---


**Physical Objects**

* House
* Residence
* Store
* Property


---


**Descriptions of Things**

* Sun Exposure
* AvailableEquipment
* Photos
* Address
* TypeOfProperty
* Roles
* Bussiness


---


**Catalogs**

*  Property
* House
* Residence
* PublishedAnnouncement
* AnnouncementRequest

---


**Containers**

*  Person

---


**Elements of Containers**

* Property
* House
* Residence
* Message
* Offer
* Store
* Unregistered User
* Client
* Person
* Employee
* Agent
* SystemAdministrator
* StoreNetworkManager
* StoreManager

---


**Organizations**

* Store

---

**Other External/Collaborating Systems**

*  


---


**Records of finance, work, contracts, legal matters**

*

---


**Financial Instruments**

*  Bussiness

---


**Documents mentioned/used to perform some work/**

* Contacts
* File
---



###**Rationale to identify associations between conceptual classes**###

An association is a relationship between instances of objects that indicates a relevant connection and that is worth of remembering, or it is derivable from the List of Common Associations: 

+ **_A_** is physically or logically part of **_B_**
+ **_A_** is physically or logically contained in/on **_B_**
+ **_A_** is a description for **_B_**
+ **_A_** known/logged/recorded/reported/captured in **_B_**
+ **_A_** uses or manages or owns **_B_**
+ **_A_** is related with a transaction (item) of **_B_**
+ etc.



| **_Concept (A)_**          | **_Association_**                  |     **_Concept (B)_** |                                       
|:---------------------------|:-----------------------------------|----------------------:| 
| Address                    | has                                |                 State |
| Agent                      | accepts/declines                   |                 Offer |
| Agent                      | publishes                          | PublishedAnnouncement |
| Agent                      | is an                              |              Employee | 
| Agent                      | lists and responds to a \n +sender |               Message | 
| Agent                      | defines                            |             Comission |
| Agent                      | receives a \n +receiver            |               Message |            
| Agent                      | reviews                            |   AnnouncementRequest |                
| AnnouncementRequest        | has a                              |              Property |
| Business                   | has                                |               Address |                             
| Business                   | has                                |             Comission |                          
| Business                   | has                                | PublishedAnnouncement |              
| Business                   | has                                |   AnnouncementRequest |
| Client                     | addresses                          |                 Store |
| Client                     | has                                |               Address |
| Client                     | makes                              |                 Offer |
| Client                     | receives a \n +receiver            |               Message |
| Client                     | requests                           |              Business |
| Client                     | has an                             |               Address |
| Client                     | is an                              |                Person |
| District                   | has                                |                  City |
| Employee                   | has a                              |                  Role |
| Employee                   | works in                           |                 Store |
| Employee                   | belongs to                         |                Person |
| File                       | imported by                        |   SystemAdministrator |
| House                      | contains                           |           SunExposure |
| House                      | is a                               |             Residence |
| Person                     | can have                           |               Address |
| Person                     | has                                |              Contacts |
| Property                   | has                                |               Address |
| Property                   | has                                |                Photos |
| PublishedAnnouncement      | advertises                         |              Property |
| PublishedAnnouncement      | has                                |              Business |
| PublishedAnnouncement      | is a                               |   AnnouncementRequest |   
| PublishedAnnouncement      | can have a                         |               Message | 
| PublishedAnnouncement      | has                                |                 Offer | 
| PublishedAnnouncementState | has                                | PublishedAnnouncement |
| Rent                       | is  a                              |              Business |
| Residence                  | contains                           |    AvailableEquipment |
| Residence                  | is a                               |              Property |
| State                      | has                                |              District |
| Store                      | has                                |              Contacts |
| Store                      | located at                         |               Address |
| StoreManager               | analyses                           | PublishedAnnouncement |
| StoreManager               | is an                              |              Employee |
| StoreManager               | analyses sold                      | PublishedAnnouncement |
| StoreManager               | intends to see                     |                 Offer |
| StoreNetworkManager        | is an                              |              Employee | 
| SystemAdministrator        | adds                               |                  City |
| SystemAdministrator        | adds                               |              District |
| SystemAdministrator        | adds                               |                 State |
| SystemAdministrator        | registers                          |                 Store |
| SystemAdministrator        | is an                              |              Employee |
| TypeOfProperty             | defines                            |              Property |
| UnregisteredUseR           | can register as                    |                Client |
        

## Domain Model

**Do NOT forget to identify concepts atributes too.**

**Insert below the Domain Model Diagram in a SVG format**

![Domain Model](svg/project-domain-model.svg)



