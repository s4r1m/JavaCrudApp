# JavaCrudApp
CRUD app made in Java, built in Maven, connected to MySql and logged using Logback

## Project Flow
The project uses the main class to initiate an object of the interface class, which then creates and object of the SQL Controller class which initializes with a connection to the SQL server at localhost.
```
    public SqlController(){
        sqlConnect=SqlConnection();
    }


    Connection SqlConnection(){
        try{
            Connection sqlConnection= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/crud","root","Hello1234"
            );
            logger.info("( Connection made with SQL )");
            return sqlConnection;
        }catch (Exception e){
            logger.error("( Connection not established, exception: "+e+" )");
            return null;
        }
    }
```

Since the project is a console application the frontend of the app is on the console.
```
            while (true) {
                System.out.println("1.Insert");
                System.out.println("2.Update");
                System.out.println("3.Search");
                System.out.println("4.Delete");
                System.out.println("5.Show Table");
                System.out.println("6.Exit");


                Scanner inputOperationSelect=new Scanner(System.in);
                int selectedOperation=inputOperationSelect.nextInt();


                switch (selectedOperation){
                    case 1:
                        System.out.println("User has decided to insert: ");
                        logger.info("( Operation Picked Insert )");
                        sqlConnect.insert();
                        break;
                    case 2:
                        System.out.println("User has decided to update: ");
                        logger.info("( Operation Picked Update )");
                        sqlConnect.update();
                        break;
                    case 3:
                        System.out.println("User has decided to search: ");
                        logger.info("( Operation Picked Search )");
                        sqlConnect.search();
                        break;
                    case 4:
                        System.out.println("User has decided to delete: ");
                        logger.info("( Operation Picked Delete )");
                        sqlConnect.delete();
                        break;
                    case 5:
                        System.out.println("User has decided to show table: ");
                        sqlConnect.showAll();
                        break;
                    case 6:
                        System.out.println("User has decided to exit");
                        logger.info("( Operation Picked Exit )");
                        return;
                    default:
                        System.out.println("Invalid Input");
                        logger.error("( User has picked invalid input )");
                        break;
                }
            }
```

The console keeps asking user for input to select the CRUD operations, until the User decides to exit the program.

Each CRUD operation is in a seperate method which is called by object of the SQL Controller made in the interface controller.
### Example:
#### Interface Controller
```
                    case 4:
                        System.out.println("User has decided to delete: ");
                        logger.info("( Operation Picked Delete )");
                        sqlConnect.delete();
                        break;
```
#### SQL Controller
```
    void delete(){
        Scanner insertValues=new Scanner(System.in);
        System.out.println("Insert ID for deletion: ");
        int idSelection=insertValues.nextInt();
        try {
            Statement sqlScript=sqlConnect.createStatement();
            sqlScript.executeUpdate(
                    "DELETE FROM user WHERE id="+idSelection
            );
            logger.info("( Delete Successful )");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("( Delete failed, exception: "+e+" )");
        }
    }
```
## Logging Using LogBack
Logging is done using Logback. Each class has its own logger initiated in the start.
Each class uses it to show info,exceptions and errors.
All logs show on the console aswell as saved to a file called "tests.log".
