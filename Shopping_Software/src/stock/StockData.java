package stock;

// Skeleton version of StockData.java that links to a database.
// NOTE: You should not have to make any changes to the other
// Java GUI classes for this to work, if you complete it correctly.
// Indeed these classes shouldn't even need to be recompiled
import java.sql.*; // DB handling package
import java.io.*;
import org.apache.derby.drda.NetworkServerControl;

public class StockData {

    private static Connection connection;
    private static Statement stmt;

    static {
        // standard code to open a connection and statement to an Access database
        try {
            NetworkServerControl server = new NetworkServerControl();
            server.start(null);
            // Load JDBC driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Establish a connection
            String sourceURL = "jdbc:derby://localhost:1527/"
                    + new File("UserDB").getAbsolutePath() + ";";
            connection = DriverManager.getConnection(sourceURL, "use", "use");
            stmt = connection.createStatement();
        } // The following exceptions must be caught
        catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
        } catch (SQLException sqle) {
            System.out.println(sqle);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // You could make methods getName, getPrice and getQuantity simpler by using an auxiliary
    // private String method getField(String key, int fieldNo) to return the appropriate field as a String

    public static String getName(String key) {
        try {
            ResultSet res = stmt.executeQuery("SELECT * FROM Stock WHERE stockKey = '" + key + "'");
            if (res.next()) {  
                return res.getString(2);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

public static double getPrice(String key) {
        // Similar to getName. If no result, return -1.0
        try {
            
           ResultSet res = stmt.executeQuery("SELECT * FROM Stock WHERE STOCkKEY = '" + key + "'");
            if (res.next()) { // there is a result
                // the name field is the second one in the ResultSet
                // Note that with  ResultSet we count the fields starting from 1
                return res.getDouble(3);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println(e);
        return 0;
    }
}

    public static int getQuantity(String key) {
       try {
            
           ResultSet res = stmt.executeQuery("SELECT * FROM Stock WHERE STOCkKEY = '" + key + "'");
            if (res.next()) { // there is a result
                // the name field is the second one in the ResultSet
                // Note that with  ResultSet we count the fields starting from 1
                return res.getInt(4);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }

 public static void updateQuantity(String key, int extra) {
        String updateStr = "UPDATE STOCK SET STOCKQUANTITY = STOCKQUANTITY + " + extra + " WHERE STOCKKEY = '" + key + "'";
        System.out.println(updateStr);
        try {
            stmt.executeUpdate(updateStr);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
  
 public static void updateprice(String key, double p) {
        String updateStr = "UPDATE STOCK SET STOCKPRICE =  " + p + " WHERE stockKey = '" + key + "'";
        System.out.println(updateStr);
        try {
            stmt.executeUpdate(updateStr);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
     public static void updatename(String key, String n) {
        String updateStr = "UPDATE STOCK SET STOCKNAME =  '" + n + "' WHERE STOCKKEY = '" + key + "'";
        System.out.println(updateStr);
        try {
            stmt.executeUpdate(updateStr);
        } catch (SQLException e) {
            System.out.println(e);
        } 
     }

  
 
     public static boolean adding (String key, String name, double p, int q) {
        String addingStr = "INSERT INTO STOCK (STOCKKEY, STOCKNAME, STOCKPRICE, STOCKQUANTITY)VALUES ('"+ key + "','" + name + "',"+ p+ "," + q + ")";
        System.out.println(addingStr);
        try {
            stmt.executeUpdate(addingStr);
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } return true;
     }
      public static void delete(String key) {
       String deleteStr = "DELETE FROM STOCK WHERE STOCKKEY = '"+ key + "'"; 
        System.out.println(deleteStr);
        try {
            stmt.executeUpdate(deleteStr);
        } catch (SQLException e) {
            System.out.println(e);
        }     
      }
    // close the database
    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            // this shouldn't happen
            System.out.println(e);
        }
    }
   
}