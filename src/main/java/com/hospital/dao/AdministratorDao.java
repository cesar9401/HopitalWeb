package com.hospital.dao;

import com.hospital.model.Administrator;
import com.hospital.model.Day;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cesar31
 */
public class AdministratorDao {

    private Connection transaction;

    public AdministratorDao() {
    }

    public AdministratorDao(Connection transaction) {
        this.transaction = transaction;
    }

    /**
     * Metodo para insertar los dias que trabaja el hospital
     */
    public void insertDays() {
        String query = "INSERT INTO DAYS(day_id, name_day) VALUES(?, ?)";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            for (Day day : Day.values()) {
                pst.setInt(1, day.getDayId());
                pst.setString(2, day.getDay());
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Metodo para insertar un nuevo administrador
     *
     * @param admin
     */
    public void insertAdministrator(Administrator admin) {
        String query = "INSERT INTO ADMINISTRATORS(admin_id, dpi, name, password) VALUES(?, ?, ?, ?)";

        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, admin.getAdminId());
            pst.setString(2, admin.getDpi());
            pst.setString(3, admin.getName());
            pst.setString(4, admin.getPass());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Metodo para obtener un administrador segun su codigo y password
     *
     * @param adminId
     * @param pass
     * @return
     */
    public Administrator getAdminById(String adminId, String pass) {
        Administrator admin = null;
        String query = "SELECT * FROM ADMINISTRATORS WHERE admin_id = ? AND password = ?";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, adminId);
            pst.setString(2, pass);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    admin = new Administrator(rs.getString("admin_id"), rs.getString("dpi"), rs.getString("name"), rs.getString("password"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return admin;
    }

    public Administrator getAdmin(String adminId) {
        Administrator admin = null;
        String query = "SELECT * FROM ADMINISTRATORS WHERE admin_id = ?";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, adminId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    admin = new Administrator(rs.getString("admin_id"), rs.getString("dpi"), rs.getString("name"), rs.getString("password"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return admin;
    }

    public List<Administrator> getAdmins() {
        List<Administrator> admins = new ArrayList<>();
        String query = "SELECT * FROM ADMINISTRATORS";
        try (PreparedStatement pst = this.transaction.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                admins.add(new Administrator(rs.getString("admin_id"), rs.getString("dpi"), rs.getString("name"), rs.getString("password")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return admins;
    }
}
