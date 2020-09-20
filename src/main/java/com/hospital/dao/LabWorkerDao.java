
package com.hospital.dao;

import com.hospital.model.LabWorker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author cesar31
 */
public class LabWorkerDao {
    private Connection transaction;

    public LabWorkerDao() {
    }

    public LabWorkerDao(Connection transaction) {
        this.transaction = transaction;
    }
    
    public void insertLabWoker(LabWorker lab) {
        String query = "INSERT INTO LAB_WORKERS(lab_worker_id, name, registry_number, dpi, phone, email, start_date, password, exam_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, lab.getLabWorkerId());
            pst.setString(2, lab.getName());
            pst.setString(3, lab.getRegistry());
            pst.setString(4, lab.getDpi());
            pst.setString(5, lab.getPhone());
            pst.setString(6, lab.getEmail());
            pst.setDate(7, lab.getStartDate());
            pst.setString(8, lab.getPass());
            pst.setInt(9, lab.getExamId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
