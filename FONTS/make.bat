javac Controladors/*.java
javac Domini/*.java
javac Controladors/*.java Drivers/*.java
javac -cp .;C:\junit-4.5.jar JUnit/*.java
move Controladors/*.class ..\EXE\CLASS\Controladors
move Domini/*.class ..\EXE\CLASS\Domini
move Drivers/*.class ..\EXE\CLASS\Drivers
move JUnit/*.class ..\EXE\CLASS\JUnit
