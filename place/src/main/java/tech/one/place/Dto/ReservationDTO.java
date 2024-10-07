package tech.one.place.Dto;

import java.util.Date;

public record ReservationDTO (Long userId, Long seatId, Date reservationDate){}
