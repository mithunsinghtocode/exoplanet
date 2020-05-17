# Exoplanet Catalog

## Software Used
- Spring Boot
- Java 11
- Docker

## Questions
### Why did you make the design choices you made?
    - I have used Spring boot in order to,
        * Parse JSON file using Jackson.
        * Do the computations based on the requirement.
        * Download catalog file or
        * Display catalog in browser using a REST api.
        * Record errors and display to user if any and proceed to parse JSON only after correction of errors.
        * User can mention filepath in the URL to generate catalog from the JSON.
        * Extend the code to have UI for file upload or retrieve file using sftp etc.
    - Code is written in Java having SOLID principles in mind.
    - Docker to allow users to run container in any platform if docker is installed.
    
### What assumptions did you make and why?
    - JSON file provided should be a valid file.
        * If any issue in parsing, eg., Year of discovery is alphanumeric which is error then user will be provided with error in browser when accessing the api. 
        * Once corrected catalog will be successfully generated. Program does not lets any garbage in.
    - Orphan planets is based on the TypeFlag specifications
        * TypeFlag: [0=no known stellar binary companion; 1=P-type binary (circumbinary); 2=S-type binary; 3=orphan planet (no star)]
    - The name (planet identifier) of the planet orbiting the hottest star - by finding the highest HostStarTempK planet in the json.
    - Timeline of planet discovery per year groupd by size - Sorting was done based on the year of discovery.    


## Improvements
1. Logger should be integrated.
2. Junit should be integrated to have full code coverage.
3. Spring profiling for execution based on environment.


## Steps to run the program manually (Requires Java 11)
1. git pull https://github.com/mithunsinghtocode/exoplanet.git
2. cd jar
3. java -jar exoplanet.jar
4. JSON file is retrieved from classpath which was provided and program will run.
5. http://localhost:8080/generateCatalog to display catalog based on json file in classpath.
6. Inorder to download catalog file, http://localhost:8080/generateTextcatalog
7. Set filepath as per below to run from json file in local PC and display catalog in browser.
Eg. http://localhost:8080/generateCatalog?filepath=/Users/mithunsingh/Downloads/exoplanet.json
8. Set filepath as per below to run from json file in local PC and download file.
Eg. http://localhost:8080/generateCatalogFile?filepath=/Users/mithunsingh/Downloads/exoplanet.json
9. If error occured in parsing the file, the following error will be displayed,
 *------------------ Error Occurred.-------------------- 
 Please resolve error before proceeding again.

 Error Description below, 

Error in locating File: Unrecognized token 'Ass': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')
 at [Source: (FileInputStream); line: 17, column: 26] (through reference chain: java.lang.Object[][0])*
 
 Followed by correction, upon hitting the api again will generate catalog.


## Docker build and run in local own docker repository (Requires docker)
1. git pull https://github.com/mithunsinghtocode/exoplanet.git
3. cd exoplanet (If the current folder is different)
2. docker build -t repository/tag:latest .
3. docker run -d -p 8080:8080 -t repository/tag

## To download docker image from Docker Hub and run (Requires docker)
1. docker run -d -p 8080:8080 -t thinkbymithun/exoplanet

## Steps to run after making changes to program files.
1. Make sure your terminal or cmd is pointing to exoplanet folder downloaded.
2. mvn clean install (If change in pom.xml)
3. mvn package (to generate jar to target folder)
4. navigate to target folder and run, java -jar exoplanet.jar

