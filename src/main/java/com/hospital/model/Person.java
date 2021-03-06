package com.hospital.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.jdom2.Element;

/**
 *
 * @author cesar31
 */
public class Person implements Serializable{

    private String name;
    private String dpi;
    private String phone;
    private String email;
    private String pass;

    public Person() {
    }

    public Person(Element e) {
        this.name = e.getChildText("NOMBRE");
        this.dpi = e.getChildText("DPI");
        this.phone = e.getChildText("TELEFONO");
        this.email = e.getChildText("CORREO");
        this.pass = e.getChildText("PASSWORD");
    }

    public Person(ResultSet rs) throws SQLException {
        this.name = rs.getNString("name");
        this.dpi = rs.getString("dpi");
        this.phone = rs.getString("phone");
        this.email = rs.getString("email");
        this.pass = rs.getString("password");
    }
    
    public Person(HttpServletRequest request) throws UnsupportedEncodingException {
        this.name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
        this.dpi = request.getParameter("dpi");
        this.phone = request.getParameter("phone");
        this.email = new String(request.getParameter("email").getBytes("ISO-8859-1"), "UTF-8");
        this.pass = new String(request.getParameter("pass").getBytes("ISO-8859-1"), "UTF-8");
    }

    public Person(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", dpi=" + dpi + ", phone=" + phone + ", email=" + email + ", pass=" + pass + '}';
    }
}
