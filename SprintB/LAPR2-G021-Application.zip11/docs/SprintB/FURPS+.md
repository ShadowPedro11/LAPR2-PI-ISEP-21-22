# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _Printing: All the images/figures produced during the software development process should be recorded in SVG format._
- _Security: Password authentication for every user. The password must hold seven alphanumeric characters, including three capital letters and two digits. Also, only the nurses are allowed to access all users' health data._
- _Help: Must have a user manual._
- _Statistics and charts viewer: the coordinator wants to see statistics and charts, to evaluate the performance of Analyze data from other centers (including data from law systems)._
- _Analyze data from other centers (including data from law systems)._

## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

- _Adequacy of the interface for different types of users._
## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._ 

- _Adaptability/configurability: Needs to be able to be adapted easily to support managing other future pandemic events requiring a massive vaccination of the population. Also, it can be further commercialized to other companies and/or organizations and/or healthcare systems besides DGS._	 
- _Needs to support, at least, Portuguese and English._
- _**The applcation needs should run on Microsoft Windows, macOS and several Unix-like OS'.**_

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._
  
- _The application must be developed in Java language._

### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

- _The application must be developed using the IntelliJ IDE or NetBeans._
- _The application graphical interface is to be developed in JavaFX 11._
- _The system must have tests for all methods, except for methods that implement Input/Output operations._
- _The unit tests should be implemented using the JUnit 5 framework. The JaCoCo plugin should be used to generate the coverage report._
- _Implementation languages (Portuguese and English)._

### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._