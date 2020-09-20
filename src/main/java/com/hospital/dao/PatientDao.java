
package com.hospital.dao;

import com.hospital.model.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author cesar31
 */
public class PatientDao {
    private Connection transaction;

    public PatientDao() {
    }

    public PatientDao(Connection transaction) {
        this.transaction = transaction;
    }
    
    public void insertPatient(Patient p) {
        String query = "INSERT INTO PATIENTS(patient_id, name, gender, birth, dpi, phone, weight, blood, email, password) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setInt(1, p.getPatientId());
            pst.setString(2, p.getName());
            pst.setBoolean(3, p.isGender());
            pst.setDate(4, p.getBirth());
            pst.setString(5, p.getDpi());
            pst.setString(6, p.getPhone());
            pst.setDouble(7, p.getWeight());
            pst.setString(8, p.getBlood());
            pst.setString(9, p.getEmail());
            pst.setString(10, p.getPass());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
