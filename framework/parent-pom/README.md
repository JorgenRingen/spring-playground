# Parent Pom

## Purpose
Parent POM inherited by all applications. This parent provides:
* BOM (Bill Of Materials) to ensure consistent versioning of dependencies and plugins across applications
* Common dependencies used across all applications (primarily test-dependencies) 
* Common plugins used across all applications. 
* Common profiles used across all applications.  

##### Considerations
A lot of dependencies are already managed for us by spring-boot-dependencies so check there before adding a possible duplicate here.

## Usage
Declare this pom as the parent in your module. It is important to specify the relative path to the pom-file when it is located in the same project. 