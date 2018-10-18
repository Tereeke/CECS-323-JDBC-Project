package jdbcproject;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author rcast
 */
public class JDBCProject {
    // JDBC driver name and database URL
   static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
   static final String DB_URL = "jdbc:derby://localhost:1527/JDBCProject";
   //  Database credentials
   static final String USER = "username";
   static final String PASS = "password";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        boolean cont = true;
        Scanner menuChoice = new Scanner(System.in);
        int userChoice = 0;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            while (cont) {
                System.out.println("Please select a menu option: ");
                System.out.println("1) List all writing groups");
                System.out.println("2) Input a group to display all data for");//do
                System.out.println("3) List all publishers");
                System.out.println("4) Input a publisher to display all data for");
                System.out.println("5) List all book titles");
                System.out.println("6) Input a book title to display all data for");
                System.out.println("7) Insert a new book");
                System.out.println("8) Insert a new publisher");//do
                System.out.println("9) Remove a book");//do
                System.out.println("0) Exit");
                userChoice = menuChoice.nextInt();
                switch (userChoice) {
                    case 0: {
                        cont = false;
                        break;
                    }
                    case 1: {
                        //STEP 4: Execute a query
                        System.out.println("Listing all writing groups:");
                        stmt = conn.createStatement();
                        String sql;
                        sql = "SELECT groupName FROM writingGroups";
                        ResultSet rs = stmt.executeQuery(sql);

                        //STEP 5: Extract data from result set
                        while (rs.next()) {
                            //Retrieve by column name
                            String groupName = rs.getString("groupName");

                            //Display values
                            System.out.print("Group Name: " + groupName + "\n");
                        }
                        //STEP 6: Clean-up environment
                        rs.close();
                        stmt.close();
                        break;
                    }
                    case 2: {
                        Scanner groupNam = new Scanner(System.in);
                        //STEP 4: Execute a query
                        System.out.println("Please enter the name of the group for which you want the data of:");
                        String userInput = groupNam.nextLine();
                        String sql;
                        sql = "SELECT headWriter, yearFormed, subject FROM writingGroups WHERE groupName = ?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, userInput);
                        
                        ResultSet rs = pstmt.executeQuery();

                        //STEP 5: Extract data from result set
                        while (rs.next()) {
                            //Retrieve by column name
                            String headWriter = rs.getString("headWriter");
                            String yearFormed = rs.getString("yearFormed");
                            String subject = rs.getString("subject");

                            //Display values
                            System.out.print("Group Name: " + userInput);
                            System.out.print(", Head Writer: " + headWriter);
                            System.out.print(", Year Formed: " + yearFormed);
                            System.out.println(", Subject: " + subject);
                        }
                        //STEP 6: Clean-up environment
                        rs.close();
                        pstmt.close();
                        break;
                    }
                    case 3: {
                        break;
                    }
                    case 4: {
                        break;
                    }
                    case 5: {
                        break;
                    }
                    case 6: {
                        break;
                    }
                    case 7: {
                        break;
                    }
                    case 8: {
                        break;
                    }
                    case 9: {
                        break;
                    }
                    default: {
                        System.out.println("Please enter a valid menu option");
                        break;
                    }
                }
            }
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        System.out.println("Goodbye!");
    }//end main
}//end 
