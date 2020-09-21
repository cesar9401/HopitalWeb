package com.hospital.model;

import org.jdom2.Element;

/**
 *
 * @author cesar31
 */
public class Exam {

    private int examId;
    private String name;
    private boolean order;
    private String description;
    private Double price;
    private boolean report;

    public Exam(Element e) {
        this.examId = Integer.parseInt(e.getChildText("CODIGO"));
        this.name = e.getChildText("NOMBRE");
        this.order = e.getChildText("ORDEN").equals("TRUE");
        this.description = e.getChildText("DESCRIPCION");
        this.price = Double.parseDouble(e.getChildText("COSTO"));
        this.report = e.getChildText("INFORME").equals("PDF");
    }

    public Exam(int examId, String name, boolean order, Double price) {
        this.examId = examId;
        this.name = name;
        this.order = order;
        this.price = price;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isReport() {
        return report;
    }

    public void setReport(boolean report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "Exam{" + "examId=" + examId + ", name=" + name + ", order=" + order + ", description=" + description + ", price=" + price + ", report=" + report + '}';
    }
}
