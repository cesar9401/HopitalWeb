package com.hospital.dao;

import com.hospital.model.Specialty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author cesar31
 */
public class SpecialtyDao {

    private Connection transaction;

    public SpecialtyDao() {
    }

    public SpecialtyDao(Connection transaction) {
        this.transaction = transaction;
    }

    /**
     * Metodo para insertar una especialidad en la base de datos
     * @param s 
     */
    public void insertSpecialty(Specialty s) {
        String query = "INSERT INTO SPECIALTIES(degree, price_consultation) VALUES(?, ?)";
        try ( PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, s.getDegree());
            pst.setDouble(2, s.getPriceConsultation());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    /**
     * Metodo para insertar las especialidades o grados academicos de cada medico
     * @param s 
     */
    public void insertMedicalDegree(Specialty s) {
        String query = "INSERT INTO MEDICAL_DEGREES(doctor_id, specialty_id) VALUES(?, (SELECT specialty_id FROM SPECIALTIES WHERE degree = ? LIMIT 1))";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, s.getDoctorId());
            pst.setString(2, s.getDegree());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}