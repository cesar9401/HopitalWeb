package com.hospital.dao;

import com.hospital.model.Doctor;
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
public class DoctorDao {

    private Connection transaction;

    public DoctorDao() {
    }

    public DoctorDao(Connection transaction) {
        this.transaction = transaction;
    }

    /**
     * Metodo para insertar a un doctor a la base de datos
     *
     * @param doc
     */
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

    /**
     * Metodo para obtener un doctor segun su email y pass en la base de datos
     *
     * @param email
     * @param pass
     * @return
     */
    public Doctor getDoctor(String email, String pass) {
        Doctor d = null;
        String query = "SELECT d.*, GROUP_CONCAT(s.degree) AS specialties, GROUP_CONCAT(m.specialty_id) AS specialties_doc FROM DOCTORS d INNER JOIN MEDICAL_DEGREES m ON d.doctor_id = m.doctor_id INNER JOIN SPECIALTIES s ON s.specialty_id = m.specialty_id "
                + "WHERE d.email = ? AND d.password = ? GROUP BY d.doctor_id;";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, email);
            pst.setString(2, pass);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    d = new Doctor(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return d;
    }
    
    public Doctor getDoctor(String doctorId) {
        Doctor d = null;
        String query = "SELECT d.*, GROUP_CONCAT(s.degree) AS specialties, GROUP_CONCAT(m.specialty_id) AS specialties_doc FROM DOCTORS d INNER JOIN MEDICAL_DEGREES m ON d.doctor_id = m.doctor_id INNER JOIN SPECIALTIES s ON s.specialty_id = m.specialty_id "
                + "WHERE d.doctor_id = ? GROUP BY d.doctor_id;";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, doctorId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    d = new Doctor(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return d;
    }

    public List<Doctor> getDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT d.*, GROUP_CONCAT(s.degree) AS specialties, GROUP_CONCAT(m.specialty_id) AS specialties_doc FROM DOCTORS d INNER JOIN MEDICAL_DEGREES m ON d.doctor_id = m.doctor_id INNER JOIN SPECIALTIES s ON s.specialty_id = m.specialty_id "
                + "GROUP BY d.doctor_id";
        try (PreparedStatement pst = this.transaction.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                doctors.add(new Doctor(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return doctors;
    }

    public List<Doctor> getDoctorsBySpeciality(int specialityId) {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT d.*, GROUP_CONCAT(s.degree) AS specialties, GROUP_CONCAT(m.specialty_id) AS specialties_doc FROM DOCTORS d INNER JOIN MEDICAL_DEGREES m ON d.doctor_id = m.doctor_id INNER JOIN SPECIALTIES s ON s.specialty_id = m.specialty_id "
                + "GROUP BY d.doctor_id HAVING specialties_doc LIKE ?";

        try (PreparedStatement pst = this.transaction.prepareStatement(query);) {
            pst.setString(1, "%" + specialityId + "%");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    doctors.add(new Doctor(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return doctors;
    }
}
