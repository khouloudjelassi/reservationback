package tech.one.place.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.one.place.model.Reservation;
import tech.one.place.model.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservationRepository  extends JpaRepository<Reservation, Long>{

    //find by user and date
    List<Reservation> findAllByUserAndDate(User user, Date date);

    @Query("SELECT r FROM Reservation r WHERE DATE(r.date) = DATE(:date)")
    List<Reservation> findAllByQuerydate(String date);

}
