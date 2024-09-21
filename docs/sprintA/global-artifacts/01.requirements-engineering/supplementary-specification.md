# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._


### System Management

* “Each store in the network has a store manager and the set of stores is managed by a store network manager. The main functions of a store manager are to monitor and streamline the branch with the aim of getting to know better the business carried out and to analyze and evaluate the performance of employees.”
* “The company's systems administrator will be responsible for registering all employees (specifying the name, the citizen's card number, the tax number, the address, the email address, the contact telephone number and the agency to which it is assigned) and branches of the network (specifying the designation, location and local manager) as well as preparing the system in order to facilitate the insertion of advertisements and facilitate the use of the application.”
* “The manager of the network intends to analyze the performance of each of the branches and the global behavior of the network on a daily basis.”


### Localization

* “The application must support the English language.”


### Workflow

* “The agent receives the request, checks the availability and sends the response.”
* “If the customer accepts the order, it is automatically scheduled in the system.”


### Reporting

* “Real Estate USA needs an application that enables buyers who visit its stores/agencies to access the properties available for lease or sale, as well as the company's employees to carry out a set of operations related to the real estate business. Among these operations are the publication of rental and sale advertisements, the registration of a business (lease or sale) and the scheduling and registration of visits to the property.”


### Security

* “All those who wish to use the application must be authenticated with a password of seven alphanumeric characters, including three capital letters and two digits.”
* “Sun exposure will take the following values: North, South, East, or West.”
* “The rent value is per month.”


### Printing

* “(…) publishes the offer so that it is visible to all clients who visit the agency and use the application.”


## Usability

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

* The application must be developed in Java language using the IntelliJ IDE or NetBeans.
* The application graphical interface is to be developed in JavaFX 11.
* All those who wish to use the application must be authenticated with a password of seven alphanumeric characters, including three capital letters and two digits.
* During the system development, the team must:
* * adopt best practices for identifying requirements, and for 00 software analysis and design;
* * adopt recognized coding conventions and standards (e.g., CamelCase);
* * use Javadoc to generate useful documentation for Java code.


## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

* The application should use object serialization to ensure data persistence between two runs of the application.
* The development team will implement unit tests for all methods, except for methods that implement Input and/or Output operations.

## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

* “The application should use object serialization to ensure data persistence between two runs of the application.”


## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._

* “The application to be developed in this project will replace an application that was already in operation in some agencies and will be replaced in July 2023.”


## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

| **_Subcategory_**       | **_Function_**                   | **_Description/Example_**                                                          |
|:------------------------|:---------------------------------|------------------------------------------------------------------------------------|
| **Best Practices**      | CamelCase coding standard        | The implementation must adopt recognized coding standards, in this case CamelCase. |
| **Best Practices**      | OO software analysis and design  | The implementation must adopt OO standards.                                        |


### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

| **_Subcategory_**            | **_Function_**                  | **_Description/Example_**                                                              |
|:-----------------------------|:--------------------------------|----------------------------------------------------------------------------------------|
| **Implementation Languages** | Java code base                  | The application must be developed in Java language using the IntelliJ IDE or Netbeans. |
| **Implementation Languages** | JavaFX 11 graphical interface   | The application graphical interface is to be developed in JavaFX 11.                   |
| **Standards Compliance**     | Javadoc documentation           | The Java code must use Javadoc to generate useful documentation.                       |
| **Standards Compliance**     | JUnit 5 testing framework       | The unit tests should be implemented using the JUnit 5 framework.                      |
| **Standards Compliance**     | JaCoCo test coverage plugin     | The JaCoCo plugin should be used to generate the test coverage report.                 |


### Interface Constraints

_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

| **_Subcategory_**            | **_Function_**                  | **_Description/Example_**                                                              |
|:-----------------------------|:--------------------------------|----------------------------------------------------------------------------------------|
| **Implementation Languages** | JavaFX 11 graphical interface   | The application graphical interface is to be developed in JavaFX 11.                   |


### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

| **_Subcategory_**        | **_Function_**   | **_Description/Example_**                                                                                          |
|:-------------------------|:-----------------|--------------------------------------------------------------------------------------------------------------------|
| **Object Serialization** | Data Persistance | •	The application should use object serialization to ensure data persistence between two runs of the application.  |

