package com.iesam.digitalLibrary.features.loan.domain;

import com.iesam.digitalLibrary.features.digitalResources.domain.DigitalResource;
import com.iesam.digitalLibrary.features.user.domain.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Loan {

    public final Integer id;
    public final User user;
    public final ArrayList<DigitalResource> digitalResources;
    public final Date startDate;
    public final Date endDate;
    public final boolean earlyReturn;

    public Loan(Integer id, User user, ArrayList<DigitalResource> digitalResources, Date startDate, Date endDate, boolean earlyReturn) {
        this.id = id;
        this.user = user;
        this.digitalResources = digitalResources;
        this.startDate = startDate;
        this.endDate = defaultEndDate(endDate);
        this.earlyReturn = earlyReturn;
    }

    // Método para verificar si el préstamo ha sido devuelto
    public boolean isReturned() {
        // Si se devolvió antes de tiempo, el préstamo ya no está en proceso.
        return !this.earlyReturn;
    }

    public Date defaultEndDate(Date endDate){
        if (endDate == null ){
            // La fecha de fin está predeterminado a 30 días de la fecha de inicio
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.startDate);
            calendar.add(Calendar.DATE, 30);
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
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", earlyReturn=" + earlyReturn +
                '}';
    }
}
