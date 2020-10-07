
package com.hospital.model;

/**
 *
 * @author cesar31
 */
public enum Day {
    Lunes("Lunes", 2), Martes("Martes", 3), Miercoles("Miercoles", 4), Jueves("Jueves", 5),
    Viernes("Viernes", 6), Sabado("Sabado", 7), Domingo("Domingo", 1);
        
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
