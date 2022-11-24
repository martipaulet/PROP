javac Domini/Model/*.java
javac Domini/Model/*.java Domini/Controladors/*.java
javac -cp lib/junit-4.13.1.jar Domini/Model/*.java JUnit/*.java
javac Domini/Model/*.java Domini/Controladors/*.java Drivers/*.java
jar cvfe DriverCtrlDomini.jar Drivers/DriverCtrlDomini Domini/Model/*.class Domini/Controladors/*.class Drivers/DriverCtrlDomini.class
move Domini\Model\*.class ..\EXE\CLASS\
move Domini\Controladors\*class ..\EXE\CLASS\
move Drivers\*.class ..\EXE\CLASS\
move JUnit\*.class ..\EXE\CLASS\
timeout 5
move DriverCtrlDomini.jar ..\EXE\

