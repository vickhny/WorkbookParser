# BETest

## Why?

We are interested in your skills as a developer. As part of our assessment, we want to see your code.

## Instructions

In this repo, you'll find two files, Workbook2.csv and Workbook2.prn. These files need to be converted to a HTML format by the code you deliver. Please consider your work as a proof of concept for a system that can keep track of credit limits from several sources.

This repository is created specially for you, so you can push anything you like. Please update this README to provide instructions, notes and/or comments for us.

## The Constraints

Please complete the test within 5 business days. Use either Java, Scala or Kotlin. Use any libs / tools you like.

## Questions?

If you have any questions please send an email to DL-eCG-NL-assessment@ebay.com.

## Finished?

Please send an email to DL-eCG-NL-assessment@ebay.com let us know you're done.

Good Luck!

## Credit Limit Tracker System

The assignment is to implement a system that can keep track of credit limits from several sources. The Credit limit tracker system will receive input file in CSV and PRN format from several sources. 

## Design Assumptions

* The Credit limit tracker system will receive N numbers of input files.  
Where, N >= 1 and,  
       Supported file format : `CSV` and `PRN`

* Each file should have credit limit records. Each record will contain Name, Address, Postcode, Phone, Credit Limit and Birthday as single line record.

* Credit Limit attribute from each file either will be in `EURO` or `CENT`.  

* If we are receiving same records from different files then we can perform credit limit inequality check on those records to verify the credit conflicts.


Given two sample input files are:

1. `Workbook2.csv`
2. `Workbook2.prn`
 

## My Implementation

* It's built for **SpringBoot 2.4.2**

## Requirement
* Java 11
* Spring Boot
* Maven
* Swagger API Documentation


## How to Run application

* Build the project by running `mvn clean package` inside root directory.
* Once successfully built, run the service by using the following command:

**Before running app, ensure that port 8090 is free. One can use `server.port` system property in `application.yml` to override default 8090 port**

Go into repo `BeTest_Vikash_Kumar` root and run:

```
./mvnw spring-boot:run
```
The command downloads maven(if required, only first time), builds and runs workbook web-app. If everything goes well, last line in the console will be

```
 INFO 8407 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8090 (http)
 INFO 8407 --- [           main] com.marktplaats.assignment.AssignmentApplication  : Started AssignmentApplication in 2.272 seconds (JVM running for 4.669)
```

You can also run the application by using below command from root:

```
java -jar  target/CreditLimitTracker-0.0.1-SNAPSHOT.jar
```

To use API by running it locally you can use `Postman` and attached postman collection. You can find postman collection file inside root directory.

```
Credit Limit Tracker.postman_collection.json
```

Steps-
1. Open Postman
2. Use attached postman  collection or use below mentioned rest end points.
3. Attach input file in form data as shown in `REST APIs Endpoints` images.
4. Make the request and to view the response in proper html format select `preview` as shown in below screenshot.
![Alt text](htmlresponse.png?raw=false "Optional Title") <!-- .element height="50%" width="50%" -->


## REST APIs Endpoints


1. Upload files and convert it to HTML
```
POST http://localhost:8090/convertToHtml
Accept: */*
Content-Type: multipart/form-data
```
![Alt text](converttohtml.png?raw=true "Optional Title")


2. Upload files, convert to html and find conflicts
```
POST http://localhost:8090/findConflicts
Accept: */*
Content-Type: multipart/form-data
```
![Alt text](findconflicts.png?raw=true "Optional Title")

## Error handling
* If line in any input files could not be parsed, it is logged and ignored.
* If a file resource could not be loaded, it is logged and ignored. Loading of other resources will not impacted.


## Get information about system health
```
http://localhost:8090/actuator/health

```

## To view Swagger 2 API docs
```
Run the server and browse to - http://localhost:8090/swagger-ui.html#/Credit_Limit_Tracker_System
```
![Alt text](swagger.png?raw=true "Optional Title")


Copyright (C) 2001 - 2021 by Marktplaats BV an Ebay company. All rights reserved.


