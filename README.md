# Stock API

## Technologies used in this project
Java 17, Spring Boot Libraries, H2 Database, Maven

## How to run this project
1. Download the zip file & unzip it.
2. After going into the project repository run this command ```mvn spring-boot:run```
3. The project will run on port **8080**
4. Please make sure to use JAVA 17 while running this project. 
5. You can check the running H2 database instance on http://localhost:8080/h2-console/ Connection details can be found in **application.properties**

## Commands to hit API endpoints
1. **Bulk Insert:** ```curl -F "file=@dow_jones_index.data" -H "X-Client_Id: abc123" http://127.0.0.1:8080/api/stock-data/bulk-insert```
2. **Get Records:** ```curl -H "X-Client_Id: abc123" http://127.0.0.1:8080/api/stock-data/AA```
3. **Insert Missing Data:** ```curl -H "Content-Type: application/json" -H "X-Client_Id: abc123" -X POST -d @dow_jones_missing.json http://127.0.0.1:8080/api/stock-data/```
4. You can verify the inserted records in H2 Database. 


### If there is any issue with it, please reach out to me on dhavalmodi556@gmail.com
