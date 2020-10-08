package com.hospital.controller;

import com.hospital.dao.*;
import com.hospital.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cesar31
 */
@WebServlet(name = "OperationsLab", urlPatterns = {"/OperationsLab"})
@MultipartConfig(maxFileSize = 16177215)
public class OperationsLab extends HttpServlet {

    private final MainController main = new MainController();
    private final Connection conexion = main.getConexion();
    private final AppointmentDao appointmentDao = new AppointmentDao(conexion);
    private final PatientDao patientDao = new PatientDao(conexion);
    private final ResultDao resultDao = new ResultDao(conexion);
    private final LabWorkerDao labDao = new LabWorkerDao(conexion);
    private final ExamDao examDao = new ExamDao(conexion);

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
            out.println("<title>Servlet OperationsLab</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OperationsLab at " + request.getContextPath() + "</h1>");
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
            case "file":
                int appId = Integer.parseInt(request.getParameter("value"));
                appointmentDao.getOrder(appId, response);
                break;

            case "result":
                int appId1 = Integer.parseInt(request.getParameter("value"));
                Appointment app = appointmentDao.getAppointmentById(appId1, true);
                setNewResult(request, response, app);
                break;
                
            case "getReport":
                int resulId = Integer.parseInt(request.getParameter("value"));
                int examId = Integer.parseInt(request.getParameter("exam"));
                Exam exam = examDao.getExamById(examId);
                resultDao.getReport(response, resulId, exam.isReport());
                
                
                break;
        }
    }

    /**
     * Formulario para crear nuevo resultado para el paciente
     *
     * @param request
     * @param response
     * @param app
     */
    private void setNewResult(HttpServletRequest request, HttpServletResponse response, Appointment app) throws ServletException, IOException {
        Exam exam = examDao.getExamById(app.getExamId());
        Patient patient = patientDao.getPatientById(app.getPatientId());
        request.setAttribute("actualApp", app);
        request.setAttribute("thisExam", exam);
        request.setAttribute("patient", patient);
        request.getRequestDispatcher("resultPatient.jsp").forward(request, response);
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
            case "newResult":
                //Nuevo resultado para paciente
                newResultPatient(request, response);
                break;
        }
    }

    /**
     * Guardar nuevo resultado para paciente en la base de datos y redirigir al
     * perfil del laboratorista
     *
     * @param request
     * @param response
     */
    private void newResultPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result result = new Result(request);
        resultDao.insertNewResult(result);
        request.setAttribute("successResult", result.getPatientId());
        LabWorker labWorker = labDao.getLabWorkerById(result.getLabWorkerId());
        main.setProfileLabWorker(request, response, labWorker);
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
