package com.hospital.dao;

import com.hospital.model.Specialty;
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
public class SpecialtyDao {

    private Connection transaction;

    public SpecialtyDao() {
    }

    public SpecialtyDao(Connection transaction) {
        this.transaction = transaction;
    }

    /**
     * Metodo para insertar una especialidad en la base de datos
     *
     * @param s
     */
    public void insertSpecialty(Specialty s) {
        String query = "INSERT INTO SPECIALTIES(degree, price_consultation) VALUES(?, ?)";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, s.getDegree());
            pst.setDouble(2, s.getPriceConsultation());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Metodo para insertar las especialidades o grados academicos de cada
     * medico
     *
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

    /**
     * Metodo para obtener listado de especialidades y precios
     *
     * @return
     */
    public List<Specialty> getSpecialties() {
        List<Specialty> specialties = new ArrayList<>();
        String query = "SELECT * FROM SPECIALTIES ORDER BY degree";
        try (PreparedStatement pst = this.transaction.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                specialties.add(new Specialty(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return specialties;
    }

    /**
     * Metodo para obtener las especialidades de un doctor
     * @param doctorId
     * @return 
     */
    public List<Specialty> getSpecialtiesByDoctor(String doctorId) {
        List<Specialty> specialties = new ArrayList<>();
        String query = "SELECT s.* FROM SPECIALTIES s INNER JOIN MEDICAL_DEGREES m ON s.specialty_id = m.specialty_id "
                + "WHERE m.doctor_id = ? ORDER BY s.degree";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, doctorId);
            try (ResultSet rs = pst.executeQuery()) {
                while(rs.next()) {
                    specialties.add(new Specialty(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return specialties;
    }
}
