package tech.one.place.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
@Entity
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String type;
    private boolean verified;
    public String getEmail() {

        return email;
    }
}
