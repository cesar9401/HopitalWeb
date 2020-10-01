package com.hospital.model;

import com.hospital.controller.ReadXml;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javax.servlet.http.HttpServletRequest;
import org.jdom2.Element;

/**
 *
 * @author cesar31
 */
public class Appointment implements Serializable {

    private int appointmentId;
    private int patientId;
    private String doctorId;
    private int examId;
    private int specialtyId;
    private Date date;
    private Time time;
    private boolean status;
    private boolean isAvailable;

    private String degree;
    private String doctorName;
    private String patientName;

    public Appointment(java.sql.Time time, boolean isAvailable) {
        this.time = time;
        this.isAvailable = isAvailable;
    }

    public Appointment(Element e) {
        this.appointmentId = Integer.parseInt(e.getChildText("CODIGO"));
        this.patientId = Integer.parseInt(e.getChildText("PACIENTE"));
        this.doctorId = e.getChildText("MEDICO");
        this.date = ReadXml.getDate(e.getChildText("FECHA"));
        this.time = ReadXml.getTime(e.getChildText("HORA"));
        //Sujeto a cambios
        this.degree = e.getChildText("TIPO");
    }

    public Appointment(ResultSet rs, boolean lab) throws SQLException {
        if (lab) {
            this.appointmentId = rs.getInt("appointment_lab_id");
            this.examId = rs.getInt("exam_id");
        } else {
            this.appointmentId = rs.getInt("appointment_id");
            this.specialtyId = rs.getInt("specialty_id");
            this.doctorName = rs.getString("doctor_name");
            this.degree = rs.getString("degree");
            this.patientName = rs.getString("patient_name");
        }
        this.patientId = rs.getInt("patient_id");
        this.doctorId = rs.getString("doctor_id");
        this.date = rs.getDate("date");
        this.time = rs.getTime("time");
        this.status = rs.getBoolean("status");
        this.isAvailable = false;
    }

    public Appointment(HttpServletRequest request, boolean lab) {
        this.doctorId = request.getParameter("doctorId");
        this.patientId = Integer.parseInt(request.getParameter("patientId"));
        this.specialtyId = Integer.parseInt(request.getParameter("specialty"));
        this.time = ReadXml.getTime(request.getParameter("AppTime"));
        this.date = (java.sql.Date) request.getSession().getAttribute("date");
        
        if(lab) {
            System.out.println("lab");
        }
    }

    public Appointment(int patientId, String doctorId, int specialtyId, Date date, Time time) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.specialtyId = specialtyId;
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

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Override
    public String toString() {
        return "Appointment{" + "appointmentId=" + appointmentId + ", patientId=" + patientId + ", doctorId=" + doctorId + ", specialtyId=" + specialtyId + ", date=" + date + ", time=" + time + '}';
    }
}
