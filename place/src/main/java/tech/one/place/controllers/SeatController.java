package tech.one.place.controllers;

import tech.one.place.model.Seat;
import tech.one.place.services.SeatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/seat")
@CrossOrigin(origins = "http://localhost:4200")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @PostMapping
    public ResponseEntity<Object> createSeatDetails(@RequestBody @Valid Seat seat){
        Seat registeredSeat = seatService.registerSeat(seat);
        return ResponseEntity.ok(registeredSeat);
    }

    @GetMapping("/{seatID}")
    public ResponseEntity<Seat> getSeatDetails(@PathVariable("seatID") int seatID) {
        Seat seat = seatService.getSeatInfo(seatID);
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Seat>> getAllSeatDetails() {
        List<Seat> seats = seatService.getALLSeatInfo();
        return ResponseEntity.ok(seats);
    }

    @DeleteMapping ("/{seatID}")
    public ResponseEntity<String> deleteSeatDetails(@PathVariable("seatID") int seatID){
        this.seatService.DeleteSeatInfo(seatID);
        return new ResponseEntity<>("seat Deleted Successfully", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{seatID}")
    public ResponseEntity<Object> updateSeat(@RequestBody @Valid Seat seat, @PathVariable("seatID") int seatID){
        seatService.updateSeat(seatID, seat);
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "Seat updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

