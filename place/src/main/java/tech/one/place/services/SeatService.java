package tech.one.place.services;
import tech.one.place.model.Seat;
import tech.one.place.repositories.SeatRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SeatService {
    private final SeatRepository seatRepo;
    public SeatService(SeatRepository seatRepo) {
        this.seatRepo = seatRepo;
    }
    public String createSeatInfo(Seat seat){
        seatRepo.save(seat);
        return("seat created seccessfuly");
    }
    public Seat registerSeat(Seat registerSeat){
        if (seatRepo.existsById(registerSeat.getId())) {
            throw new IllegalArgumentException("Seat already exists");
        }
        Seat seat = new Seat();
        seat.setName(registerSeat.getName());
        seat.setReference(registerSeat.getReference());
        seat.setRoom(registerSeat.getRoom());
        Seat registeredSeat = seatRepo.save(seat);
        return registeredSeat;
    }
    public Seat getSeatInfo(long seatID){
        return seatRepo.findById(seatID).orElse(null);
    }
    public List<Seat> getALLSeatInfo(){return seatRepo.findAll();}
    public Seat DeleteSeatInfo(long seatId){
        Seat seat = seatRepo.findById(seatId).get();
        seatRepo.delete(seat);
        return seat;
    }
    public Seat updateSeat(long seatID, Seat seat){
        Seat existingSeat = seatRepo.findById(seatID).orElse(null);
        existingSeat.setName(seat.getName());
        existingSeat.setReference(seat.getReference());
        existingSeat.setRoom(seat.getRoom());
        return seatRepo.save(existingSeat);
    }


}
