package com.iesam.digitalLibrary.features.loan.domain;

import com.iesam.digitalLibrary.features.digitalResources.domain.DigitalResource;
import com.iesam.digitalLibrary.features.user.domain.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Loan {

    private static final int defaultDays = 30;

    public final Integer id;
    public final User user;
    public final ArrayList<DigitalResource> digitalResources;
    public final Date startDate;
    public final Date endDate;
    public final Date returnDate;


    public Loan(Integer id, User user, ArrayList<DigitalResource> digitalResources, Date returnDate) {
        this.id = id;
        this.user = user;
        this.digitalResources = digitalResources;
        this.startDate = defaultStartDate() ;
        this.endDate = defaultEndDate();
        this.returnDate = returnDate;
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
