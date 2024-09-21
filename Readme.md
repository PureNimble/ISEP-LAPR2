# Project Template

This is the project template for LEI's second semester project 2022/2023.

It contains didactic artifacts relevant to the integrative project to be developed during the second semester of the academic year of 2021-2022 in the [Degree in Informatics Engineering (LEI)](http://www.isep.ipp.pt/Course/Course/26) from [Instituto Superior de Engenharia do Porto (ISEP)](http://www.isep.ipp.pt).

In particular, it has:

* [The team members and task assignment during Sprints](docs/Readme.md)
* A template to capture and systematize evidence of applying properly the Software Development Process, namely regarding the activities of Engineering Requirements, Analysis and Design
* Source code available to students as a starting point
* General description of how the provided application works (and it is structured).

## Application Overview

WIP

# How to generate the svg files

On project root folder, run the following script:

Remarks: it works for Linux and MacOS. For Windows, you have to adapt the script.

```shell
$ bin/generate-plantuml-diagrams.sh
```

# How the project is organised

This project used Java and Maven.

We have to declare the maven-surefire-plugin in the pom.xml file and configure the dependencies of this plugin. 

We have to declare the following dependencies:

The junit-platform-surefire-provider dependency allows us to run tests that use either the “old” JUnit (3 or 4) or JUnit 5.

If we want to run tests that use JUnit 5, we have to declare the junit-jupiter-engine dependency.

* Junit Jupiter Dependency graph
    - https://junit.org/junit5/docs/current/user-guide/
* JUnit Annotation
    - https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations


## Maven goals
### Run the unit tests
```
mvn clean test
```

### Generate javadoc for the source code
```
mvn javadoc:javadoc
```

### Generate javadoc for the test code
```
mvn javadoc:test-javadoc
```

### Generate Jacoco source code coverage report
```
mvn test jacoco:report
```

### Check if thresholds limits are achieved
```
mvn test jacoco:check
```

### Generates a PIT Mutation coverage report to target/pit-reports/YYYYMMDDHHMI
```
mvn org.pitest:pitest-maven:mutationCoverage
```

### Generates a quicker PIT Mutation coverage report to target/pit-reports/YYYYMMDDHHMI
```
mvn org.pitest:pitest-maven:mutationCoverage -DwithHistory
```

### Complete example

``` 
mvn test javadoc:javadoc jacoco:report org.pitest:pitest-maven:mutationCoverage -DhistoryInputFile=target/fasterPitMutationTesting-history.txt -DhistoryOutputFile=target/fasterPitMutationTesting-history.txt -Dsonar.pitest.mode=reuseReport -Dthreads=4 -DtimestampedReports=false
```
## Jacoco dependencies
* https://github.com/pitest/pitest-junit5-plugin
  - https://mvnrepository.com/artifact/org.pitest/pitest-junit5-plugin
    - required to work with JUnit5

# Who do I talk to about this project

Nuno Bettencourt

