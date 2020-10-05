package com.hospital.model;

import com.hospital.controller.ReadXml;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jdom2.Element;

/**
 *
 * @author cesar31
 */
public class Doctor extends Person implements Serializable{

    private String doctorId;
    private String collegiate;
    private Time startTime;
    private Time endTime;
    private Date startDate;
    private List<Specialty> specialties = new ArrayList<>();
    
    private int quantity;

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
    
    public Doctor(ResultSet rs) throws SQLException{
        super(rs);
        this.doctorId = rs.getString("doctor_id");
        this.collegiate = rs.getString("collegiate");
        this.startTime = rs.getTime("start_time");
        this.endTime = rs.getTime("end_time");
        this.startDate = rs.getDate("start_date");
        String s = rs.getString("specialties");
        setSpecialty(s);        
    }
    
    public Doctor(HttpServletRequest request) throws UnsupportedEncodingException{
        super(request);
        this.doctorId = new String(request.getParameter("doctorId").getBytes("ISO-8859-1"), "UTF-8");
        this.collegiate = request.getParameter("collegiate");
        this.startTime = ReadXml.getTime(request.getParameter("startTime"));
        this.endTime = ReadXml.getTime(request.getParameter("endTime"));
        this.startDate = ReadXml.getDate(request.getParameter("date"));
    }
    
    private void setSpecialty(String s) {
        String [] sp = s.split(",");
        for (String sp1 : sp) {
            specialties.add(new Specialty(sp1));
        }
    }

    public Doctor(String doctorId, String collegiate, Time startTime, Time endTime, int quantity) {
        this.doctorId = doctorId;
        this.collegiate = collegiate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return super.toString() + "Doctor{" + "doctorId=" + doctorId + ", collegiate=" + collegiate + ", startTime=" + startTime + ", endTime=" + endTime + ", startDate=" + startDate + '}';
    }
}
