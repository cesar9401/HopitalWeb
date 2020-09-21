package com.hospital.model;

import com.hospital.controller.ReadXml;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Element;

/**
 *
 * @author cesar31
 */
public class Doctor extends Person {

    private String doctorId;
    private String collegiate;
    private Time startTime;
    private Time endTime;
    private Date startDate;
    private List<Specialty> specialties = new ArrayList<>();

    public Doctor(Element e) {
        super(e);
        this.doctorId = e.getChildText("CODIGO");
        this.collegiate = e.getChildText("COLEGIADO");
        Element el = e.getChild("ESPECIALIDAD");
        List<Element> elChild = el.getChildren();
        for (Element i : elChild) {
            specialties.add(new Specialty(i.getText()));
        }
        Element ele = e.getChild("HORARIO");
        this.startTime = ReadXml.getTime(ele.getChildText("INICIO"));
        this.endTime = ReadXml.getTime(ele.getChildText("FIN"));
        this.startDate = ReadXml.getDate(e.getChildText("TRABAJO"));
    }

    public Doctor(String doctorId, String name, String email, String pass) {
        super(name, email, pass);
        this.doctorId = doctorId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getCollegiate() {
        return collegiate;
    }

    public void setCollegiate(String collegiate) {
        this.collegiate = collegiate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    @Override
    public String toString() {
        return super.toString() + "Doctor{" + "doctorId=" + doctorId + ", collegiate=" + collegiate + ", startTime=" + startTime + ", endTime=" + endTime + ", startDate=" + startDate + '}';
    }
}
