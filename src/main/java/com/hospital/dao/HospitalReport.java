package com.hospital.dao;

import com.hospital.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cesar31
 */
public class HospitalReport {

    private final Connection transaction;

    public HospitalReport(Connection transaction) {
        this.transaction = transaction;
    }

    /**
     * Medico -> Reporte de citas agendadas en un intervalo de tiempo
     *
     * @param doctorId
     * @param date1
     * @param date2
     * @return
     */
    public List<Appointment> getAppointmentsByDoctor(String doctorId, java.sql.Date date1, java.sql.Date date2) {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT a.*, d.name AS doctor_name, s.degree, p.name AS patient_name FROM APPOINTMENTS a INNER JOIN DOCTORS d ON a.doctor_id = d.doctor_id "
                + "INNER JOIN SPECIALTIES s ON a.specialty_id = s.specialty_id INNER JOIN PATIENTS p ON a.patient_id = p.patient_id "
                + "WHERE a.doctor_id = ? AND a.date BETWEEN ? AND ? ORDER BY date,time";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setString(1, doctorId);
            pst.setDate(2, date1);
            pst.setDate(3, date2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                appointments.add(new Appointment(rs, false));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return appointments;
    }

    /**
     * Medico -> Reporte de citas para el día en curso
     *
     * @param doctorId
     * @param date
     * @return
     */
    public List<Appointment> getAppointmentsByDoctorToday(String doctorId, java.sql.Date date) {
        AppointmentDao aDao = new AppointmentDao(this.transaction);
        return aDao.getAppointmentsByDoctor(doctorId, date, false);
    }

    /**
     * Pacientes con mas reportes medicos en un intervalo de tiempo
     * 
     * @param date1
     * @param date2
     * @return 
     */
    public List<Patient> patientsWithMostReports(java.sql.Date date1, java.sql.Date date2) {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT p.*, COUNT(*) AS quantity FROM REPORTS r INNER JOIN PATIENTS p ON r.patient_id = p.patient_id "
                + "WHERE r.date BETWEEN ? AND ? GROUP BY r.patient_id ORDER BY quantity DESC";
        try {
            PreparedStatement pst = this.transaction.prepareStatement(query);
            pst.setDate(1, date1);
            pst.setDate(2, date2);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Patient tmp = new Patient(rs);
                    tmp.setQuantity(rs.getInt("quantity"));
                    patients.add(tmp);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return patients;
    }

    /**
     * Laboratorista -> Reporte de examenes a realizarse en su turno de día y a
     * realizados
     *
     * @param examId
     * @param date
     * @return
     */
    public List<Appointment> getAppointmentLab(int examId, java.sql.Date date) {
        AppointmentDao aDao = new AppointmentDao(this.transaction);
        return aDao.getAppointmentLab(examId, date);
    }

    /**
     * Laboratorista -> Reporte de las 10 fechas con más trabajo realizado
     * @param labWorkerId
     * @return 
     */
    public List<Result> get10DateMoreWork(String labWorkerId) {
        List<Result> results = new ArrayList<>();
        String query = "SELECT r.date, r.exam_id, e.name, r.lab_worker_id, COUNT(*) AS quantity FROM RESULTS r INNER JOIN EXAMS e ON r.exam_id = e.exam_id "
                + " WHERE r.lab_worker_id = ? GROUP BY r.date ORDER BY quantity DESC LIMIT 10";
        try {
            PreparedStatement pst = this.transaction.prepareStatement(query);
            pst.setString(1, labWorkerId);
            try (ResultSet rs = pst.executeQuery()) {
                while(rs.next()) {
                    java.sql.Date date = rs.getDate("date");
                    int examId = rs.getInt("exam_id");
                    String examName = rs.getString("name");
                    String labId = rs.getString("lab_worker_id");
                    int quantity = rs.getInt("quantity");
                    
                    results.add(new Result(date, labId, examId, examName, quantity));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return results;
    }
    
    /**
     * Paciente -> Ultimos 5 examenes de laboratorio realizados
     *
     * @param patientId
     * @return
     */
    public List<Result> getResultsByPatient(int patientId) {
        List<Result> results = new ArrayList<>();
        //String query = "SELECT * FROM RESULTS WHERE patient_id = ? ORDER BY date LIMIT 5";
        String query = "SELECT r.*, p.name AS patient, l.name AS labWorker, e.name AS exam FROM RESULTS r INNER JOIN PATIENTS p ON r.patient_id = p.patient_id INNER JOIN LAB_WORKERS l ON r.lab_worker_id = l.lab_worker_id INNER JOIN EXAMS e ON r.exam_id = e.exam_id "
                + "WHERE r.patient_id = ? ORDER BY date LIMIT 5";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setInt(1, patientId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    results.add(new Result(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return results;
    }

    /**
     * Paciente -> Examenes realizados de un tipo dentro de un intervalo de
     * tiempo
     *
     * @param patientId
     * @param examId
     * @param date1
     * @param date2
     * @return
     */
    public List<Result> getResultsByPatientAndDate(int patientId, int examId, java.sql.Date date1, java.sql.Date date2) {
        List<Result> results = new ArrayList<>();
        //String query = "SELECT * FROM RESULTS WHERE patient_id = ? AND exam_id = ? AND date BETWEEN ? AND ? ORDER BY date LIMIT 5";
        String query = "SELECT r.*, p.name AS patient, l.name AS labWorker, e.name AS exam FROM RESULTS r INNER JOIN PATIENTS p ON r.patient_id = p.patient_id INNER JOIN LAB_WORKERS l ON r.lab_worker_id = l.lab_worker_id INNER JOIN EXAMS e ON r.exam_id = e.exam_id "
                + "WHERE r.patient_id = ? AND r.exam_id = ? AND r.date BETWEEN ? AND ? ORDER BY date";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setInt(1, patientId);
            pst.setInt(2, examId);
            pst.setDate(3, date1);
            pst.setDate(4, date2);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    results.add(new Result(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return results;
    }

    /**
     * Paciente -> Ultimas 5 consultas realizadas
     *
     * @param patientId
     * @return
     */
    public List<Report> getLast5ReportsByPatient(int patientId) {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT r.*, p.name AS patient, d.name AS doctor, s.degree AS kind FROM REPORTS r INNER JOIN PATIENTS p ON r.patient_id = p.patient_id INNER JOIN DOCTORS d ON r.doctor_id = d.doctor_id INNER JOIN APPOINTMENTS a ON r.appointment_id = a.appointment_id INNER JOIN SPECIALTIES s ON s.specialty_id = a.specialty_id "
                + "WHERE r.patient_id = ? ORDER BY date, time LIMIT 5";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setInt(1, patientId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    reports.add(new Report(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return reports;
    }

    /**
     * Paciente -> Consultas realizadas con un medico especifico en un intervalo
     * de tiempo
     *
     * @param patientId
     * @param doctorId
     * @param date1
     * @param date2
     * @return
     */
    public List<Report> getReportsByDoctorAndTime(int patientId, String doctorId, java.sql.Date date1, java.sql.Date date2) {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT r.*, p.name AS patient, d.name AS doctor, s.degree AS kind FROM REPORTS r INNER JOIN PATIENTS p ON r.patient_id = p.patient_id INNER JOIN DOCTORS d ON r.doctor_id = d.doctor_id INNER JOIN APPOINTMENTS a ON r.appointment_id = a.appointment_id INNER JOIN SPECIALTIES s ON s.specialty_id = a.specialty_id "
                + "WHERE r.patient_id = ? AND r.doctor_id = ? AND r.date BETWEEN ? AND ? ORDER BY date, time";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setInt(1, patientId);
            pst.setString(2, doctorId);
            pst.setDate(3, date1);
            pst.setDate(4, date2);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    reports.add(new Report(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return reports;
    }

    /**
     * Administrador -> Los 10 medicos que mas informas han realizado en un
     * intervalo de tiempo
     *
     * @param date1
     * @param date2
     * @return
     */
    public List<Report> getTop10DoctorsReports(java.sql.Date date1, java.sql.Date date2) {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT r.doctor_id, d.name AS doctor, COUNT(*) AS quantity, GROUP_CONCAT(DISTINCT r.date) AS dates FROM REPORTS r INNER JOIN DOCTORS d ON r.doctor_id = d.doctor_id "
                + "WHERE r.date BETWEEN ? AND ? GROUP BY r.doctor_id ORDER BY quantity DESC LIMIT 10";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setDate(1, date1);
            pst.setDate(2, date2);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    reports.add(new Report(rs.getString("doctor_id"), rs.getString("doctor"), rs.getInt("quantity"), rs.getString("dates")));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return reports;
    }

    /**
     * Administrador -> Ingresos obtenidos por medico en un intervalo de tiempo
     *
     * @param date1
     * @param date2
     * @return
     */
    public List<Report> getIncomesByDate(java.sql.Date date1, java.sql.Date date2) {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT r.doctor_id AS doctor_id, d.name AS doctor_name, SUM(i.income) AS total FROM INCOMES i INNER JOIN REPORTS r ON i.report_id = r.report_id INNER JOIN DOCTORS d ON d.doctor_id = r.doctor_id "
                + "WHERE r.date BETWEEN ? AND ? GROUP BY doctor_id ORDER BY total DESC";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setDate(1, date1);
            pst.setDate(2, date2);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    reports.add(new Report(rs.getString("doctor_id"), rs.getString("doctor_name"), rs.getDouble("total")));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return reports;
    }

    /**
     * Administrador -> Los 5 medicos con menos cantidad de citas dentro de un
     * intervalo de tiempo
     *
     * @param date1
     * @param date2
     * @return
     */
    public List<Doctor> getDoctorsFewerApp(java.sql.Date date1, java.sql.Date date2) {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT a.doctor_id, d.name, COUNT(*) AS quantity, d.collegiate, d.start_time, d.end_time FROM APPOINTMENTS a INNER JOIN DOCTORS d ON a.doctor_id = d.doctor_id "
                + "WHERE a.date BETWEEN = AND ? GROUP BY a.doctor_id ORDER BY quantity ASC, doctor_id LIMIT 5";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setDate(1, date1);
            pst.setDate(2, date2);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String doctorId = rs.getString("doctor_id");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    String collegiate = rs.getString("collegiate");
                    java.sql.Time timeStart = rs.getTime("start_time");
                    java.sql.Time timeEnd = rs.getTime("end_time");

                    Doctor tmp = new Doctor(doctorId, collegiate, timeStart, timeEnd, quantity);
                    tmp.setName(name);
                    doctors.add(tmp);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return doctors;
    }

    /**
     * Administrador -> Ingresos generados por paciente en un intervalo de
     * tiempo
     *
     * @param date1
     * @param date2
     * @return
     */
    List<Patient> getPatientByIncome(java.sql.Date date1, java.sql.Date date2) {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT r.patient_id, p.name, p.gender, p.weight, p.blood, SUM(i.income) AS total FROM INCOMES i INNER JOIN REPORTS r ON i.report_id = r.report_id INNER JOIN PATIENTS p ON r.patient_id = p.patient_id "
                + "WHERE r.date BETWEEN ? AND ? GROUP BY r.patient_id ORDER BY total DESC, patient_id";
        try (PreparedStatement pst = this.transaction.prepareStatement(query)) {
            pst.setDate(1, date1);
            pst.setDate(2, date2);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int patientId = rs.getInt("patient_id");
                    String name = rs.getString("name");
                    boolean gender = rs.getBoolean("gender");
                    double weight = rs.getDouble("weight");
                    String blood = rs.getString("blood");
                    Double total = rs.getDouble("total");

                    Patient tmp = new Patient(patientId, gender, weight, blood, total);
                    tmp.setName(name);

                    patients.add(tmp);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return patients;
    }
}
