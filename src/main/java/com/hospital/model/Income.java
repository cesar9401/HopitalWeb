
package com.hospital.model;

/**
 *
 * @author cesar31
 */
public class Income {
    private int incomeId;
    private int reportId;
    private int resultId;
    private Double income;

    public Income(int id, Double income, boolean report) {
        if(report) {
            this.reportId = id;
        }else {
            this.resultId = id;
        }
        this.income = income;
    }
    
    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "Income{" + "incomeId=" + incomeId + ", reportId=" + reportId + ", resultId=" + resultId + ", income=" + income + '}';
    }
}
