package com.hospital.model;

import com.hospital.controller.ReadXml;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import org.jdom2.Element;

/**
 *
 * @author cesar31
 */
public class Appointment {

    private int appointmentId;
    private int patientId;
    private String doctorId;
    private int examId;
    private Date date;
    private Time time;
    private boolean status;

    public Appointment(Element e) {
        this.appointmentId = Integer.parseInt(e.getChildText("CODIGO"));
        this.patientId = Integer.parseInt(e.getChildText("PACIENTE"));
        this.doctorId = e.getChildText("MEDICO");
        this.date = ReadXml.getDate(e.getChildText("FECHA"));
        this.time = ReadXml.getTime(e.getChildText("HORA"));
    }
    
    public Appointment(ResultSet rs, boolean lab) throws SQLException {
        if(lab) {
            this.appointmentId = rs.getInt("appointment_lab_id");
            this.examId = rs.getInt("exam_id");
        } else {
            this.appointmentId = rs.getInt("appointment_id");
        }
        this.patientId = rs.getInt("patient_id");
        this.doctorId = rs.getString("doctor_id");
        this.date = rs.getDate("date");
        this.time = rs.getTime("time");
        this.status = rs.getBoolean("status");
    }

    public Appointment(int appointmentId, int patientId, String doctorId, Date date, Time time) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" + "appointmentId=" + appointmentId + ", patientId=" + patientId + ", doctorId=" + doctorId + ", examId=" + examId + ", date=" + date + ", time=" + time + ", status=" + status + '}';
    }
}
