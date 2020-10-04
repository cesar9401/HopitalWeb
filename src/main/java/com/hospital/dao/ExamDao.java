package com.hospital.dao;

import com.hospital.model.Exam;
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

    /**
     * Metodo para agregar un nuevo examen a la base de datos
     *
     * @param e
     */
    public void createExam(Exam e) {
        String query = "INSERT INTO EXAMS(name, exam_order, description, price, report) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, e.getName());
            pst.setBoolean(2, e.isOrder());
            pst.setString(3, e.getDescription());
            pst.setDouble(4, e.getPrice());
            pst.setBoolean(5, e.isReport());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Metodo para actualizar informacion de un examen
     *
     * @param e
     */
    public void updateExam(Exam e) {
        String query = "UPDATE EXAMS SET name = ?, exam_order = ?, description = ?, price = ?, report = ? WHERE exam_id = ?";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, e.getName());
            pst.setBoolean(2, e.isOrder());
            pst.setString(3, e.getDescription());
            pst.setDouble(4, e.getPrice());
            pst.setBoolean(5, e.isReport());
            pst.setInt(6, e.getExamId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Metodo para obtener listado de examenes
     *
     * @param random
     * @return
     */
    public List<Exam> getExams(boolean random) {
        List<Exam> exams = new ArrayList<>();
        String query = random ? "SELECT * FROM EXAMS ORDER BY RAND() LIMIT 6" : "SELECT * FROM EXAMS";
        try (PreparedStatement pst = this.transaction.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                exams.add(new Exam(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return exams;
    }

    /**
     * Metodo para obtener listado de examenes segun su nombre
     *
     * @param name
     * @return
     */
    public List<Exam> getExamsByName(String name) {
        String str = "%" + name + "%";
        List<Exam> exams = new ArrayList<>();
        String query = "SELECT * FROM EXAMS WHERE name LIKE ?";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, str);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    exams.add(new Exam(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return exams;
    }

    /**
     * Obtenter examenes segun rango de precio
     *
     * @param option
     * @param price
     * @return
     */
    public List<Exam> getExamsByPrice(int option, Double price) {
        List<Exam> exams = new ArrayList<>();
        String query = "SELECT * FROM EXAMS WHERE price";
        switch (option) {
            case 1:
                query += " = ?";
                break;
            case 2:
                query += " >= ?";
                break;
            case 3:
                query += " <= ?";
                break;
        }
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setDouble(1, price);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    exams.add(new Exam(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return exams;
    }

    /**
     * Obtener un examen segun su id
     *
     * @param examId
     * @return
     */
    public Exam getExamById(int examId) {
        Exam exam = null;
        String query = "SELECT * FROM EXAMS WHERE exam_id = ?";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setInt(1, examId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    exam = new Exam(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return exam;
    }
}
