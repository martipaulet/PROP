javac Domini/*.java
javac Domini/*.java Controladors/*.java
javac -cp lib/junit-4.13.1.jar Domini/*.java JUnit/*.java
javac Domini/*.java Controladors/*.java Drivers/*.java
jar cvfe DriverCtrlDomini.jar Drivers/DriverCtrlDomini Domini/*.class Controladors/*.class Drivers/DriverCtrlDomini.class
move Domini\*.class ..\EXE\CLASS\
move Controladors\*class ..\EXE\CLASS\
move Drivers\*.class ..\EXE\CLASS\
move JUnit\*.class ..\EXE\CLASS\
move DriverCtrlDomini.jar ..\EXE\
pause