
package com.hospital.model;

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
    
    
}
