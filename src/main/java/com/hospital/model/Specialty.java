
package com.hospital.model;

/**
 *
 * @author cesar31
 */
public class Specialty {
    private int specialtyId;
    private String degree;
    private Double priceConsultation;
    
    //MEDICAL_DEGREES
    private int degreeId;
    private String doctorId;

    public Specialty(int specialtyId, String degree) {
        this.specialtyId = specialtyId;
        this.degree = degree;
    }

    public Specialty(int specialtyId, String degree, Double priceConsultation) {
        this.specialtyId = specialtyId;
        this.degree = degree;
        this.priceConsultation = priceConsultation;
    }

    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Double getPriceConsultation() {
        return priceConsultation;
    }

    public void setPriceConsultation(Double priceConsultation) {
        this.priceConsultation = priceConsultation;
    }

    public int getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(int degreeId) {
        this.degreeId = degreeId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}
