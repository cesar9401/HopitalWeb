package com.hospital.dao;

import com.hospital.model.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author cesar31
 */
public class ReportDao {

    private Connection transaction;

    public ReportDao() {
    }

    public ReportDao(Connection transaction) {
        this.transaction = transaction;
    }

    public void insertReport(Report r) {
        String query = "INSERT INTO REPORTS(report_id, appointment_id, patient_id, doctor_id, report, date, time) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setInt(1, r.getReportId());
            pst.setInt(2, r.getAppointmentId());
            pst.setInt(3, r.getPatientId());
            pst.setString(4, r.getDoctorId());
            pst.setString(5, r.getReport());
            pst.setDate(6, r.getDate());
            pst.setTime(7, r.getTime());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
