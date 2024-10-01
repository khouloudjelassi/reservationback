package tech.one.place.services;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import tech.one.place.model.Room;
import tech.one.place.model.Seat;
import tech.one.place.repositories.RoomRepository;
import org.springframework.stereotype.Service;
import tech.one.place.repositories.SeatRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoomService {
    private final RoomRepository roomRepo;
    private final SeatRepository seatRepo;

    public RoomService(RoomRepository roomRepo, SeatRepository seatRepo) {
        this.roomRepo = roomRepo;
        this.seatRepo = seatRepo;
    }

    public String createRoomInfo(Room room){
        roomRepo.save(room);
        return("room created seccessfuly");
    }
//
@Transactional
public Room registerRoom(Room registerRoom) {
    if (roomRepo.existsById(registerRoom.getId())) {
        throw new IllegalArgumentException("Room already exists");
    }

    Room room = new Room();
    room.setStatus(registerRoom.getStatus());
    room.setCapacity(registerRoom.getCapacity());
    room.setName(registerRoom.getName());
    room.setDepartment(registerRoom.getDepartment());

    Room registeredRoom = roomRepo.save(room);

    // Create and save seats
    createSeats(registeredRoom);

    return registeredRoom;
}

    private void createSeats(Room room) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= room.getCapacity(); i++) {
            Seat seat = new Seat();
            seat.setName("Seat" + i);
            seat.setReference("Seat_" + room.getDepartment() + i );
            seat.setRoom(room);
            seats.add(seat);
        }
        seatRepo.saveAll(seats); // Save all seats at once
    }
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    public Room getRoomInfo(long Id){
        return roomRepo.findById(Id).orElse(null);
    }

    public void deleteRoom(long id) {
        Room room = roomRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Room not found"));
        List<Seat> seats = seatRepo.findByRoomId(room.getId());
        seatRepo.deleteAll(seats);
        roomRepo.delete(room);
    }
    public Room updateRoom(long Id, Room room){
        Room exroom = roomRepo.findById(Id).get();
        exroom.setStatus(room.getStatus());
        exroom.setCapacity(room.getCapacity());
        return roomRepo.save(exroom);
    }
}
