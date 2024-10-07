package tech.one.place.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.one.place.model.Reservation;
import tech.one.place.model.Seat;
import tech.one.place.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository  extends JpaRepository<Reservation, Long>{

    //find by user and date
    List<Reservation> findAllByUserAndDate(User user, Date date);

    @Query("SELECT r FROM Reservation r WHERE DATE(r.date) = DATE(:date)")
    List<Reservation> findAllByQuerydate(String date);

    Optional<Reservation> findReservationBySeat(Seat seat);
    Optional<Reservation> findReservationByUserAndSeat(User user, Seat seat);
    Optional<Reservation> findReservationByUser(User user);
    Optional<Reservation> findReservationBySeatAndUserAndDate(Seat seat, User user, Date date);
    Optional<Reservation> findReservationByUserAndDate(User user, Date date);
    Optional<Reservation> findReservationBySeatAndDate(Seat seat,Date date);
}
