package jdbcproject;
import java.sql.*;
import java.util.*;

/**
 *
 * 
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
        boolean b = false;
        boolean validInt = false;
        Scanner menuChoice = new Scanner(System.in);
        int userChoice = -1;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String menu = "\nPlease select a menu option: \n"
                    + "1) List all writing groups\n"
                    + "2) Input a group to display all data for\n"
                    + "3) List all publishers\n"
                    + "4) Input a publisher to display all data for\n"
                    + "5) List all book titles\n"
                    + "6) Input a book title to display all data for\n"
                    + "7) Insert a new book\n"
                    + "8) Insert a new publisher\n"
                    + "9) Remove a book\n"
                    + "0) Exit\n";
            do{
                menuChoice.reset();
                System.out.println(menu); 
                if(menuChoice.hasNextInt())
                    userChoice = menuChoice.nextInt();
                else
                    menuChoice.next();         
                switch (userChoice) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        System.out.println("Listing all writing groups:");
                        stmt = conn.createStatement();
                        String sql;
                        sql = "SELECT groupName FROM writingGroups";
                        ResultSet rs = stmt.executeQuery(sql);
                        while (rs.next()) {
                            String groupName = rs.getString("groupName");
                            System.out.print("Group Name: " + groupName + "\n");
                        }
                        rs.close();
                        stmt.close();
                        break;
                    }
                    case 2: {
                        Scanner groupNam = new Scanner(System.in);
                        groupNam.reset();
                        System.out.println("Please enter the name of the group for which you want the data of:");
                        String userInput = groupNam.nextLine();
                        String sql;
                        sql = "SELECT * FROM writingGroups NATURAL JOIN books NATURAL JOIN publishers WHERE groupName = ?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, userInput);
                        ResultSet rs = pstmt.executeQuery();
                        if(!rs.next())
                        {
                            System.out.println("No such group exists.");
                            break;
                        }
                        while (rs.next()) {
                            String groupName = rs.getString("groupName");
                            String headWriter = rs.getString("headWriter");
                            String yearFormed = rs.getString("yearFormed");
                            String subject = rs.getString("subject");
                            System.out.println("Group Name: " + groupName);
                            System.out.print("Head Writer: " + headWriter);
                            System.out.print(", Year Formed: " + yearFormed);
                            System.out.println(", Subject: " + subject);
                            System.out.println("Books Written: ");
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
                        }
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
                            //Retrieve by column name
                            String groupName = rs.getString("publisherName");

                            //Display values
                            System.out.print("Publisher Name: " + groupName + "\n");
                        }
                        
                        
                        rs.close();
                        break;
                    }
                    case 4: {
                        Scanner groupNam = new Scanner(System.in);
                        System.out.println("Please enter the name of the publisher for which you want the data of:");
                        String userInput = groupNam.nextLine();
                        String sql;
                        sql = "SELECT publisherAddress, publisherEmail, publisherPhone FROM publishers WHERE publisherName = ?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, userInput);

                        ResultSet rs = pstmt.executeQuery();

                        while (rs.next()) {
                            String addy = rs.getString("publisherAddress");
                            String email = rs.getString("publisherEmail");
                            String phone = rs.getString("publisherPhone");
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
                            String bookTitle = rs.getString("bookTitle");
                            System.out.print("Book Title: " + bookTitle + "\n");
                        }
                        rs.close();
                        break;
                    }
                    case 6: {
                        Scanner groupNam = new Scanner(System.in);
                        System.out.println("Please enter the name of the book for which you want the data of:");
                        String userInput = groupNam.nextLine();
                        System.out.println("Please enter the publisher of the book for which you want the data of:");
                        String pubName = groupNam.nextLine();
                        String sql;
                        sql = "SELECT yearPublished, numberPages, groupName FROM books JOIN WHERE bookTitle = ? and publisherName = ?" ;
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, userInput);
                        pstmt.setString(2, pubName);

                        ResultSet rs = pstmt.executeQuery();
                        
                        while (rs.next()) {
                         
                            String yearPublished = rs.getString("yearPublished");
                            String numberPages = rs.getString("numberPages");
                            String groupName = rs.getString("groupName");
                            String publisherName = pubName;
             
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
                        Scanner scan = new Scanner(System.in);
                        
                      
             
                        
                        stmt = conn.createStatement();
                        String sqlCheck;
                        sqlCheck = "SELECT publisherName FROM publishers";
                        ResultSet rsCheck = stmt.executeQuery(sqlCheck);
                        String userPubName = "";
                        boolean isValid = false;    
                        do{
                            System.out.println("Please enter the name of the your publisher: ");
                            userPubName = scan.nextLine();
                          while(rsCheck.next())
                            {
                                String pubName = rsCheck.getString("publisherName");
                                if(userPubName.equals(pubName))
                                {
                                    //Valid Publisher
                                    isValid = true;
                                }else{
                                    //System.out.println("Not A Valid Publisher");
                                }
                            }
                          
                        }while(!isValid);
                        
                    
                       
                        stmt.close();
                      //  System.out.println("Good Pub");
                        
                        
                        stmt = conn.createStatement();
                        isValid = false;
                        String userGroup = "";
                        String sqlCheckGroupName;
                        sqlCheckGroupName = "SELECT groupName FROM WritingGroups";
                        ResultSet groupNameCheck = stmt.executeQuery(sqlCheckGroupName);
                        
                        
                        do{
                            //System.out.println("Please enter the name of the your writing group: ");
                          userGroup = scan.nextLine();
                          while(groupNameCheck.next())
                            {
                                String pubName = groupNameCheck.getString("groupName");
                                if(userGroup.equals(pubName))
                                {
                                    //Valid Publisher
                                    isValid = true;
                                }else{
                                    System.out.println(userGroup + "!=" + pubName );
                                }
                            }
                          
                        }while(!isValid);
                        
                        System.out.println("Please enter the name of the book you want to input:");
                        String bookTitle = scan.nextLine();
                        
                        System.out.println("Please enter the year of this book:");
                        String  year = new Integer(scan.nextInt()).toString();
                        
                        System.out.println("Please enter the page number of this book:");
                        String  pageLength = new Integer(scan.nextInt()).toString();
                        
                        
                        
                        String sql;
                        //INSERT INTO books VALUES('Cool Booko','2009','170','Awesome Writers','Nicks Books')
                        sql = "INSERT INTO books VALUES('?','?','?','?',?')";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, bookTitle);
                        pstmt.setString(2, year);
                        pstmt.setString(3, pageLength);
                        pstmt.setString(5, userPubName);
                        pstmt.setString(4, userGroup);
                        
                       
                        break;	                     
                        
                        7
                                
                        
                        
                        
                        
                        
                        
                        
                        
                        break;
                    }
                    case 8: {
                        Scanner newPub = new Scanner(System.in);
                        System.out.println("Please enter the name of the new publisher(limited to 20 characters): ");
                        String newPubName = newPub.nextLine();
                        Scanner oldPub = new Scanner(System.in);
                        System.out.println("Please enter the name of the old publisher(limited to 20 characters): ");
                        String oldPubName = oldPub.nextLine();
                        
                        stmt = conn.createStatement();
                        String sqlCheck;
                        sqlCheck = "SELECT publisherName FROM publishers";
                        ResultSet rsCheck = stmt.executeQuery(sqlCheck);
                        while(rsCheck.next())
                        {
                            String pubName = rsCheck.getString("publisherName");
                            if(pubName.equals(newPubName))
                            {
                                System.out.println("The new publisher already exists");
                                b = true;
                            }
                        }
                        stmt.close();
                        rsCheck.close();
                        if(b)
                            break;
                        String sql;
                        sql = "SELECT publisherAddress, publisherPhone, publisherEmail FROM publishers WHERE publisherName = ?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, oldPubName);
                        ResultSet rs = pstmt.executeQuery();
                        if(rs.next())
                        {
                            String pubAddress = rs.getString("publisherAddress");
                            String pubPhone = rs.getString("publisherPhone");
                            String pubEmail = rs.getString("publisherEmail");
                            
                            String sqlAdd;
                            sqlAdd = "INSERT INTO publishers VALUES(?, ?, ?, ?)";
                            PreparedStatement pstmtAdd = conn.prepareStatement(sqlAdd);
                            pstmtAdd.setString(1, newPubName);
                            pstmtAdd.setString(2, pubAddress);
                            pstmtAdd.setString(3, pubPhone);
                            pstmtAdd.setString(4, pubEmail);
                            pstmtAdd.executeUpdate();
                            pstmtAdd.close();

                            String sqlUpdate;
                            sqlUpdate = "UPDATE books SET publisherName = ? WHERE publisherName = ?";
                            PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate);
                            pstmtUpdate.setString(1, newPubName);
                            pstmtUpdate.setString(2, oldPubName);
                            pstmtUpdate.executeUpdate();
                            pstmtUpdate.close();
                            System.out.println("Books updated");

                            String sqlDelete;
                            sqlDelete = "DELETE FROM publishers WHERE publisherName = ?";
                            PreparedStatement pstmtDelete = conn.prepareStatement(sqlDelete);
                            pstmtDelete.setString(1, oldPubName);
                            pstmtDelete.executeUpdate();
                            pstmtDelete.close();
                            System.out.println("Publisher removed");
                        }
                        else
                            System.out.println("The old publisher entered does not exist so no changes were made.");
                        pstmt.close();
                        rs.close();
                        break;
                    }
                    case 9: {
                        Scanner groupN = new Scanner(System.in);
                        Scanner title = new Scanner(System.in);
                        System.out.println("Please enter the title for the book that you would like to remove:");
                        String userResponse = title.nextLine();
                        System.out.println("Please enter the group name for the book that you would like to remove:");
                        String userInput = groupN.nextLine();
                        String sql;
                        sql = "DELETE FROM books WHERE bookTitle = ? AND groupName = ?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, userResponse);
                        pstmt.setString(2, userInput);
                        pstmt.executeUpdate();
                        pstmt.close();
                        System.out.println("Book removed.");
                        stmt = conn.createStatement();
                        sql = "SELECT bookTitle FROM books";
                        ResultSet rs = stmt.executeQuery(sql);
                        System.out.println("Books: ");
                        while (rs.next()) {
                            String bookTitle = rs.getString("bookTitle");
                            System.out.print("Title: " + bookTitle + "\n");
                        }
                        rs.close();
                        stmt.close();
                        break;
                    }
                    default: {
                        System.out.println("Please enter a valid menu option");
                        break;
                    }
                }
            }while(userChoice != 0);
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        menuChoice.close();
    }//end main
}//end 