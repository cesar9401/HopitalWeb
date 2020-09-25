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
        switch (action) {
            case "singOff":
                HttpSession session = request.getSession();
                session.invalidate();
                //request.getRequestDispatcher("index.jsp").forward(request, response);
                response.sendRedirect("index.jsp");
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
            case "login":;
                initLogin(request, response);
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
                PatientDao pd = new PatientDao(conexion);
                Patient p = pd.getPatien(email, pass);
                if (p != null) {
                    ResultDao rDao = new ResultDao(conexion);
                    List<Result> results = rDao.getResultsByPatient(p.getPatientId());
                    ReportDao rD = new ReportDao(conexion);
                    List<Report> reports = rD.getReportsByPatient(p.getPatientId());
                    AppointmentDao aD = new AppointmentDao(conexion);
                    List<Appointment> app = aD.getAppointmentsByPatient(p.getPatientId(), false, false);
                    List<Appointment> appLab = aD.getAppointmentsByPatient(p.getPatientId(), false, true);
                    
                    request.getSession().setAttribute("user", p.getPatientId());
                    request.getSession().setAttribute("profile", p);
                    request.getSession().setAttribute("results", results);
                    request.getSession().setAttribute("reports", reports);
                    request.getSession().setAttribute("app", app);
                    request.getSession().setAttribute("appLab", appLab);
                    
                    request.getRequestDispatcher("patientView.jsp").forward(request, response);
                }
                break;
            case "DOCTORS":
                DoctorDao dd = new DoctorDao(conexion);
                Doctor d = dd.getDoctor(email, pass);
                if (d != null) {
                    System.out.println(d.toString());
                }
                break;
            case "LAB_WORKERS":
                LabWorkerDao ld = new LabWorkerDao(conexion);
                LabWorker l = ld.getLabWorker(email, pass);
                if (l != null) {
                    System.out.println(l.toString());
                }
                break;
            case "ADMINISTRATORS":
                AdministratorDao ad = new AdministratorDao(conexion);
                Administrator a = ad.getAdminById(email, pass);

                if (a != null) {
                    SpecialtyDao sDao = new SpecialtyDao(conexion);
                    ExamDao eDao = new ExamDao(conexion);
                    List<Specialty> specialties = sDao.getSpecialties();
                    List<Exam> exams = eDao.getExams();

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
