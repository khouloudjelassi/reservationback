package tech.one.place.model;

import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

@Entity
@Data

public class Reservation implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "idSeat")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;


    private Date date;

}
