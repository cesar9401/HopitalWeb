package com.hospital.model;

import com.hospital.controller.ReadXml;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
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
    
    private String degree;
    
    private int specialtyId;
    
    private String patientName;
    private String doctorName;
    
    private int quantity;
    private String dates;
    
    private double total;

    public Report(Element e) {
        this.reportId = Integer.parseInt(e.getChildText("CODIGO"));
        this.patientId = Integer.parseInt(e.getChildText("PACIENTE"));
        this.doctorId = e.getChildText("MEDICO");
        this.degree = e.getChildText("TIPO");
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
        
        this.patientName = rs.getString("patient");
        this.doctorName = rs.getString("doctor");
        this.degree = rs.getString("kind");
    }

    public Report(HttpServletRequest request) throws UnsupportedEncodingException {
        this.appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        this.patientId = Integer.parseInt(request.getParameter("patientId"));
        this.doctorId = request.getParameter("doctorId");
                this.report = new String(request.getParameter("report").getBytes("ISO-8859-1"), "UTF-8");
        this.date = ReadXml.getDate(request.getParameter("date"));
        this.time = ReadXml.getTime(request.getParameter("time"));
        
        this.specialtyId = Integer.parseInt(request.getParameter("specialtyId"));
    }

    public Report(String doctorId, String doctorName, int quantity, String dates) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.quantity = quantity;
        this.dates = dates;
    }

    public Report(String doctorId, String doctorName, double total) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.total = total;
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

    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
    
    @Override
    public String toString() {
        return "Report{" + "reportId=" + reportId + ", appointmentId=" + appointmentId + ", patientId=" + patientId + ", doctorId=" + doctorId + ", report=" + report + ", date=" + date + ", time=" + time + '}';
    }
}
