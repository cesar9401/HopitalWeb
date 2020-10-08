package com.hospital.dao;

import com.hospital.model.Patient;
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
public class PatientDao {

    private Connection transaction;

    public PatientDao() {
    }

    public PatientDao(Connection transaction) {
        this.transaction = transaction;
    }

    /**
     * Metodo para insertar un paciente a la bd
     *
     * @param p
     */
    public void insertPatient(Patient p) {
        String query = "INSERT INTO PATIENTS(patient_id, name, gender, birth, dpi, phone, weight, blood, email, password) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setInt(1, p.getPatientId());
            pst.setString(2, p.getName());
            pst.setBoolean(3, p.isGender());
            pst.setDate(4, p.getBirth());
            pst.setString(5, p.getDpi());
            pst.setString(6, p.getPhone());
            pst.setDouble(7, p.getWeight());
            pst.setString(8, p.getBlood());
            pst.setString(9, p.getEmail());
            pst.setString(10, p.getPass());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Metodo para obtener un paciente de la bd segun su email y pass
     *
     * @param email
     * @param pass
     * @return
     */
    public Patient getPatien(String email, String pass) {
        Patient p = null;
        String query = "SELECT * FROM PATIENTS WHERE email = ? AND password = ?";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, email);
            pst.setString(2, pass);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    p = new Patient(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return p;
    }

    /**
     * Metodo para obtener un paciente segun su id
     *
     * @param patientId
     * @return
     */
    public Patient getPatientById(int patientId) {
        Patient p = null;
        String query = "SELECT * FROM PATIENTS WHERE patient_id = ? LIMIT 1";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setInt(1, patientId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    p = new Patient(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return p;
    }

    /**
     * Metodo para obtener listado de pacientes de la base de datos
     *
     * @return
     */
    public List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM PATIENTS";
        try (PreparedStatement ps = this.transaction.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                patients.add(new Patient(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return patients;
    }

    /**
     * Metodo para insertar un nuevo paciente a la base de datos
     * @param p
     * @return 
     */
    public int createNewPatient(Patient p) {
        int id = 0;
        String query = "INSERT INTO PATIENTS(name, gender, birth, dpi, phone, weight, blood, email, password) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = this.transaction.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, p.getName());
            pst.setBoolean(2, p.isGender());
            pst.setDate(3, p.getBirth());
            pst.setString(4, p.getDpi());
            pst.setString(5, p.getPhone());
            pst.setDouble(6, p.getWeight());
            pst.setString(7, p.getBlood());
            pst.setString(8, p.getEmail());
            pst.setString(9, p.getPass());
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return id;
    }
}
