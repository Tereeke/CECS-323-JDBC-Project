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
                
                
                // 3-7
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
                        String sqlGroup;
                        sqlGroup = "SELECT * FROM writingGroups";
                        stmt = conn.createStatement();
                        ResultSet rsGroup = stmt.executeQuery(sqlGroup);
                        if(!rsGroup.next())
                        {
                            System.out.println("No such group exists.");
                            break;
                        }else
                        {
                            String groupName = rsGroup.getString("groupName");
                            String headWriter = rsGroup.getString("headWriter");
                            String yearFormed = rsGroup.getString("yearFormed");
                            String subject = rsGroup.getString("subject");
                            System.out.println("Group Name: " + groupName);
                            System.out.print("Head Writer: " + headWriter);
                            System.out.print(", Year Formed: " + yearFormed);
                            System.out.println(", Subject: " + subject);
                            System.out.println("Books Written: ");
                        }
                        stmt.close();
                        rsGroup.close();
                        String sql;
<<<<<<< HEAD
<<<<<<< HEAD
                        sql = "SELECT * FROM writingGroups NATURAL JOIN books NATURAL JOIN publishers WHERE groupName = ?";
=======
                        sql = "SELECT headWriter, yearFormed, subject FROM writingGroups WHERE groupName = ?";
>>>>>>> parent of 84e5427... Last comit
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
<<<<<<< HEAD
                            System.out.println("Books Written: ");
=======
                        sql = "SELECT bookTitle, yearPublished, numberPages,"
                                + "publisherName, publisherAddress, publisherPhone,"
                                + "publisherEmail FROM writingGroups NATURAL JOIN books NATURAL JOIN publishers WHERE groupName = ?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, userInput);
                        ResultSet rs = pstmt.executeQuery();
                        while (rs.next()) {
>>>>>>> d432ecc2b637dcea134c0b05a0b26fe97f1cc203
                            String book = rs.getString("bookTitle");
                            String yearPublished = rs.getString("yearPublished");
                            String numberPages = rs.getString("numberPages");
                            String publisherName = rs.getString("publisherName");
                            String publisherAddress = rs.getString("publisherAddress");
                            String publisherPhone = rs.getString("publisherPhone");
                            String publisherEmail = rs.getString("publisherEmail");
                            System.out.print("Book Title: " + book);
                            System.out.print(", Year Published: " + yearPublished);
                            System.out.print(", Number of Pages: " + numberPages);
                            System.out.println(", Publisher Name: " + publisherName);
                            System.out.print("Published By: " + publisherName);
                            System.out.print(", Address: " + publisherAddress);
                            System.out.print(", Phone: " + publisherPhone);
                            System.out.println(", Email: " + publisherEmail);
=======
>>>>>>> parent of 84e5427... Last comit
                        }
                        //STEP 6: Clean-up environment
                        rs.close();
                        pstmt.close();
                        break;
                    }
                    case 3: {
                        System.out.println("List all publishers");
                        stmt = conn.createStatement();
                        String sql = "SELECT publisherName from publishers";

                        ResultSet rs = stmt.executeQuery(sql);
                        while (rs.next()) {
                            String groupName = rs.getString("publisherName");
                            System.out.print("Publisher Name: " + groupName + "\n");
                        }
                        rs.close();
                      
                        break;
                    }
                    case 4: {
                        Scanner groupNam = new Scanner(System.in);
                        //STEP 4: Execute a query
                        System.out.println("Please enter the name of the publisher for which you want the data of:");
                        String userInput = groupNam.nextLine();
                        String sql;
                        sql = "SELECT publisherAddress, publisherEmail, publisherPhone FROM publishers WHERE publisherName = ?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, userInput);
                        
                        ResultSet rs = pstmt.executeQuery();
                        
                        while (rs.next()) {
                            //Retrieve by column name
                            String addy = rs.getString("publisherAddress");
                            String email = rs.getString("publisherEmail");
                            String phone = rs.getString("publisherPhone");

                            //Display values
                            System.out.print("Group Name: " + userInput);
                            System.out.print(",Email: " + email);
                            System.out.print(", phone: " + phone);
                            System.out.println(", Address: " + addy);
                        }
                        rs.close();
                        pstmt.close();
                        
                        break;
                    }
                    case 5: {
                        System.out.println("List all book titles:");
                        stmt = conn.createStatement();
                        String sql = "SELECT bookTitle from books";
                  
                        ResultSet rs = stmt.executeQuery(sql);
                        while (rs.next()) {
                            //Retrieve by column name
                            String bookTitle = rs.getString("bookTitle");

                            //Display values
                            System.out.print("Book Title: " + bookTitle + "\n");
                        }
                        
                        
                        rs.close();
                        break;
                    }
                    case 6: {
                        Scanner groupNam = new Scanner(System.in);
                        //STEP 4: Execute a query
                        System.out.println("Please enter the name of the book for which you want the data of:");
                        String userInput = groupNam.nextLine();
                        String sql;
                        sql = "SELECT yearPublished, numberPages, groupName, publisherName FROM books WHERE bookTitle = ?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, userInput);
<<<<<<< HEAD
<<<<<<< HEAD
                        pstmt.setString(2, pubName);
=======
>>>>>>> d432ecc2b637dcea134c0b05a0b26fe97f1cc203

=======
                        
>>>>>>> parent of 84e5427... Last comit
                        ResultSet rs = pstmt.executeQuery();

                        while (rs.next()) {
<<<<<<< HEAD
<<<<<<< HEAD
                         
                            String yearPublished = rs.getString("yearPublished");
                            String numberPages = rs.getString("numberPages");
                            String groupName = rs.getString("groupName");
                            String publisherName = pubName;
             
=======
                            String yearPublished = rs.getString("yearPublished");
                            String numberPages = rs.getString("numberPages");
                            String groupName = rs.getString("groupName");
                            String publisherName = rs.getString("publisherName");
>>>>>>> d432ecc2b637dcea134c0b05a0b26fe97f1cc203
=======
                            //Retrieve by column name
                            String yearPublished = rs.getString("yearPublished");
                            String numberPages = rs.getString("numberPages");
                            String groupName = rs.getString("groupName");
                            String publisherName = rs.getString("publisherName");

                            //Display values
>>>>>>> parent of 84e5427... Last comit
                            System.out.println("||---------------------------------------||");
                            System.out.println("|| Book Title: " + userInput);
                            System.out.println("|| Group Name: " + groupName);
                            System.out.println("|| Email: " + yearPublished);
                            System.out.println("|| Year Published: " + numberPages);
                            System.out.println("|| Publisher Name: " + publisherName);
                            System.out.println("||---------------------------------------||");
                        }
                        rs.close();
                        pstmt.close();
                        break;
                    }
                    case 7: {
<<<<<<< HEAD
                        Scanner scan = new Scanner(System.in);
                        //STEP 4: Execute a query
                        System.out.println("Please enter the name of the book you want to input:");
                        String bookTitle = scan.nextLine();
                        
                        System.out.println("Please enter the year of this book:");
                        String  year = scan.nextLine();
                        
                        System.out.println("Please enter the page number of this book:");
                        String  pageLength = scan.nextLine();
                        
                        System.out.println("Please enter the publisher of this book:");
                        String  publisher = scan.nextLine();
                        
                        System.out.println("Please enter the Writing Group of this book:");
                        String  writingGroup = scan.nextLine();
                        
                        
                        String sql;
                        //INSERT INTO books VALUES('Cool Booko','2009','170','Awesome Writers','Nicks Books')
                        sql = "INSERT INTO books VALUES('?','?','?','?',?')";
                        //PreparedStatement pstmt = conn.prepareStatement(sql);
                        //pstmt.setString(1, bookTitle);
                        //pstmt.setString(2, year);
                        //pstmt.setString(3, pageLength);
                        //pstmt.setString(5, publisher);
                        //pstmt.setString(4, writingGroup);
                        
<<<<<<< HEAD
=======
>>>>>>> d432ecc2b637dcea134c0b05a0b26fe97f1cc203
=======
                       // pstmt.executeQuery();
>>>>>>> parent of 84e5427... Last comit
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
