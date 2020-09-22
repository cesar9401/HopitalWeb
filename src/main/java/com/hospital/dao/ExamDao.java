package com.hospital.dao;

import com.hospital.model.Exam;
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
public class ExamDao {

    private Connection transaction;

    public ExamDao() {
    }

    public ExamDao(Connection transaction) {
        this.transaction = transaction;
    }

    /**
     * Metodo para insertar un examen a la base de datos
     *
     * @param e
     */
    public void insertExam(Exam e) {
        String query = "INSERT INTO EXAMS(exam_id, name, exam_order, description, price, report) VALUES(?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setInt(1, e.getExamId());
            pst.setString(2, e.getName());
            pst.setBoolean(3, e.isOrder());
            pst.setString(4, e.getDescription());
            pst.setDouble(5, e.getPrice());
            pst.setBoolean(6, e.isReport());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Metodo para obtener listado de examenes
     *
     * @return
     */
    public List<Exam> getExams() {
        List<Exam> exams = new ArrayList<>();
        String query = "SELECT * FROM EXAMS";
        try ( PreparedStatement pst = this.transaction.prepareStatement(query);  ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                exams.add(new Exam(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return exams;
    }
}
