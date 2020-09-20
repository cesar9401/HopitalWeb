
package com.hospital.model;

import java.sql.Date;

/**
 *
 * @author cesar31
 */
public class Patient extends Person{
    private int patientId;
    private boolean gender;
    private Date birth;
    private Double weight;
    private String blood;

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
