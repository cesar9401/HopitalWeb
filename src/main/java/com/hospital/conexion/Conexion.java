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

    public static Connection getConnection() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return conexion;
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Statement st) {
        try {
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement ps) {
        try {
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
