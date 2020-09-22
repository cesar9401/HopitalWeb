package com.hospital.dao;

import com.hospital.model.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        try ( PreparedStatement pst = this.transaction.prepareStatement(query)) {
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
        try ( PreparedStatement pst = this.transaction.prepareStatement(query)) {
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
}
