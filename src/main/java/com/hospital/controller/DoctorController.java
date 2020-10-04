package com.hospital.controller;

import com.hospital.dao.*;
import com.hospital.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
@WebServlet(name = "DoctorController", urlPatterns = {"/DoctorController"})
public class DoctorController extends HttpServlet {

    private final MainController main = new MainController();
    private final Connection conexion = main.getConexion();
    private final DoctorDao doctorDao = new DoctorDao(conexion);
    private final AppointmentDao appointmentDao = new AppointmentDao(conexion);
    private final PatientDao patientDao = new PatientDao(conexion);
    private final ReportDao reportDao = new ReportDao(conexion);
    private final SpecialtyDao specialtyDao = new SpecialtyDao(conexion);
    private final ExamDao examDao = new ExamDao(conexion);
    private final LabWorkerDao labDao = new LabWorkerDao(conexion);

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
            case "doctors":
                //Doctores para el admin para edicion y creacion
                setDoctorsAdmin(request, response);
                break;
            case "labWorkers":
                //Laboratoristas para el admin para edicion y creacion
                setLabWorkersAdmin(request, response);
                break;
            //Redirige hacia jsp para escribir informe del paciente
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
        List<Exam> exams = examDao.getExams(false);
        request.getSession().setAttribute("exams", exams);
        request.getRequestDispatcher("exams.jsp").forward(request, response);
    }

    /**
     * Metodo para llevar la informacion de los doctores a la vista del
     * administrador
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void setDoctorsAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Doctor> doctors = doctorDao.getDoctors();
        for (Doctor d : doctors) {
            d.setSpecialties(specialtyDao.getSpecialtiesByDoctor(d.getDoctorId()));
        }
        List<Specialty> specialties = specialtyDao.getSpecialties(false);

        request.getSession().setAttribute("specialty", specialties);
        request.setAttribute("doctors", doctors);
        request.getRequestDispatcher("doctors.jsp").forward(request, response);
    }

    /**
     * Metodo para llevar informacion de los laboratoristas a la vista del
     * aministrador
     *
     * @param request
     * @param response
     */
    public void setLabWorkersAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LabWorker> labWorkers = labDao.getLabWorkers();
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
            case "changeDateDoctor":
                //Cambiar fecha del doctor
                String fecha = request.getParameter("date");
                String doctorId = request.getParameter("doctorId");
                java.sql.Date date = ReadXml.getDate(fecha);

                request.getSession().setAttribute("date", date);
                Doctor doctor = doctorDao.getDoctor(doctorId);
                main.setProfileDoctor(request, response, doctor);
                break;
            case "newReport":
                //Crear informe para un paciente
                createReport(request, response);
                break;
            case "searchSpecialties":
                //Buscar especialidades para editar
                searchSpecialties(request, response);
                break;
            case "editSpecialty":
                //Editar especialidades
                editSpecialty(request, response);
                break;
            case "newSpecialty":
                newSpecialty(request, response);
                break;
            case "searchExams":
                //Buscar examenes para editar
                searchExams(request, response);
                break;
            case "editExam":
                //Editar examenes
                editExam(request, response);
                break;
            case "newExam":
                newExam(request, response);
                break;
            case "searchDoctors":
                searchDoctors(request, response);
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

    /**
     * Metodo que recibe la informacion de una especialidad que se va a
     * actualizar
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void editSpecialty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Specialty tmp = new Specialty(request);
        specialtyDao.updateSpecialty(tmp);
        request.setAttribute("update", tmp);
        setSpecialtiesAdmin(request, response);
    }

    /**
     * Metodo que recibe la informacion de una nueva especialidad que se va a
     * ingresar
     *
     * @param request
     * @param response
     */
    private void newSpecialty(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {
        Specialty tmp = new Specialty(request);
        specialtyDao.insertSpecialty(tmp);
        request.setAttribute("newSpecialty", tmp.getDegree());
        setSpecialtiesAdmin(request, response);
    }

    /**
     * Metodo para buscar examenes para poder editarlos
     *
     * @param request
     * @param response
     */
    private void searchExams(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int option = Integer.parseInt(request.getParameter("kind"));
        List<Exam> exams;
        if (option == 0) {
            String name = request.getParameter("search");
            exams = examDao.getExamsByName(name);
        } else {
            Double precio = Double.parseDouble(request.getParameter("search"));
            exams = examDao.getExamsByPrice(option, precio);
        }
        request.getSession().setAttribute("exams", exams);
        request.getRequestDispatcher("exams.jsp").forward(request, response);
    }

    /**
     * Metodo que recibe la informacion de un examne que se va a actualizar
     *
     * @param request
     * @param response
     */
    private void editExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Exam tmp = new Exam(request);
        examDao.updateExam(tmp);
        request.setAttribute("examUp", tmp.getName());
        setExamsAdmin(request, response);
    }

    /**
     * Metodo para crear un nuevo examen en el sistema
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void newExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Exam tmp = new Exam(request);
        examDao.createExam(tmp);
        request.setAttribute("newEx", tmp.getName());
        setExamsAdmin(request, response);
    }

    /**
     * Metodo para buscar doctores y enviarlos al admnistradores para poder
     * editar
     *
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     * @throws ServletException
     * @throws IOException
     */
    private void searchDoctors(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {
        int opt = Integer.parseInt(request.getParameter("kind"));
        List<Doctor> doctors = new ArrayList<>();
        switch (opt) {
            case 1:
                String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF8");
                System.out.println("name = " + name);
                doctors = doctorDao.getDoctorsByName(name);
                break;
            case 2:
                int specialtyId = Integer.parseInt(request.getParameter("specialty"));
                System.out.println("specialtyId = " + specialtyId);
                doctors = doctorDao.getDoctorsBySpeciality(specialtyId);
                break;
            case 3:
                java.sql.Time time1 = ReadXml.getTime(request.getParameter("time1"));
                System.out.println("time1 = " + time1);
                java.sql.Time time2 = ReadXml.getTime(request.getParameter("time2"));
                System.out.println("time2 = " + time2);
                doctors = doctorDao.getDoctorsByHours(time1, time2);
                break;
            case 4:
                java.sql.Date date1 = ReadXml.getDate(request.getParameter("date1"));
                System.out.println("date = " + date1);
                java.sql.Date date2 = ReadXml.getDate(request.getParameter("date2"));
                System.out.println("date2 = " + date2);
                doctors = doctorDao.getDoctorsByStartDate(date1, date2);
                break;
        }
        for (Doctor d : doctors) {
            d.setSpecialties(specialtyDao.getSpecialtiesByDoctor(d.getDoctorId()));
        }

        request.setAttribute("doctors", doctors);
        request.getRequestDispatcher("doctors.jsp").forward(request, response);
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
