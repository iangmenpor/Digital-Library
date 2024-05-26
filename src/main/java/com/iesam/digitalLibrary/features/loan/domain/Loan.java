package com.iesam.digitalLibrary.features.loan.domain;

import com.iesam.digitalLibrary.features.digitalResources.domain.DigitalResource;
import com.iesam.digitalLibrary.features.user.domain.User;

import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Loan {

    private static final int defaultDays = 30;

    public final Integer id;
    public final User user;
    public final List<DigitalResource> digitalResources;
    public final Date startDate;
    public final Date endDate;
    public final Date returnDate;

    // Constructor con ID
    public Loan(Integer id, User user, List<DigitalResource> digitalResources, Date returnDate) {
        this.id = id;
        this.user = user;
        this.digitalResources = digitalResources;
        this.startDate = defaultStartDate() ;
        this.endDate = defaultEndDate();
        this.returnDate = returnDate;
    }
    // Constructor sin ID (el ID se genera por un método privado) y fecha de entrega null
    public Loan(User user, List<DigitalResource> digitalResources) {
        this.id = defaultId();
        this.user = user;
        this.digitalResources = digitalResources;
        this.startDate = defaultStartDate() ;
        this.endDate = defaultEndDate();
        this.returnDate = null;
    }

    // Lógica para generar un ID único
    private Integer defaultId() {
        Random random = new Random();
        // Obtener la marca de tiempo actual en milisegundos
        long timestamp = new Date().getTime();
        // Generar un ID aleatorio único entre 10 y 99 (inclusive)
        int randomId = random.nextInt(90) + 10;
        // Combinar la marca de tiempo y el ID aleatorio para formar un ID único
        int id = (int) (timestamp % 1000) * 1000 + randomId;
        // Asegurar que el ID sea positivo
        return Math.abs(id);
    }

    // Método para verificar si el préstamo ha sido devuelto
    public boolean isReturned() {
        return returnDate!=null;
    }

    private Date defaultStartDate(){
        return new Date();
    }
    private Date defaultEndDate(){
        if (endDate == null ){
            // La fecha de fin está predeterminado a 30 días de la fecha de inicio
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.startDate);
            calendar.add(Calendar.DATE, defaultDays);
            return calendar.getTime();
        }
        return endDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", user=" + user +
                ", digitalResources=" + digitalResources +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
