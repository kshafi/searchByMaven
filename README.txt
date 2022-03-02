
Assumptions:
*  JAVA 8 has been installed
*  MAVEN has been installed
*  Input file "ProgrammingAssignment.csv" will be added to the resources folder

To build and Compile:
Run the following Maven command to generate the executable jar file:
  mvn clean dependency:copy-dependencies package

To execute the jar file:
Run the following java command:
   java -jar searchByMaven-1.0-SNAPSHOT.jar

To search a file entery, do the following once prompted to enter a value:
For a single value search:
    * Enter a key to search
    * Enter Q to quit input
    * Output will be printed on the screen
    * utput will be printed on the screen based on the key
For a 2 values search:
    * Enter a first key value
    * Enter a second key value
    * Enter Q to quit input
    * Output will be printed on the screen based on the two keys

Note: You can add as many values as you like, once Q is enter, only the first
      two value entered will be used for the search


Thoughts on how to implement unit and integration tests:
* Add tests for reading the input file
    - Valid file
    - Invalid file
    - Empty file
    - File with incorrect data
    - File with valid data
    - File loading specific tests
    - Data searching specific test
    - Data searching using one key
    - Data searching using two keys
    - Expect results for each scenario
    - Volume tests
    - Performance tests

