javac Domini/*.java
javac Domini/*.java Controladors/*.java
javac -cp lib/junit-4.13.1.jar Domini/*.java JUnit/*.java
javac Domini/*.java Controladors/*.java Drivers/*.java
jar cvfe DriverCtrlDomini.jar Drivers/DriverCtrlDomini Controladors/*.class Domini/*.class Drivers/DriverCtrlDomini.class
move Domini\*.class ..\EXE\CLASS\
move Controladors\*class ..\EXE\CLASS\
move Drivers\*.class .
pause