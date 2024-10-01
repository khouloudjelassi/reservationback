package tech.one.place.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Entity
@Data

public class Seat implements Serializable{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String reference;

    @ManyToOne
    @JoinColumn(name = "idRoom", referencedColumnName = "id")
    @JsonIgnore

    private Room room;

}
