package com.hospital.model;

import com.hospital.controller.ReadXml;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Element;

/**
 *
 * @author cesar31
 */
public class LabWorker extends Person {

    private String labWorkerId;
    private String registry;
    private Date startDate;
    private int examId;
    private String examName;
    private List<Day> days = new ArrayList<>();
    private Exam exam;

    public LabWorker(Element e) {
        super(e);
        this.labWorkerId = e.getChildText("CODIGO");
        this.registry = e.getChildText("REGISTRO");
        this.examName = e.getChildText("EXAMEN");
        Element el = e.getChild("TRABAJO");
        List<Element> elChild = el.getChildren();
        for (Element i : elChild) {
            days.add(Day.valueOf(i.getText()));
            }
        this.startDate = ReadXml.getDate(e.getContent(17).getValue());
    }
    
    public LabWorker(ResultSet rs) throws SQLException {
        super(rs);
        this.labWorkerId = rs.getString("lab_worker_id");
        this.registry = rs.getString("registry_number");
        this.startDate = rs.getDate("start_date");
        this.examId = rs.getInt("exam_id");
        String d = rs.getString("name_days");
        setDay(d);
    }

    public LabWorker(String labWorkerId, String name, String email, String pass) {
        super(name, email, pass);
        this.labWorkerId = labWorkerId;
    }
    
    private void setDay(String d) {
        String[] sd = d.split(",");
        for(String sd1 : sd) {
            days.add(Day.valueOf(sd1));
        }
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

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
    
    @Override
    public String toString() {
        return super.toString() + "LabWorker{" + "labWorkerId=" + labWorkerId + ", registry=" + registry + ", startDate=" + startDate + ", examId=" + examId + '}';
    }
}
