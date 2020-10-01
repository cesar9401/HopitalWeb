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
public class Report implements Serializable {

    private int reportId;
    private int appointmentId;
    private int patientId;
    private String doctorId;
    private String report;
    private Date date;
    private Time time;

    public Report(Element e) {
        this.reportId = Integer.parseInt(e.getChildText("CODIGO"));
        this.patientId = Integer.parseInt(e.getChildText("PACIENTE"));
        this.doctorId = e.getChildText("MEDICO");
        this.report = e.getChildText("INFORME");
        this.date = ReadXml.getDate(e.getChildText("FECHA"));
        this.time = ReadXml.getTime(e.getChildText("HORA"));
    }

    public Report(ResultSet rs) throws SQLException {
        this.reportId = rs.getInt("report_id");
        this.appointmentId = rs.getInt("appointment_id");
        this.patientId = rs.getInt("patient_id");
        this.doctorId = rs.getString("doctor_id");
        this.report = rs.getString("report");
        this.date = rs.getDate("date");
        this.time = rs.getTime("time");
    }

    public Report(HttpServletRequest request) {
        this.appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        this.patientId = Integer.parseInt(request.getParameter("patientId"));
        this.doctorId = request.getParameter("doctorId");
        this.report = request.getParameter("report");
        this.date = ReadXml.getDate(request.getParameter("date"));
        this.time = ReadXml.getTime(request.getParameter("time"));
    }

    public Report(int reportId, int appointmentId, int patientId, Date date, Time time) {
        this.reportId = reportId;
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.date = date;
        this.time = time;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
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

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
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

    @Override
    public String toString() {
        return "Report{" + "reportId=" + reportId + ", appointmentId=" + appointmentId + ", patientId=" + patientId + ", doctorId=" + doctorId + ", report=" + report + ", date=" + date + ", time=" + time + '}';
    }
}
