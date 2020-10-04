/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {

    private final MainController main = new MainController();
    private final DoctorController doc = new DoctorController();
    private final Connection conexion = main.getConexion();
    private final DoctorDao doctorDao = new DoctorDao(conexion);
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
            out.println("<title>Servlet AdminController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminController at " + request.getContextPath() + "</h1>");
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
            case "newDoctor":
                newDoctor(request, response);
                break;
            default:
                String doctorId = action;
                System.out.println("doctorId = " + doctorId);
                Doctor doctor = doctorDao.getDoctor(doctorId);
                doctor.setSpecialties(specialtyDao.getSpecialtiesByDoctor(doctor.getDoctorId()));
                request.setAttribute("doctor", doctor);
                List<Specialty> specialties = specialtyDao.getSpecialties(false);
                request.setAttribute("specialties", specialties);
                request.getRequestDispatcher("doctorInformation.jsp").forward(request, response);
                break;
        }
    }

    private void newDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Specialty> specialties = specialtyDao.getSpecialties(false);
        request.getSession().setAttribute("specialties", specialties);
        request.getRequestDispatcher("doctorInformation.jsp").forward(request, response);
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
            case "editDoctor":
                //Editar doctor
                editDoctor(request, response);
                break;
            case "addDoctor":
                //Agregar doctor
                addDoctor(request, response);
                break;
            default:
                break;
        }
    }

    private void editDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Doctor doctor = new Doctor(request);
            System.out.println(doctor.toString());
            //Especialidades
            List<Specialty> specialtiesN = new ArrayList<>();
            List<Specialty> specialtiesDB = specialtyDao.getSpecialtiesByDoctor(doctor.getDoctorId());
            String[] valores = request.getParameterValues("docSpecialties");
            try {
                if (valores != null) {
                    for (String v : valores) {
                        specialtiesN.add(new Specialty(Integer.parseInt(v), doctor.getDoctorId()));
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace(System.out);
            }

            for (int i = 0; i < specialtiesN.size(); i++) {
                for (int j = 0; j < specialtiesDB.size(); j++) {
                    if (specialtiesN.get(i).getSpecialtyId() == specialtiesDB.get(j).getSpecialtyId()) {
                        specialtiesN.remove(i);
                        specialtiesDB.remove(j);
                        i--;
                        break;
                    }
                }
            }

            doctorDao.updateDoctor(doctor, specialtiesN, specialtiesDB);
            request.setAttribute("updateDoctor", doctor.getName());
            doc.setDoctorsAdmin(request, response);

        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Metodo para agregar un nuevo doctor a la base de datos
     *
     * @param request
     * @param response
     */
    private void addDoctor(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {
        Doctor doctor = new Doctor(request);
        System.out.println(doctor.toString());
        String[] valores = request.getParameterValues("docSpecialties");
        List<Specialty> specialties = new ArrayList<>();
        try {
            if (valores != null) {
                for (String v : valores) {
                    specialties.add(new Specialty(Integer.parseInt(v), doctor.getDoctorId()));
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace(System.out);
        }

        doctor.setSpecialties(specialties);
        //Agregar a la base de datos
        doctorDao.insertDoctor(doctor);
        for (Specialty s : specialties) {
            specialtyDao.insertMedicalDegreeById(s);
        }
        request.setAttribute("nuevoDoc", doctor.getName());
        doc.setDoctorsAdmin(request, response);
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
