package tech.one.place.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatDTO {
    private long id;
    private String name;
    private String reference;
//    public SeatDTO(long id, String name, String reference) {
//        this.id = id;
//        this.name = name;
//        this.reference = reference;
//    }

    // Getters and Setters
}
