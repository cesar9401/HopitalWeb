
package com.hospital.model;

/**
 *
 * @author cesar31
 */
public enum Day {
    Lunes("Lunes", 1), Martes("Martes", 2), Miercoles("Miercoles", 3), Jueves("Jueves", 4),
    Viernes("Viernes", 5), Sabado("Sabado", 6), Domingo("Domingo", 7);
        
    private String day;
    private int dayId;
    
    private Day(String day, int dayId){
        this.day = day;
        this.dayId = dayId;
    }

    public String getDay() {
        return day;
    }

    public int getDayId() {
        return dayId;
    }
}
