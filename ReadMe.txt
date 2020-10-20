1) Open the project as maven project
2) If want to run the 2 tests as a testsuite in parallel right click and run testNG.xml file.
3) If want to run from command line go to the folder where pom file is, from command line. Execute bellow command.
   mvn clean test -DsuiteXmlFile=testNG.xml
4) If need to check individually run the individual from the IDE by selecting the tests.