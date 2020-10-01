package com.hospital.controller;

import com.hospital.dao.*;
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
@WebServlet(name = "DoctorController", urlPatterns = {"/DoctorController"})
public class DoctorController extends HttpServlet {

    private final MainController main = new MainController();
    private final Connection conexion = main.getConexion();
    private final DoctorDao doctorDao = new DoctorDao(conexion);
    private final AppointmentDao appointmentDao = new AppointmentDao(conexion);
    private final PatientDao patientDao = new PatientDao(conexion);
    private final ReportDao reportDao = new ReportDao(conexion);
    private final SpecialtyDao specialtyDao = new SpecialtyDao(conexion);

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
            out.println("<title>Servlet DoctorController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DoctorController at " + request.getContextPath() + "</h1>");
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
            //Redirigir al perfil del doctor
            case "myProfile":
                String doctorId = (String) request.getSession().getAttribute("user");
                Doctor doctor = doctorDao.getDoctor(doctorId);
                request.getSession().setAttribute("success", null);
                main.setProfileDoctor(request, response, doctor);
                break;
            //Especialidades para editar por admin
            case "specialties":
                setSpecialtiesAdmin(request, response);
                break;
            //Examenes para editar por admin
            case "exams":
                setExamsAdmin(request, response);
                break;
            default:
                setReportePatient(request, response);
                break;
        }
    }

    /**
     * Redirigir a la vista donde se escribira el informe de un paciente
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void setReportePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int appId = Integer.parseInt(request.getParameter("action"));
        Appointment app = appointmentDao.getAppointmentById(appId);
        Patient patient = patientDao.getPatientById(app.getPatientId());

        //Datos para jsp
        request.getSession().setAttribute("appointment", app);
        request.getSession().setAttribute("patient", patient);
        request.getRequestDispatcher("reportPatient.jsp").forward(request, response);
    }

    /**
     * Metodo para obtener las espcialidades del hospital y poder editarlas por
     * el administrador
     *
     * @param request
     * @param response
     */
    private void setSpecialtiesAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Specialty> specialties = specialtyDao.getSpecialties(false);
        request.getSession().setAttribute("specialties", specialties);
        request.getRequestDispatcher("specialties.jsp").forward(request, response);
    }

    /**
     * Metodo para obtener los examenes del hospital y poder editarlos por el
     * administrador
     *
     * @param request
     * @param response
     */
    private void setExamsAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("exams.jps").forward(request, response);
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
            //Cambiar fecha del doctor
            case "changeDateDoctor":
                String fecha = request.getParameter("date");
                String doctorId = request.getParameter("doctorId");
                java.sql.Date date = ReadXml.getDate(fecha);

                request.getSession().setAttribute("date", date);
                Doctor doctor = doctorDao.getDoctor(doctorId);
                main.setProfileDoctor(request, response, doctor);
                break;
            //Crear informe del doctor
            case "newReport":
                createReport(request, response);
                break;
            //Buscar especialidades para editar
            case "searchSpecialties":
                searchSpecialties(request, response);
                break;
            case "editSpecialty":
                editSpecialty(request, response);
                break;
        }
    }

    /**
     * Metodo para que recibe el informe de un paciente y lo envia a la base de
     * datos
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void createReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Report report = new Report(request);
        System.out.println(report.toString());
        reportDao.createReport(report);
        request.getSession().setAttribute("success", true);
        Doctor doctor = doctorDao.getDoctor(report.getDoctorId());
        main.setProfileDoctor(request, response, doctor);
    }

    /**
     * Metodo para buscar reportes y editarlos
     *
     * @param request
     * @param response
     */
    private void searchSpecialties(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int option = Integer.parseInt(request.getParameter("kind"));
        List<Specialty> specialties;
        if (option == 0) {
            String degree = request.getParameter("search");
            specialties = specialtyDao.getSpecialtiesByName(degree);
        } else {
            Double precio = Double.parseDouble(request.getParameter("search"));
            specialties = specialtyDao.getSpecialtiesByPrice(option, precio);
        }
        request.getSession().setAttribute("specialties", specialties);
        request.getRequestDispatcher("specialties.jsp").forward(request, response);
    }

    private void editSpecialty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Specialty tmp = new Specialty(request);
        specialtyDao.updateSpecialty(tmp);
        request.setAttribute("update", tmp);
        System.out.println(tmp.toString());
        setSpecialtiesAdmin(request, response);
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
