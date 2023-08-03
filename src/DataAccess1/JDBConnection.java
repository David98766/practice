/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author I586662
 */
public class JDBConnection {
    private static String dbURL = "jdbc:derby://localhost:1527/BarberShop;create=true;user=Barber;password=lemon";
 
    private static Connection conn = null;
    
    public static void initialize(){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(dbURL);
        }
        catch(Exception except) {
            except.printStackTrace();
        }
    }
    

  public static Connection getConnection(){
        try{
            if(conn==null || conn.isClosed()){
                initialize();
            }
        }catch (SQLException ex){
            Logger.getLogger(JDBConnection.class.getName())
                    .log(Level.SEVERE, null,ex);
        }
        return conn;
    }
    /**
     *
     * @param conn
     * @param stmt
     * @param rs
     */
    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}