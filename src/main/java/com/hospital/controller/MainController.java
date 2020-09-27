package com.hospital.controller;

import com.hospital.conexion.Conexion;
import com.hospital.dao.*;
import com.hospital.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author cesar31
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
@MultipartConfig(maxFileSize = 16177215)
public class MainController extends HttpServlet {

    private final Connection conexion = Conexion.getConnection();
    private final ResultDao resultDao = new ResultDao(conexion);
    private final ReportDao reportDao = new ReportDao(conexion);
    private final AppointmentDao appointmentDao = new AppointmentDao(conexion);
    private final PatientDao patientDao = new PatientDao(conexion);
    private final SpecialtyDao specialtyDao = new SpecialtyDao(conexion);
    private final DoctorDao doctorDao = new DoctorDao(conexion);
    private final ExamDao examDao = new ExamDao(conexion);
    private final AdministratorDao administratorDao = new AdministratorDao(conexion);
    private final LabWorkerDao labWorkerDao = new LabWorkerDao(conexion);

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerAdmin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerAdmin at " + request.getContextPath() + "</h1>");
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
        System.out.println("action = " + action);
        switch (action) {
            case "singOff":
                HttpSession session = request.getSession();
                session.invalidate();
                //request.getRequestDispatcher("index.jsp").forward(request, response);
                response.sendRedirect("index.jsp");
                break;
            case "newAppointment":
                List<Doctor> doctors = doctorDao.getDoctors();
                request.getSession().setAttribute("doctors", doctors);
                request.getRequestDispatcher("appointment.jsp").forward(request, response);
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
            case "load":
                loadData(request, response);
                break;
            case "login":
                initLogin(request, response);
                break;
            case "d_specialties":
                int specialtyId = Integer.parseInt(request.getParameter("specialties"));
                List<Doctor> doctors = doctorDao.getDoctorsBySpeciality(specialtyId);
                request.getSession().setAttribute("doctors", doctors);
                request.getRequestDispatcher("appointment.jsp").forward(request, response);
                break;
        }
    }

    private void loadData(HttpServletRequest request, HttpServletResponse response) {
        try {
            Part filePart = request.getPart("file");
            ReadXml read = new ReadXml(filePart);
            read.laodData();

        } catch (IOException | ServletException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void initLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String value = request.getParameter("tipoUsuario");

        switch (value) {
            case "PATIENTS":
                Patient p = patientDao.getPatien(email, pass);
                if (p != null) {
                    List<Result> results = resultDao.getResultsByPatient(p.getPatientId());
                    List<Report> reports = reportDao.getReportsByPatient(p.getPatientId());
                    List<Appointment> app = appointmentDao.getAppointmentsByPatient(p.getPatientId(), false, false);
                    List<Appointment> appLab = appointmentDao.getAppointmentsByPatient(p.getPatientId(), false, true);
                    List<Specialty> specialties = specialtyDao.getSpecialties();

                    request.getSession().setAttribute("user", p.getPatientId());
                    request.getSession().setAttribute("profile", p);
                    request.getSession().setAttribute("results", results);
                    request.getSession().setAttribute("reports", reports);
                    request.getSession().setAttribute("app", app);
                    request.getSession().setAttribute("appLab", appLab);
                    request.getSession().setAttribute("specialties", specialties);

                    request.getRequestDispatcher("patientView.jsp").forward(request, response);
                }
                break;
            case "DOCTORS":
                Doctor d = doctorDao.getDoctor(email, pass);
                if (d != null) {
                    System.out.println(d.toString());
                }
                break;
            case "LAB_WORKERS":
                LabWorker l = labWorkerDao.getLabWorker(email, pass);
                if (l != null) {
                    System.out.println(l.toString());
                }
                break;
            case "ADMINISTRATORS":
                Administrator a = administratorDao.getAdminById(email, pass);

                if (a != null) {
                    List<Specialty> specialties = specialtyDao.getSpecialties();
                    List<Exam> exams = examDao.getExams();

                    request.getSession().setAttribute("user", a.getAdminId());
                    request.getSession().setAttribute("profile", a);
                    request.getSession().setAttribute("specialties", specialties);
                    request.getSession().setAttribute("exams", exams);
                    request.getRequestDispatcher("adminView.jsp").forward(request, response);
                }
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
