package com.hospital.model;

import com.hospital.controller.ReadXml;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdom2.Element;

/**
 *
 * @author cesar31
 */
public class Patient extends Person {

    private int patientId;
    private boolean gender;
    private Date birth;
    private Double weight;
    private String blood;

    public Patient(Element e) {
        super(e);
        this.patientId = Integer.parseInt(e.getChildText("CODIGO"));
        this.gender = e.getChildText("SEXO").equals("Hombre");
        this.birth = ReadXml.getDate(e.getChildText("BIRTH"));
        this.weight = Double.parseDouble(e.getChildText("PESO"));
        this.blood = e.getChildText("SANGRE");
    }
    
    public Patient(ResultSet rs) throws SQLException {
        super(rs);
        this.patientId = rs.getInt("patient_id");
        this.gender = rs.getBoolean("gender");
        this.birth = rs.getDate("birth");
        this.weight = rs.getDouble("weight");
        this.blood = rs.getString("blood");
    }
    
    public Patient(int patientId, String name, String email, String pass) {
        super(name, email, pass);
        this.patientId = patientId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    @Override
    public String toString() {
        return super.toString() + "Patient{" + "patientId=" + patientId + ", gender=" + gender + ", birth=" + birth + ", weight=" + weight + ", blood=" + blood + '}';
    }
}
