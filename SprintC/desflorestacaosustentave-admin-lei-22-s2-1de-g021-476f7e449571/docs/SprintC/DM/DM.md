# OO Analysis #

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used. 

## Rationale to identify domain conceptual classes ##
To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development". 


### _Conceptual Class Category List_ ###

**Product/Service related to a Transaction or Transaction Line Item**

* Vaccination

---

**Roles of People or Organizations**

* Administrator
* Employee
* Nurse
* Receptionist at vaccination center
* Receptionist at healthCare center
* Center Coordinator
* SNS User

---

**Places**

* Vaccination Center
* Waiting room
* Recovery Room
* HealthCare Center
* Vaccination room

---

**Noteworthy Events**

* Vaccine schedule
* Vaccination event

---


**Physical Objects**

* Vaccine

---

**Descriptions of Things**



---

**Organizations**

* SNS

---

**Documents mentioned/used to perform some work/**

* Vaccination Certificate
* Relatories from vaccination

---

###**Rationale to identify associations between conceptual classes** ###

An association is a relationship between instances of objects that indicates a relevant connection and that is worth of remembering, or it is derivable from the List of Common Associations: 

+ **_Organization_** owns **_Vaccination center_**
+ **_Organization_** **owns** **_HealthCare center_**
+ **_Center coordinator_** **creates relatories of** **_Vaccination center_**
+ **_Employee_** works for **_Vaccination Center_**
+ **_Nurse_** performs **_Vaccination event_**
+ **_Vaccination center receptionist_** **checks for** **_Vaccine schedule_**
+ **_HealthCare center receptionist_** **works for** **_HealthCare center_**
+ **_HealtCare center receptionist_** schedules **_Vaccine schedule_**
+ ****_Vaccination certificate_** validates **_Vaccination event_****
+ **_SNS User_** recivies **_Notification_**
+ ****_SNS user_** can request **_Vaccination certificate_****
+ ****_Vaccination event_** fullfiling **_Vaccine schedule_**** 
+ **_Waiting room_** for **_SNS user_**
+ **_Recovery room_** for **_SNS user_**
+ ****_SNS user_** schedules **_Vaccine schedule_****
+ **_Vaccine_** of **_Vaccination event_**
+ ****_Vaccination center_** contains **_Vaccine_****
+ ****_Nurse_** administer **_Vaccine_****
+ ****_Recovery room_** from **_Vaccination center_****
+ ****_Waiting room_** from **_Vaccination center_****
+ ****_Nurse_** sends patien to **_Recovery room_****
+ ****_Vaccination center receptionist_** sends patient to **_Waiting room_****
+ ****_Administrator_** works for **_Organization_****
+ ****_Administrator_** can specify new **_Vaccine_****
+ ****_Administrator_** register **_Employee_****



| Concept (A) 		                  |    Association   	    |             Concept (B) |
|---------------------------------|:---------------------:|------------------------:|
| Organization 	                  |       owns    	       |      Vaccination center |
| Organization                    |         owns          |       HealthCare center |
| Center coordinator              | creates relatories of |      Vaccination center |
| Employee                        |       works for       |      Vaccination center |
| Nurse                           |       performs        |       Vaccination event |
| Vaccination center receptionist |      checks for       |        Vaccine schedule |
| HealthCare center receptionist  |       works for       |       HealthCare center |
| HealthCare center receptionist  |       schedules       |        Vaccine schedule |
| Vaccination certificate         |       validates       |       Vaccination event |
| SNS user                        |       recieves        |            Notification |
| SNS user                        |      can request      | Vaccination certificate |
| Vaccination event               |      fullfiling       |        Vaccine schedule |
| Waiting room                    |          for          |                SNS user |
| Recovery room                   |          for          |                SNS user |
| SNS user                        |       Schedules       |      Vaccinete schedule |
| Vaccine                         |          of           |       Vaccination event |
| Vaccination center              |       contains        |                 Vaccine |
| Nurse                           |      administer       |                 Vaccine |
| Recovery room                   |         from          |      Vaccination center |
| Waiting room                    |         from          |      Vaccination center |
| Nurse                           |   sends patient to    |           recovery room |
| Vaccination center receptionist |   sends patient to    |            Waiting room |
| Administrator                   |       works for       |            Organization |
| Administrator                   |    can specify new    |                 Vaccine |
| Administrator                   |       register        |                Employee |


## Domain Model

**Do NOT forget to identify concepts atributes too.**

![DM.svg](DM(SprintC).svg)