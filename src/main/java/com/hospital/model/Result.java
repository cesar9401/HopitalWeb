
package com.hospital.model;

import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author cesar31
 */
public class Result extends Report{
    private String labWorkerId;
    private int examId;
    private InputStream order;
    private InputStream reportResult;

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

    public InputStream getOrder() {
        return order;
    }

    public void setOrder(InputStream order) {
        this.order = order;
    }

    public InputStream getReportResult() {
        return reportResult;
    }

    public void setReportResult(InputStream reportResult) {
        this.reportResult = reportResult;
    }
}
