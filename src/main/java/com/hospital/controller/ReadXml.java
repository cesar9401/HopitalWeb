package com.hospital.controller;

import com.hospital.model.Administrator;
import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.Exam;
import com.hospital.model.LabWorker;
import com.hospital.model.Patient;
import com.hospital.model.Report;
import com.hospital.model.Result;
import com.hospital.model.Specialty;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author cesar31
 */
public class ReadXml {

    public void getAdministrators() {
        List<Administrator> admins = new ArrayList<>();
        List<Element> listE = getData("admin");
        for(Element e : listE) {
            admins.add(new Administrator(e));
        }
        
        for(Administrator a : admins) {
            System.out.println(a.toString());
        }
    }
    
    public void getDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        List<Element> listEl = getData("doctor");
        for(Element e : listEl) {
            doctors.add(new Doctor(e));
        }
        
        for(Doctor d : doctors) {
            System.out.println(d.toString());
        }
    }
    
    public void getLabWorkers() {
        List<LabWorker> lab = new ArrayList<>();
        List<Element> listEl = getData("laboratorista");
        for(Element e : listEl) {
            lab.add(new LabWorker(e));
        }
        
        for(LabWorker l : lab) {
            System.out.println(l.toString());
        }
    }
    
    public void getPatients() {
        List<Patient> patients = new ArrayList<>();
        List<Element> listEl = getData("paciente");
        for(Element e : listEl) {
            patients.add(new Patient(e));
        }
        
        for(Patient p : patients) {
            System.out.println(p.toString());
        }
    }
    
    public void getExam() {
        List<Exam> exams = new ArrayList<>();
        List<Element> listEl = getData("examen");
        for(Element e : listEl) {
            exams.add(new Exam(e));
        }
        
        for(Exam e : exams){
            System.out.println(e.toString());
        }
    }
    
    public void getReports() {
        List<Report> reports = new ArrayList<>();
        List<Element> listEl = getData("reporte");
        for(Element e : listEl) {
            reports.add(new Report(e));
        }
        
        for(Report r: reports) {
            System.out.println(r.toString());
        }
    }
    
    public void getResults() {
        List<Result> results = new ArrayList<>();
        List<Element> listEl = getData("resultado");
        for(Element e : listEl) {
            results.add(new Result(e));
        } 
        
        for(Result r : results) {
            System.out.println(r.toString());
        }
    }

    public void getAppointments() {
        List<Appointment> appoint = new ArrayList<>();
        List<Element> listEl = getData("cita");
        for(Element e : listEl) {
            appoint.add(new Appointment(e));
        }
        
        for(Appointment a : appoint) {
            System.out.println(a.toString());
        }
    }
    
    public void getSpecialties() {
        List<Specialty> special = new ArrayList<>();
        List<Element> listEl = getData("consulta");
        for(Element e: listEl) {
            special.add(new Specialty(e));
        }
        
        for(Specialty c : special) {
            System.out.println(c.toString());
        }
    }
    
    private List<Element> getData(String node) {
        List<Element> elements = new ArrayList<>();
        try {
            SAXBuilder builder = new SAXBuilder();
            File xml = new File("data.xml");
            Document document = builder.build(xml);
            Element root = document.getRootElement();
            elements = root.getChildren(node);

        } catch (JDOMException | IOException ex) {
            ex.printStackTrace(System.out);
        }
        return elements;
    }
    
    public static java.sql.Date getDate(String source) {
        java.sql.Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = format.parse(source);
            date = new java.sql.Date(fecha.getTime());
        } catch (ParseException ex) {
            ex.printStackTrace(System.out);
        }
        return date;
    }
    
    public static java.sql.Time getTime(String source) {
        java.sql.Time time = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Long ms = format.parse(source).getTime();
            time = new java.sql.Time(ms);
        } catch (ParseException ex) {
            ex.printStackTrace(System.out);
        }
        return time;
    }
}
