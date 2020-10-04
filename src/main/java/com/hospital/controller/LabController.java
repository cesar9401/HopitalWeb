package com.hospital.controller;

import com.hospital.dao.*;
import com.hospital.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
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
@WebServlet(name = "LabController", urlPatterns = {"/LabController"})
public class LabController extends HttpServlet {

    private final MainController main = new MainController();
    private final DoctorController doctorController = new DoctorController();
    private final Connection conexion = main.getConexion();
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
            out.println("<title>Servlet LabController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LabController at " + request.getContextPath() + "</h1>");
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
            case "newLabWorker":
                //Formulario para nuevo laboratorista
                newLabWorker(request, response);
                break;
            default:
                String labWorkerId = action;
                LabWorker labWorker = labDao.getLabWorkerById(labWorkerId);
                labWorker.setExam(examDao.getExamById(labWorker.getExamId()));
                request.setAttribute("labWorker", labWorker);
                newLabWorker(request, response);
                break;
        }
    }
    
    private void newLabWorker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Exam> exams = examDao.getExams(false);
        request.setAttribute("exams", exams);
        request.setAttribute("days", Day.values());
        request.getRequestDispatcher("labWorkerInformation.jsp").forward(request, response);
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
            case "searchLab":
                //Buscar laboratorista segun parametros
                searchLabWorkers(request, response);
                break;
            case "addLabWorker":
                //Agregar nuevo laboratorista
                System.out.println("Agregar LabWorker");
                break;
            case "editLabWorker":
                //Editar laboratorista
                System.out.println("Editar");
                break;
        }
        
    }

    /**
     * Metodo para buscar laboratoristas segun parametros
     *
     * @param request
     * @param response
     */
    private void searchLabWorkers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int option = Integer.parseInt(request.getParameter("kind"));
        List<LabWorker> labWorkers = new ArrayList<>();
        switch (option) {
            case 1:
                String name = request.getParameter("name");
                System.out.println("name = " + name);
                labWorkers = labDao.getLabWorkersByName(name);
                break;
            case 2:
                int examId = Integer.parseInt(request.getParameter("exams"));
                System.out.println("examId = " + examId);
                labWorkers = labDao.getLabWorkersByExam(examId);
                break;
            case 3:
                int dayId = Integer.parseInt(request.getParameter("days"));
                System.out.println("dayId = " + dayId);
                labWorkers = labDao.getLabWorkersByDays(dayId);
                break;
        }

        for (LabWorker l : labWorkers) {
            l.setExam(examDao.getExamById(l.getExamId()));
        }
        List<Exam> exams = examDao.getExams(false);
        request.setAttribute("days", Day.values());
        request.setAttribute("exams", exams);
        request.setAttribute("labWorkers", labWorkers);
        request.getRequestDispatcher("labWorkers.jsp").forward(request, response);
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
