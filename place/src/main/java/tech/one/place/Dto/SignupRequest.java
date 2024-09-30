package tech.one.place.Dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private final String type="user";
    private final boolean verified=false;



}
