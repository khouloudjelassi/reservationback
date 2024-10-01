package tech.one.place.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.one.place.model.Seat;

import java.util.List;


public interface SeatRepository extends JpaRepository<Seat, Long> {


    List<Seat> findByRoomId(long idRoom);
}
