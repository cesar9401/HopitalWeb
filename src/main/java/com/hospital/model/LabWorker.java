package com.hospital.model;

import com.hospital.controller.ReadXml;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Element;
import org.jdom2.Namespace;

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

    @Override
    public String toString() {
        return super.toString() + "LabWorker{" + "labWorkerId=" + labWorkerId + ", registry=" + registry + ", startDate=" + startDate + ", examId=" + examId + '}';
    }
}
