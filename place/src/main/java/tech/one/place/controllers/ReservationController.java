package tech.one.place.controllers;
import tech.one.place.Dto.ReservationDTO;
import tech.one.place.model.Reservation;
import tech.one.place.repositories.ReservationRepository;
import tech.one.place.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "http://localhost:4200/")
public class ReservationController {
    @Autowired
    ReservationService resService;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Object> createReservation(@RequestBody @Valid Reservation res){
        Reservation res1 = resService.crateReservation(res);
        return ResponseEntity.ok(res1);
    }
    @PutMapping("/{reservationId}")
    public ResponseEntity<Object> updateReservation(@RequestBody @Valid Reservation res,@PathVariable("reservationId" )int Id){
        this.resService.updateReservation(Id,res);
        return ResponseEntity.ok(res);
    }
    @GetMapping("/{reservationId}")
    public ResponseEntity<Object> getReservationDetails(@PathVariable("reservationId") int Id){
        Reservation res = resService.getReservation(Id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(){
        List<Reservation> reservations = resService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }
    @DeleteMapping("/{resId}")
    public ResponseEntity<Object> deleteReservation(@PathVariable("resId") int Id){
        Reservation res = resService.deleteReservation(Id);
        return ResponseEntity.ok(res);
    }


    //find by date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Reservation>> getAllReservationsByDate(@PathVariable("date") String date){
        List<Reservation> reservations = resService.findAllByDate(date);
        return ResponseEntity.ok(reservations);
    }

    // to check date because we can have a lot of reservations with same seat and user but diff dates
    @PostMapping("/reserveunreserve")
    public ResponseEntity<Reservation> reserveUnreserve( @RequestBody ReservationDTO reservation) throws Exception {

        return ResponseEntity.ok(reservationService.reserveUnreserveSeat( reservation.userId(),reservation.seatId(), reservation.reservationDate()));
    }
@DeleteMapping("/deletereservation")
public ResponseEntity<String> deleteReservation( @RequestBody ReservationDTO reservation) throws Exception {
    return ResponseEntity.ok(reservationService.deleteReservation( reservation.userId(),reservation.seatId(), reservation.reservationDate()));

}
@GetMapping("/myreservations/{userId}")
    public ResponseEntity<?> getReservationsByUser(@PathVariable("userId") Long userId){
        return ResponseEntity.ok().body(reservationService.getAllReservationsByUser(userId));
}
}
