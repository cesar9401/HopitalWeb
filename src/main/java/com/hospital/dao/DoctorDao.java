
package com.hospital.dao;

import com.hospital.model.Doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author cesar31
 */
public class DoctorDao {
    private Connection transaction;

    public DoctorDao() {
    }

    public DoctorDao(Connection transaction) {
        this.transaction = transaction;
    }
    
    public void insertDoctor(Doctor doc) {
        String query = "INSERT INTO DOCTORS(doctor_id, name, collegiate, dpi, phone, email, start_time, end_time, start_date, password) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, doc.getDoctorId());
            pst.setString(2, doc.getName());
            pst.setString(3, doc.getCollegiate());
            pst.setString(4, doc.getDpi());
            pst.setString(5, doc.getPhone());
            pst.setString(6, doc.getEmail());
            pst.setTime(7, doc.getStartTime());
            pst.setTime(8, doc.getEndTime());
            pst.setDate(9, doc.getStartDate());
            pst.setString(10, doc.getPass());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
