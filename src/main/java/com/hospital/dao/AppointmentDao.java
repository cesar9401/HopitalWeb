package com.hospital.dao;

import com.hospital.model.Appointment;
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

    public List<Appointment> getAppointmentsByPatient(int patientId, boolean status, boolean lab) {
        List<Appointment> appointments = new ArrayList<>();
        String query;
        if (lab) {
            query = "SELECT * FROM APPOINTMENTS_LAB WHERE patient_id = ? AND status = ? ORDER BY date, time";
        } else {
            query = "SELECT * FROM APPOINTMENTS WHERE patient_id = ? AND status = ? ORDER BY date, time";
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

    public List<Appointment> getAppointmentsByDoctor(String doctorId, java.sql.Date date, boolean status, boolean lab) {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM APPOINTMENTS WHERE doctor_id = ? AND date = ? AND status = ? ORDER BY time";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, doctorId);
            pst.setDate(2, date);
            pst.setBoolean(3, status);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                appointments.add(new Appointment(rs, lab));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return appointments;
    }
}
