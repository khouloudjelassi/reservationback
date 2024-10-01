package tech.one.place.Dto;

import lombok.Data;

import java.util.List;
@Data
public class RoomDTO {
    private long id;
    private String name;
    private List<SeatDTO> seats; // Include only the necessary fields

    // Getters and Setters
}