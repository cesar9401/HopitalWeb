package com.hospital.dao;

import com.hospital.model.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Metodo para insertar un resultado a la base de datos
     *
     * @param r
     */
    public void insertResult(Result r) {
        String query = "INSERT INTO RESULTS(result_id, appointment_lab_id, patient_id, exam_id, lab_worker_id, report, date, time) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        String queryIn = "INSERT INTO INCOMES(result_id, income) VALUES(?, (SELECT price FROM EXAMS WHERE exam_id = ? LIMIT 1))";
        try {
            PreparedStatement pst = this.transaction.prepareStatement(query);
            pst.setInt(1, r.getResultId());
            pst.setInt(2, r.getAppointmentLabId());
            pst.setInt(3, r.getPatientId());
            pst.setInt(4, r.getExamId());
            pst.setString(5, r.getLabWorkerId());
            //pst.setBlob(6, r.getOrderResult());
            pst.setBlob(6, r.getReportResult());
            pst.setDate(7, r.getDate());
            pst.setTime(8, r.getTime());
            pst.executeUpdate();
            
            pst = this.transaction.prepareStatement(queryIn);
            pst.setInt(1, r.getResultId());
            pst.setInt(2, r.getExamId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Resultados de laboratorio de cada paciente
     *
     * @param patient_id
     * @return
     */
    public List<Result> getResultsByPatient(int patient_id) {
        List<Result> results = new ArrayList<>();
        //String query = "SELECT * FROM RESULTS WHERE patient_id = ? ORDER BY date";
        String query = "SELECT r.*, p.name AS patient, l.name AS labWorker, e.name AS exam FROM RESULTS r INNER JOIN PATIENTS p ON r.patient_id = p.patient_id INNER JOIN LAB_WORKERS l ON r.lab_worker_id = l.lab_worker_id INNER JOIN EXAMS e ON r.exam_id = e.exam_id "
                + "WHERE r.patient_id = ? ORDER BY date";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setInt(1, patient_id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    results.add(new Result(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return results;
    }
}
