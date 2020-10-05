package com.hospital.model;

import com.hospital.controller.ReadXml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import org.jdom2.Element;

/**
 *
 * @author cesar31
 */
public class Result implements Serializable{

    private int resultId;
    private int appointmentLabId;
    private int patientId;
    private Date date;
    private Time time;

    private String labWorkerId;
    private int examId;
    private InputStream orderResult;
    private InputStream reportResult;
    
    private String patientName;
    private String labWorkerName;
    private String examName;

    public Result(Element e) {
        //super(e);
        this.resultId = Integer.parseInt(e.getChildText("CODIGO"));
        this.patientId = Integer.parseInt(e.getChildText("PACIENTE"));
        this.examId = Integer.parseInt(e.getChildText("EXAMEN"));
        this.labWorkerId = e.getChildText("LABORATORISTA");
        try {
            String ord = e.getChildText("ORDEN");
            String rs = e.getChildText("INFORME");

            if (ord.length() > 0) {
                System.out.println(ord.length());
                this.orderResult = new FileInputStream(new File(ord));
            }

            if (rs.length() > 0) {
                System.out.println(rs.length());
                this.reportResult = new FileInputStream(new File(rs));
            }

        } catch (FileNotFoundException ex) {
            //ex.printStackTrace(System.out);
            System.out.println("Pruebas sin archivos");
        }
        this.date = ReadXml.getDate(e.getChildText("FECHA"));
        this.time = ReadXml.getTime(e.getChildText("HORA"));        
    }

    public Result(ResultSet rs) throws SQLException {
        this.resultId = rs.getInt("result_id");
        this.appointmentLabId = rs.getInt("appointment_lab_id");
        this.patientId = rs.getInt("patient_id");
        this.examId = rs.getInt("exam_id");
        this.labWorkerId = rs.getString("lab_worker_id");
        this.orderResult = (InputStream) rs.getBlob("exam_order");
        this.reportResult = (InputStream) rs.getBlob("report");
        this.date = rs.getDate("date");
        this.time = rs.getTime("time");
        
        this.patientName = rs.getString("patient");
        this.labWorkerName = rs.getString("labWorker");
        this.examName = rs.getString("exam");
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getAppointmentLabId() {
        return appointmentLabId;
    }

    public void setAppointmentLabId(int appointmentLabId) {
        this.appointmentLabId = appointmentLabId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
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

    public String getLabWorkerId() {
        return labWorkerId;
    }

    public void setLabWorkerId(String labWorkerId) {
        this.labWorkerId = labWorkerId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public InputStream getOrderResult() {
        return orderResult;
    }

    public void setOrderResult(InputStream orderResult) {
        this.orderResult = orderResult;
    }

    public InputStream getReportResult() {
        return reportResult;
    }

    public void setReportResult(InputStream reportResult) {
        this.reportResult = reportResult;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getLabWorkerName() {
        return labWorkerName;
    }

    public void setLabWorkerName(String labWorkerName) {
        this.labWorkerName = labWorkerName;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
    
    @Override
    public String toString() {
        return "Result{" + "resultId=" + resultId + ", appointmentLabId=" + appointmentLabId + ", patientId=" + patientId + ", date=" + date + ", time=" + time + ", labWorkerId=" + labWorkerId + ", examId=" + examId + ", orderResult=" + orderResult + ", reportResult=" + reportResult + '}';
    }
}
