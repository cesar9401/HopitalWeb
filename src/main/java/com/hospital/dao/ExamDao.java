
package com.hospital.dao;

import com.hospital.model.Exam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    
    public void insertExam(Exam e) {
        String query = "INSERT INTO EXAMS(exam_id, name, exam_order, description, price, report) VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
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
}
