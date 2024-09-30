package tech.one.place.services;
import lombok.AllArgsConstructor;
import tech.one.place.model.Reservation;
import tech.one.place.model.Room;
import tech.one.place.model.Seat;
import tech.one.place.model.User;
import tech.one.place.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.one.place.repositories.RoomRepository;
import tech.one.place.repositories.SeatRepository;
import tech.one.place.repositories.UserRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepo;
    private final RoomRepository roomRepo;
    private final UserRepository userRepo;
    private final SeatRepository seatRepo;


    public Reservation crateReservation(Reservation reservation){

        Seat seat = seatRepo.findById(reservation.getSeat().getId()).get();

        if(seat == null) {
            throw new IllegalArgumentException("Seat not found");
        }

        User user = userRepo.findById(reservation.getUser().getId()).get();

        if(user == null) {
            throw new IllegalArgumentException("User not found");
        }


        if(!reservationRepo.findAllByUserAndDate(user, reservation.getDate()).isEmpty()) {
            throw new IllegalArgumentException("Reservation already exists");
        }


        reservation.setDate(reservation.getDate());
        reservation.setSeat(seat);
        reservation.setUser(user);


        Reservation registeredReservation = reservationRepo.save(reservation);

        return registeredReservation;
    }

    //find by date
    public List<Reservation> findAllByDate(String date){
        return reservationRepo.findAllByQuerydate(date);
    }




    public Reservation register(Reservation res){
        try {
            User user = userRepo.findById(res.getUser().getId()).get();
            Seat seat = seatRepo.findById(res.getSeat().getId()).get();



            Reservation reservation = new Reservation();
            reservation.setDate(res.getDate());
            reservation.setSeat(res.getSeat());
            reservation.setUser(res.getUser());
            Reservation registeredReservation = reservationRepo.save(reservation);

            return registeredReservation;
        }catch (Exception e){
            throw new IllegalArgumentException("Reservation already exists");
        }
    }
    public Reservation updateReservation(long reservationId,Reservation res){
        Reservation reservation = reservationRepo.findById(reservationId).get();
        reservation.setDate(res.getDate());
        reservation.setSeat(res.getSeat());
        reservation.setUser(res.getUser());
        return reservationRepo.save(reservation);
    }
    public Reservation getReservation(long Id){
        return reservationRepo.findById(Id).get();
    }
    public List<Reservation> getAllReservations(){
        return  reservationRepo.findAll();

    }
    public Reservation deleteReservation(long Id){
        Reservation res = reservationRepo.findById(Id).get();
        reservationRepo.deleteById(Id);
        return res;
    }
}
