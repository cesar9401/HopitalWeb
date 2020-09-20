
package com.hospital.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cesar31
 */
public class LabWorker extends Person{
    private String labWorkerId;
    private String registry;
    private Date startDate;
    private int examId;
    private List<Day> days = new ArrayList<>();

    public LabWorker(String labWorkerId, String name, String email, String pass) {
        super(name, email, pass);
        this.labWorkerId = labWorkerId;
    }

    public String getLabWorkerId() {
        return labWorkerId;
    }

    public void setLabWorkerId(String labWorkerId) {
        this.labWorkerId = labWorkerId;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
    
    @Override
    public String toString() {
        return super.toString() + "LabWorker{" + "labWorkerId=" + labWorkerId + ", registry=" + registry + ", startDate=" + startDate + ", examId=" + examId + '}';
    }
}
