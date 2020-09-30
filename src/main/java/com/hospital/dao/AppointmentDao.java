package com.hospital.dao;

import com.hospital.model.Appointment;
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
public class AppointmentDao {

    private Connection transaction;

    public AppointmentDao() {
    }

    public AppointmentDao(Connection transaction) {
        this.transaction = transaction;
    }

    /**
     * Metodo para insertar citas con el medico, recibo un objeto de tipo cita
     *
     * @param a
     */
    public void insertAppointment(Appointment a) {
        String query = "INSERT INTO APPOINTMENTS(appointment_id, patient_id, doctor_id, date, time, status) VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setInt(1, a.getAppointmentId());
            pst.setInt(2, a.getPatientId());
            pst.setString(3, a.getDoctorId());
            pst.setDate(4, a.getDate());
            pst.setTime(5, a.getTime());
            pst.setBoolean(6, a.isStatus());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Metodo para insertar una nueva cita en el sistema
     *
     * @param a
     */
    public void insertNewAppointment(Appointment a) {
        String query = "INSERT INTO APPOINTMENTS(patient_id, doctor_id, specialty_id, date, time) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setInt(1, a.getPatientId());
            pst.setString(2, a.getDoctorId());
            pst.setInt(3, a.getSpecialtyId());
            pst.setDate(4, a.getDate());
            pst.setTime(5, a.getTime());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Metodo para insertar una cita en laboratorio, recibe objeto de tipo cita
     *
     * @param a
     */
    public void inserAppointmentLab(Appointment a) {
        String query = "INSERT INTO APPOINTMENTS_LAB(appointment_lab_id, patient_id, doctor_id, exam_id, date, time, status) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setInt(1, a.getAppointmentId());
            pst.setInt(2, a.getPatientId());
            pst.setString(3, a.getDoctorId());
            pst.setInt(4, a.getExamId());
            pst.setDate(5, a.getDate());
            pst.setTime(6, a.getTime());
            pst.setBoolean(7, a.isStatus());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Metodo para obtener las citas medicas y de lab de algun paciente
     *
     * @param patientId
     * @param status
     * @param lab
     * @return
     */
    public List<Appointment> getAppointmentsByPatient(int patientId, boolean status, boolean lab) {
        List<Appointment> appointments = new ArrayList<>();
        String query;
        if (lab) {
            query = "SELECT * FROM APPOINTMENTS_LAB WHERE patient_id = ? AND status = ? ORDER BY date, time";
        } else {
            query = "SELECT a.*, d.name AS doctor_name, s.degree, p.name AS patient_name FROM APPOINTMENTS a INNER JOIN DOCTORS d ON a.doctor_id = d.doctor_id "
                    + "INNER JOIN SPECIALTIES s ON a.specialty_id = s.specialty_id INNER JOIN PATIENTS p ON a.patient_id = p.patient_id"
                    + "WHERE a.patient_id = ? AND a.status = ? ORDER BY a.date, a.time;";
        }
        try (PreparedStatement pst = this.transaction.prepareStatement(query);) {
            pst.setInt(1, patientId);
            pst.setBoolean(2, status);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    appointments.add(new Appointment(rs, lab));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return appointments;
    }

    /**
     * Metodo para obtener las citas de un medico segun determinada fecha
     *
     * @param doctorId
     * @param date
     * @param lab
     * @return
     */
    public List<Appointment> getAppointmentsByDoctor(String doctorId, java.sql.Date date, boolean lab) {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT a.*, d.name AS doctor_name, s.degree, p.name AS patient_name FROM APPOINTMENTS a INNER JOIN DOCTORS d ON a.doctor_id = d.doctor_id "
                + "INNER JOIN SPECIALTIES s ON a.specialty_id = s.specialty_id INNER JOIN PATIENTS p ON a.patient_id = p.patient_id "
                + "WHERE a.doctor_id = ? AND a.date = ? ORDER BY time";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, doctorId);
            pst.setDate(2, date);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                appointments.add(new Appointment(rs, lab));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return appointments;
    }

    public Appointment getAppointmentById(int appId) {
        Appointment app = null;
        String query = "SELECT a.*, d.name AS doctor_name, s.degree, p.name AS patient_name FROM APPOINTMENTS a INNER JOIN DOCTORS d ON a.doctor_id = d.doctor_id "
                + "INNER JOIN SPECIALTIES s ON a.specialty_id = s.specialty_id INNER JOIN PATIENTS p ON a.patient_id = p.patient_id "
                + "WHERE a.appointment_id = ? LIMIT 1";

        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setInt(1, appId);
            try (ResultSet rs = pst.executeQuery()) {
                if(rs.next()) {
                    app = new Appointment(rs, false);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return app;
    }
}
