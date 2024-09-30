package tech.one.place.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Embeddable
public class ReservationId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "idSeat")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    private Date date;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationId that = (ReservationId) o;

        if (!seat.equals(that.seat)) return false;
        if (!user.equals(that.user)) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = seat.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
