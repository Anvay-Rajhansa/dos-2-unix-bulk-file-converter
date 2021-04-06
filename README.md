# dos-2-unix-bulk-file-converter

Given directory as input this program will identify all the shell (.sh) files form that folder and its sub-folders 
and convert them in to unix format with the help of do2unix tool.  

##Prerequisite 
do2unix tool should be installed and available on PATH
https://community.chocolatey.org/packages/dos2unix

##Execution instruction
1. Clone this repo.
2. Perform in root directory `mvn clean install`
3. Perform in root directory `mvn package`
   : This will generate jar file with name `dos-2-unix-bulk-file-converter-1.0-SNAPSHOT-jar-with-dependencies.jar` 
   in target directory.
   
4. Run this generated jar with a directory as input argument.

ex : `java -jar .\dos-2-unix-bulk-file-converter-1.0-SNAPSHOT-jar-with-dependencies.jar "C:\Documents\inputdirectory"`