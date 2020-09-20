
package com.hospital.model;

/**
 *
 * @author cesar31
 */
public enum Day {
    LUNES("Lunes", 1), MARTES("Martes", 2), MIERCOLES("Miércoles", 3), JUEVES("Jueves", 4),
    VIERNES("Viernes", 5), SABADO("Sabado", 6), DOMINGO("Domingo", 7);
        
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
