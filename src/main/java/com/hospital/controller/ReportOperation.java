package com.hospital.controller;

import com.hospital.dao.DoctorDao;
import com.hospital.dao.ExamDao;
import com.hospital.dao.HospitalReport;
import com.hospital.dao.LabWorkerDao;
import com.hospital.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cesar31
 */
@WebServlet(name = "ReportOperation", urlPatterns = {"/ReportOperation"})
public class ReportOperation extends HttpServlet {

    private final MainController main = new MainController();
    private final Connection conexion = main.getConexion();
    private final HospitalReport hospital = new HospitalReport(conexion);
    private final LabWorkerDao labDao = new LabWorkerDao(conexion);
    private final ExamDao examDao = new ExamDao(conexion);
    private final DoctorDao doctorDao = new DoctorDao(conexion);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReportOperation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReportOperation at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "doctor":
                request.getRequestDispatcher("doctorReports.jsp").forward(request, response);
                break;
            case "lab":
                LabWorker lab = (LabWorker) request.getSession().getAttribute("profile");
                Exam exam = examDao.getExamById(lab.getExamId());
                request.getSession().setAttribute("e", exam.getName());
                labReport4(request, response);
                request.getRequestDispatcher("labReports.jsp").forward(request, response);
                break;
            case "patient":
                List<Exam> exams = examDao.getExams(false);
                List<Doctor> doctors = doctorDao.getDoctors();
                request.getSession().setAttribute("examsPatient", exams);
                request.getSession().setAttribute("doctorsPatient", doctors);
                patientReport1(request, response);
                patientReport3(request, response);
                request.getRequestDispatcher("patientReports.jsp").forward(request, response);
                break;
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "doctor1":
                doctorReport1(request, response);
                break;

            case "doctor2":
                doctorReport2(request, response);
                break;

            case "doctor3":
                doctorReport3(request, response);
                break;

            case "lab1":
                labReport1(request, response);
                break;

            case "lab2":
                labReport2(request, response);
                break;

            case "lab3":
                break;

            case "patient2":
                patientReport2(request, response);
                break;

            case "patient4":
                patientReport4(request, response);
                break;

        }
    }

    /**
     * Reporte 1 doctor
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doctorReport1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.sql.Date date1 = ReadXml.getDate(request.getParameter("date1"));
        java.sql.Date date2 = ReadXml.getDate(request.getParameter("date2"));
        String doctorId = (String) request.getSession().getAttribute("user");
        List<Appointment> report1 = hospital.getAppointmentsByDoctor(doctorId, date1, date2);

        request.setAttribute("date1", date1);
        request.setAttribute("date2", date2);
        request.setAttribute("report1", report1);
        request.getRequestDispatcher("doctorReports.jsp").forward(request, response);
    }

    private void doctorReport2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.sql.Date date3 = ReadXml.getDate(request.getParameter("date3"));
        String doctorId = (String) request.getSession().getAttribute("user");
        List<Appointment> report2 = hospital.getAppointmentsByDoctorToday(doctorId, date3);
        request.setAttribute("date3", date3);
        request.setAttribute("report2", report2);
        request.getRequestDispatcher("doctorReports.jsp").forward(request, response);
    }

    private void doctorReport3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.sql.Date date4 = ReadXml.getDate(request.getParameter("date4"));
        java.sql.Date date5 = ReadXml.getDate(request.getParameter("date5"));
        List<Patient> report3 = hospital.patientsWithMostReports(date4, date5);
        request.setAttribute("date4", date4);
        request.setAttribute("date5", date5);
        request.setAttribute("report3", report3);
        request.getRequestDispatcher("doctorReports.jsp").forward(request, response);
    }

    private void labReport1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.sql.Date date1 = ReadXml.getDate(request.getParameter("date1"));
        String labId = (String) request.getSession().getAttribute("user");
        LabWorker lab = labDao.getLabWorkerById(labId);
        boolean work = main.timeToWork(date1, lab);
        List<Appointment> app = hospital.getAppointmentLab(lab.getExamId(), date1);

        request.setAttribute("date1", date1);
        request.setAttribute("work", work);
        request.setAttribute("lab1", app);
        request.getRequestDispatcher("labReports.jsp").forward(request, response);
    }

    private void labReport2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.sql.Date date2 = ReadXml.getDate(request.getParameter("date2"));
        String labId = (String) request.getSession().getAttribute("user");
        LabWorker lab = labDao.getLabWorkerById(labId);
        boolean work = main.timeToWork(date2, lab);
        List<Appointment> app = hospital.getAppointmentLab(lab.getExamId(), date2);

        request.setAttribute("date2", date2);
        request.setAttribute("work2", work);
        request.setAttribute("lab2", app);
        request.getRequestDispatcher("labReports.jsp").forward(request, response);
    }

    private void labReport4(HttpServletRequest request, HttpServletResponse response) {
        String labId = (String) request.getSession().getAttribute("user");
        List<Result> lab4 = hospital.get10DateMoreWork(labId);
        request.setAttribute("lab4", lab4);
    }

    private void patientReport1(HttpServletRequest request, HttpServletResponse response) {
        int patientId = (int) request.getSession().getAttribute("user");
        List<Result> patient1 = hospital.getResultsByPatient(patientId);
        request.getSession().setAttribute("patient1", patient1);
    }

    private void patientReport2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int examId = Integer.parseInt(request.getParameter("exam"));
        int patientId = (int) request.getSession().getAttribute("user");
        java.sql.Date date1 = ReadXml.getDate(request.getParameter("date1"));
        java.sql.Date date2 = ReadXml.getDate(request.getParameter("date2"));
        List<Result> patient2 = hospital.getResultsByPatientAndDate(patientId, examId, date1, date2);

        request.setAttribute("date1", date1);
        request.setAttribute("date2", date2);
        request.setAttribute("examId", examId);
        request.setAttribute("patient2", patient2);
        request.getRequestDispatcher("patientReports.jsp").forward(request, response);
    }

    private void patientReport3(HttpServletRequest request, HttpServletResponse response) {
        int patientId = (int) request.getSession().getAttribute("user");
        List<Report> patient3 = hospital.getLast5ReportsByPatient(patientId);
        request.getSession().setAttribute("patient3", patient3);
    }

    private void patientReport4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int patientId = (int) request.getSession().getAttribute("user");
        String doctorId = request.getParameter("doctor");
        java.sql.Date date3 = ReadXml.getDate(request.getParameter("date3"));
        java.sql.Date date4 = ReadXml.getDate(request.getParameter("date4"));

        List<Report> patient4 = hospital.getReportsByDoctorAndTime(patientId, doctorId, date3, date4);
        request.setAttribute("date3", date3);
        request.setAttribute("date4", date4);
        request.setAttribute("doctorId", doctorId);
        request.setAttribute("patient4", patient4);
        request.getRequestDispatcher("patientReports.jsp").forward(request, response);
    }
}
