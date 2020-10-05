package com.hospital.controller;

import com.hospital.conexion.Conexion;
import com.hospital.dao.*;
import com.hospital.model.*;
import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.Part;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author cesar31
 */
public class ReadXml {

    private final Part filePart;
    private Connection conexion;

    public ReadXml(Part filePart) {
        this.filePart = filePart;
        getConnection();
    }

    private void getConnection() {
        this.conexion = Conexion.getConnection();
    }

    public void laodData() {
        getAdministrators();
        getSpecialties();
        getDoctors();
        getExam();
        getLabWorkers();
        getPatients();
        getReports();
        getResults();
        getAppointments();
    }

    public void getAdministrators() {
        List<Administrator> admins = new ArrayList<>();
        List<Element> listE = getData("admin");
        for (Element e : listE) {
            admins.add(new Administrator(e));
        }

        AdministratorDao dao = new AdministratorDao(conexion);
        dao.insertDays();
        for (Administrator a : admins) {
            dao.insertAdministrator(a);
            System.out.println(a.toString());
        }
    }

    public void getDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        List<Element> listEl = getData("doctor");
        for (Element e : listEl) {
            doctors.add(new Doctor(e));
        }

        DoctorDao dao = new DoctorDao(conexion);
        SpecialtyDao sDao = new SpecialtyDao(conexion);

        for (Doctor d : doctors) {
            dao.insertDoctor(d);
            System.out.println(d.toString());
            for (Specialty s : d.getSpecialties()) {
                Specialty tmp = new Specialty(s.getDegree());
                tmp.setDoctorId(d.getDoctorId());
                sDao.insertMedicalDegree(tmp);
                System.out.println(tmp.toString());
            }
        }
    }

    public void getLabWorkers() {
        List<LabWorker> lab = new ArrayList<>();
        List<Element> listEl = getData("laboratorista");
        for (Element e : listEl) {
            lab.add(new LabWorker(e));
        }

        LabWorkerDao dao = new LabWorkerDao(conexion);
        for (LabWorker l : lab) {
            dao.insertLabWoker(l);
            dao.insertLabWorkersDays(l);
            System.out.println(l.toString());
        }
    }

    public void getPatients() {
        List<Patient> patients = new ArrayList<>();
        List<Element> listEl = getData("paciente");
        for (Element e : listEl) {
            patients.add(new Patient(e));
        }

        PatientDao dao = new PatientDao(conexion);
        for (Patient p : patients) {
            dao.insertPatient(p);
            System.out.println(p.toString());
        }
    }

    public void getExam() {
        List<Exam> exams = new ArrayList<>();
        List<Element> listEl = getData("examen");
        for (Element e : listEl) {
            exams.add(new Exam(e));
        }

        ExamDao dao = new ExamDao(conexion);
        for (Exam e : exams) {
            dao.insertExam(e);
            System.out.println(e.toString());
        }
    }

    public void getReports() {
        List<Report> reports = new ArrayList<>();
        List<Element> listEl = getData("reporte");
        for (Element e : listEl) {
            reports.add(new Report(e));
        }

        ReportDao dao = new ReportDao(conexion);
        for (Report r : reports) {
            dao.insertReport(r);
            System.out.println(r.toString());
        }
    }

    public void getResults() {
        List<Result> results = new ArrayList<>();
        List<Element> listEl = getData("resultado");
        for (Element e : listEl) {
            results.add(new Result(e));
        }

        ResultDao dao = new ResultDao(conexion);
        AppointmentDao aDao = new AppointmentDao(conexion);
        for (Result r : results) {
            int id = aDao.insertAppointmentLab(r);
            r.setAppointmentLabId(id);
            dao.insertResult(r);
            System.out.println(r.toString());
        }
    }

    public void getAppointments() {
        List<Appointment> appoint = new ArrayList<>();
        List<Element> listEl = getData("cita");
        for (Element e : listEl) {
            appoint.add(new Appointment(e));
        }

        AppointmentDao dao = new AppointmentDao(conexion);
        for (Appointment a : appoint) {
            dao.insertAppointment(a);
            System.out.println(a.toString());
        }
    }

    public void getSpecialties() {
        List<Specialty> specialties = new ArrayList<>();
        List<Element> listEl = getData("consulta");
        for (Element e : listEl) {
            specialties.add(new Specialty(e));
        }

        SpecialtyDao dao = new SpecialtyDao(conexion);
        for (Specialty s : specialties) {
            dao.insertSpecialty(s);
            System.out.println(s.toString());
        }
    }

    private List<Element> getData(String node) {
        List<Element> elements = new ArrayList<>();
        try {
            SAXBuilder builder = new SAXBuilder();
            //File xml = new File("data.xml");
            InputStream input = filePart.getInputStream();
            Document document = builder.build(input);
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
            java.util.Date fecha = format.parse(source);
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
