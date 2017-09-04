#  Product and Warehouse and Inventory (PWI) 

## Prerequisites:
* MySQL 5.7
* JDK 1.8 
* Maven 3.*

## Install and run the project 
1. download/clone the project 
2. prepare the database
  * import in MySQL the self-contained file that comes with the project -  [database-script.sql]  
  * username/password - `vantibolli`/`vantibolli`
  * you also can run database script using maven. update your database connection information in pom.xml Line nubmer 221 to 223
  * change to the root folder of the project and excute the following maven command 
  * `mvn sql:execute`
3. change to the root folder of the project and excute the following maven command 
  * `mvn package cargo:run`
  * now the REST api is up and running with tomcat 8 on `localhost:8080`   
  
  
> **Note:** you could run a similar configuration from Eclipse if you have the m2e plugin installed 

> **Note:** after you `mvn package` the application, you also can deploy the generated war file in installed Tomcat on your machine you need to change  cargo plugin configurations pom.xml file
  
	   

## Testing the project 

### From Maven 
Run the following maven command on the console in the root directory of the project 
  
  ```mvn clean install verify ```


## Project Architecture 

 Is composed with SOA, MVC pattern. 
 
 The Application layers.
 
 1. The web layer is the uppermost layer of a web application. It is responsible of processing user's input and returning the correct response back to the user. The web layer must also handle the exceptions thrown by the other layers. Because the web layer is the entry point of our application, it must take care of authentication and act as a first line of defense against unauthorized users.
 
 2. The service layer resides below the web layer. It acts as a transaction boundary and contains both application and infrastructure services. The application services provides the public API of the service layer. They also act as a transaction boundary and are responsible of authorization. The infrastructure services contain the "plumbing code " that communicates with external resources such as file systems, databases, or email servers. Often these methods are used by more than a one application service.

 3. The repository layer is the lowest layer of a web application. It is responsible of communicating with the used data storage