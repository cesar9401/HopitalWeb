
package com.hospital.dao;

import com.hospital.model.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author cesar31
 */
public class ResultDao {
    
    private Connection transaction;

    public ResultDao() {
    }

    public ResultDao(Connection transaction) {
        this.transaction = transaction;
    }
    
    public void insertResult(Result r) {
        String query = "INSERT INTO RESULTS(result_id, appointment_lab_id, patient_id, exam_id, lab_worker_id, order, report, date, time) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = this.transaction.prepareStatement(query);
            pst.setInt(1, r.getReportId());
            pst.setInt(2, r.getAppointmentId());
            pst.setInt(3, r.getPatientId());
            pst.setInt(4, r.getExamId());
            pst.setString(5, r.getLabWorkerId());
            pst.setBlob(6, r.getOrder());
            pst.setBlob(7, r.getReportResult());
            pst.setDate(8, r.getDate());
            pst.setTime(9, r.getTime());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
