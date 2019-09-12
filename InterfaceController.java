import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SecureCacheResponse;
import java.sql.Connection;
import java.util.Scanner;

public class InterfaceController {
    private static final Logger logger = LoggerFactory.getLogger(InterfaceController.class);

    InterfaceController(){
        inputInterface();
    }

    void inputInterface(){

        SqlController sqlConnect= new SqlController();
        if(sqlConnect.sqlConnect==null){
            logger.info("( Connection not established )");
            return;
        }
        else{
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
        }
    }
}