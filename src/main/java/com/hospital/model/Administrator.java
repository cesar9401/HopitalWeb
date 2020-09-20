
package com.hospital.model;

/**
 *
 * @author cesar31
 */
public class Administrator {
    private String adminId;
    private String dpi;
    private String name;
    private String pass;

    public Administrator() {
    }

    public Administrator(String adminId, String dpi, String name, String pass) {
        this.adminId = adminId;
        this.dpi = dpi;
        this.name = name;
        this.pass = pass;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Administrator{" + "adminId=" + adminId + ", dpi=" + dpi + ", name=" + name + ", pass=" + pass + '}';
    }
}
