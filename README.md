# JavaCrudApp
CRUD app made in Java, built in Maven, connected to MySql and logged using Logback

## Project Flow
The project uses the main class to initiate an object of the interface class, which then creates and object of the SQL Controller class which initializes with a connection to the SQL server at localhost.
https://github.com/s4r1m/JavaCrudApp/blob/2dc8613428fd78fc3808a346186501cbc95b921c/SqlController.java#L12-L27

Since the project is a console application the frontend of the app is on the console.
https://github.com/s4r1m/JavaCrudApp/blob/2dc8613428fd78fc3808a346186501cbc95b921c/InterfaceController.java#L23-L68

The console keeps asking user for input to select the CRUD operations, until the User decides to exit the program.

Each CRUD operation is in a seperate method which is called by object of the SQL Controller made in the interface controller.
### Example:
#### Interface Controller
https://github.com/s4r1m/JavaCrudApp/blob/2dc8613428fd78fc3808a346186501cbc95b921c/InterfaceController.java#L50-L54
#### SQL Controller
https://github.com/s4r1m/JavaCrudApp/blob/2dc8613428fd78fc3808a346186501cbc95b921c/SqlController.java#L88-L102

## Logging Using LogBack
Logging is done using Logback. Each class has its own logger initiated in the start.
Each class uses it to show info,exceptions and errors.
All logs show on the console aswell as saved to a file called "tests.log".
