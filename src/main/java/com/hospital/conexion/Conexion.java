
package com.hospital.conexion;

import java.sql.*;

/**
 *
 * @author cesar31
 */
public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/HOSPITAL";
    private static final String USER = "cesar31";
    private static final String PASS = "Huevos94C";
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
    public static void close(Connection connection) {
        try {
            connection.close();
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }    
    
    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Statement st) {
        try {
            st.close();
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(PreparedStatement ps) {
        try {
            ps.close();
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }    
}