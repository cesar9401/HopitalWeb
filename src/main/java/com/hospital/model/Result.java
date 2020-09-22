package com.hospital.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import org.jdom2.Element;

/**
 *
 * @author cesar31
 */
public class Result extends Report {

    private String labWorkerId;
    private int examId;
    private InputStream orderResult;
    private InputStream reportResult;

    public Result(Element e) {
        super(e);
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
    }

    public Result(String labWorkerId, int reportId, int appointmentId, int patientId, Date date, Time time) {
        super(reportId, appointmentId, patientId, date, time);
        this.labWorkerId = labWorkerId;
    }

    public Result(int examId, int reportId, int appointmentId, int patientId, Date date, Time time) {
        super(reportId, appointmentId, patientId, date, time);
        this.examId = examId;
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

    @Override
    public String toString() {
        return super.toString() + "Result{" + "labWorkerId=" + labWorkerId + ", examId=" + examId + ", orderResult=" + orderResult + ", reportResult=" + reportResult + '}';
    }
}
