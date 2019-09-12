import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Scanner;

public class SqlController {
    private static final Logger logger = LoggerFactory.getLogger(SqlController.class);

    Connection sqlConnect;

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

    void insert(){
        Scanner insertValues=new Scanner(System.in);

        System.out.println("Insert Name: ");
        String nameInsertion=insertValues.nextLine();

        System.out.println("Insert Age: ");
        int ageInsertion=insertValues.nextInt();

        System.out.println("Insert Phone: ");
        int phoneInsertion=insertValues.nextInt();

        try {
            Statement sqlScript=sqlConnect.createStatement();
            sqlScript.executeUpdate(
                    "INSERT INTO user (name,age,phone)" + "VALUES ( '"+nameInsertion+"',"+ageInsertion+","+phoneInsertion+")"
            );
            logger.info("( Insert successful, "+"VALUES ( '"+nameInsertion+"',"+ageInsertion+","+phoneInsertion+"))");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("( Insert failed, exception: "+e+" )");
        }
    }

    void update(){
        Scanner insertValues=new Scanner(System.in);

        System.out.println("Insert ID to update: ");
        int idUpdate=insertValues.nextInt();
        insertValues.nextLine();

        System.out.println("Insert Name: ");
        String nameInsertion=insertValues.nextLine();

        System.out.println("Insert Age: ");
        int ageInsertion=insertValues.nextInt();

        System.out.println("Insert Phone: ");
        int phoneInsertion=insertValues.nextInt();

        try {
            Statement sqlScript=sqlConnect.createStatement();
            String sqlStatement="Update user set name=?,age=?,phone=? where id=?";
            PreparedStatement updateSqlStatment=sqlConnect.prepareStatement(sqlStatement);

            updateSqlStatment.setString(1,nameInsertion);
            updateSqlStatment.setString(2,Integer.toString(ageInsertion));
            updateSqlStatment.setString(3,Integer.toString(phoneInsertion));
            updateSqlStatment.setString(4,Integer.toString(idUpdate));

            updateSqlStatment.executeUpdate();
            logger.info("( Update Successful )");

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("( Update failed, exception: "+e+" )");
        }
    }

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

    void search(){
        Scanner insertValues=new Scanner(System.in);
        System.out.println("Insert ID for Search: ");
        int idSelection=insertValues.nextInt();
        try {
            Statement sqlScript=sqlConnect.createStatement();
            ResultSet resultQuery=sqlScript.executeQuery(
                    "SELECT * FROM user WHERE id="+idSelection
            );
            while (resultQuery.next()){
                System.out.println("ID: "+resultQuery.getInt("id"));
                System.out.println("Name: "+resultQuery.getString("name"));
                System.out.println("Age: "+resultQuery.getInt("age"));
                System.out.println("Phone: "+resultQuery.getInt("phone"));
            }
            logger.info("( Search Successful )");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("( Search failed, exception: "+e+" )");
        }
    }

    void showAll(){
        try {
            Statement sqlScript=sqlConnect.createStatement();
            ResultSet resultQuery=sqlScript.executeQuery(
                    "SELECT * FROM user"
            );
            while (resultQuery.next()){
                System.out.println("ID: "+resultQuery.getInt("id"));
                System.out.println("Name: "+resultQuery.getString("name"));
                System.out.println("Age: "+resultQuery.getInt("age"));
                System.out.println("Phone: "+resultQuery.getInt("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("( ShowAll failed, exception: "+e+" )");
        }
    }
}
