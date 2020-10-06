package com.hospital.controller;

import com.hospital.dao.*;
import com.hospital.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "LabController", urlPatterns = {"/LabController"})
@MultipartConfig(maxFileSize = 16177215)
public class LabController extends HttpServlet {

    private final long CERO_H = 21600000;
    private final long UNO_H = 3600000;
    private final MainController main = new MainController();
    private final DoctorController doctorController = new DoctorController();
    private final Connection conexion = main.getConexion();
    private final LabWorkerDao labDao = new LabWorkerDao(conexion);
    private final ExamDao examDao = new ExamDao(conexion);
    private final DoctorDao doctorDao = new DoctorDao(conexion);
    private final AppointmentDao appDao = new AppointmentDao(conexion);

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
            case "appointmentLab":
                //Formulario para escoger doctor y examen a realizarse en laboratorio
                appointmentLab(request, response);
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
     * Metodo para que el paciente mostrar pagina para que el paciente escoja el
     * examen a realizar y el doctor que ordeno el examen
     *
     * @param request
     * @param response
     */
    private void appointmentLab(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Doctor> doctors = doctorDao.getDoctors();
        List<Exam> exams = examDao.getExams(false);
        request.getSession().setAttribute("doctors", doctors);
        request.getSession().setAttribute("exams", exams);
        request.getRequestDispatcher("appointmentLab.jsp").forward(request, response);
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
                addLabWorker(request, response);
                break;
            case "editLabWorker":
                //Editar laboratorista
                editLabWorker(request, response);
                break;
            case "getInfoApp":
                //Obtener informacion sobre citas en laboratorio
                getInfoAppointment(request, response);
                break;
            case "setAppLab":
                //Agendar nueva cita en laboratorio
                setAppointmentLab(request, response);
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
     * Metodo para agregar un laboratorista nuevo al sistema y agregarlo a la bd
     *
     * @param request
     * @param response
     */
    private void addLabWorker(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {
        LabWorker labWorker = new LabWorker(request);
        labDao.createLabWoker(labWorker);
        labDao.insertLabWorkersDays(labWorker);
        request.setAttribute("newLab", labWorker.getName());
        doctorController.setLabWorkersAdmin(request, response);
    }

    /**
     * Metdo para la edicio de un laboratorista y agregarlo la la bd
     *
     * @param request
     * @param response
     */
    private void editLabWorker(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {
        LabWorker labEdit = new LabWorker(request);
        LabWorker labDB = labDao.getLabWorkerById(labEdit.getLabWorkerId());
        List<Day> dayN = labEdit.getDays();
        List<Day> dayDB = labDB.getDays();

        for (int i = 0; i < dayN.size(); i++) {
            for (int j = 0; j < dayDB.size(); j++) {
                if (dayN.get(i).getDayId() == dayDB.get(j).getDayId()) {
                    dayN.remove(i);
                    dayDB.remove(j);
                    i--;
                    break;
                }
            }
        }

        System.out.println("Nuevos");
        for (Day d : dayN) {
            System.out.println(d.getDayId() + " " + d.getDay());
        }

        System.out.println("Eliminar");
        for (Day d : dayDB) {
            System.out.println(d.getDayId() + " " + d.getDay());
        }

        labDao.updateLabWorker(labEdit, dayN, dayDB);
        request.setAttribute("updateLab", labEdit.getName());
        doctorController.setLabWorkersAdmin(request, response);
    }

    private void getInfoAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int examId = Integer.parseInt(request.getParameter("exam"));
        Exam exam = examDao.getExamById(examId);
        String doctorId = request.getParameter("doctor");
        java.sql.Date date = ReadXml.getDate(request.getParameter("date"));

        List<Appointment> appLab = getAppointmentLab(examId, date);
        //Atributos para el fomulario
        request.setAttribute("appointments", appLab);
        request.setAttribute("exam", exam);
        request.setAttribute("doctorId", doctorId);
        request.setAttribute("date", date);
        request.getRequestDispatcher("appointmentLab.jsp").forward(request, response);
    }

    private List<Appointment> getAppointmentLab(int examId, java.sql.Date date) {
        List<Appointment> appLab = new ArrayList<>();
        List<Appointment> app = appDao.getAppointmentLab(examId, date);

        java.sql.Time time = new java.sql.Time(CERO_H);
        System.out.println("time: " + time);
        for (int i = 0; i < 24; i++) {
            boolean isApp = false;
            for (Appointment a : app) {
                if (time.equals(a.getTime())) {
                    appLab.add(a);
                    isApp = true;
                    break;
                }
            }
            if (!isApp) {
                java.sql.Time tmp = new Time(time.getTime());
                appLab.add(new Appointment(tmp, true));
            }
            time.setTime(time.getTime() + UNO_H);
        }

        for (Appointment a : appLab) {
            System.out.println(a.toString());
        }

        return appLab;
    }

    /**
     * Agendar nueva cita en laboratorio
     *
     * @param request
     * @param response
     */
    private void setAppointmentLab(HttpServletRequest request, HttpServletResponse response) {
        Appointment appLab = new Appointment(request, true);
        System.out.println(appLab.toString());
        System.out.println("ExamId: " + appLab.getExamId());
        appDao.createAppointmentLab(appLab);
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
