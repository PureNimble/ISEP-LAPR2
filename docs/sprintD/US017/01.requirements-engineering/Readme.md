# US 17 - To list all deals made

## 1. Requirements Engineering


### 1.1. User Story Description


As a network manager, I want to list all deals made


### 1.2. Customer Specifications and Clarifications


**From the client clarifications:**

> **Question:** What should be the default order of the deals when displaying them to the network manager?
>
> **Answer:** ****
>
> **Date:** Monday, 29 de May de 2023 às 14:13

> **Question:** In this User Story it is requested that "All deals made" are listed. Are these deals just accepted purchase requests, or are declined purchase requests also included?
>  
> **Answer:** ****.
> 
> **Date:** Monday, 29 de May de 2023 às 14:50


### 1.3. Acceptance Criteria


* **AC1:** The actor should be able to sort all properties by property area (square feet)
  in descending/ascending order.
* **AC2:** Two sorting algorithms should be implemented (to be chosen manually by
  the network manager).
* **AC3:** Worst-case time complexity of each algorithm should be documented in the
  application user manual that must be delivered with the application (in the
  annexes, where algorithms should be written in pseudocode).


### 1.4. Found out Dependencies


* There is a dependency to "US10 Place an order to purchase a property" since at least the offer must exist.
* There is a dependency to "US11 Accept or decline a purchase order for a property." since this refers to the deals.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* a sorting criteria,
	
* Selected data:
	* Classifying task category 


**Output Data:**

* List of deals made
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us17-system-sequence-diagram.svg)
