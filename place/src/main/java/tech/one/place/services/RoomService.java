package tech.one.place.services;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import tech.one.place.Dto.RoomDTO;
import tech.one.place.Dto.SeatDTO;
import tech.one.place.model.Room;
import tech.one.place.model.Seat;
import tech.one.place.repositories.RoomRepository;
import org.springframework.stereotype.Service;
import tech.one.place.repositories.SeatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        seatRepo.saveAll(seats);
    }
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

//    public Room getRoomInfo(long Id){
//        return roomRepo.findById(Id).orElse(null);
//    }

    public RoomDTO getRoomInfos(long id) {
        Room room = roomRepo.findById(id).orElse(null);
        if (room == null) return null;

        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setName(room.getName());
        dto.setSeats(room.getSeats().stream()
                .map(seat -> new SeatDTO(seat.getId(), seat.getName(), seat.getReference()))
                .collect(Collectors.toList()));
        return dto;
    }


    public void deleteRoom(long id) {
        Room room = roomRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Room not found"));
        List<Seat> seats = seatRepo.findByRoomId(room.getId());
        seatRepo.deleteAll(seats);
        roomRepo.delete(room);
    }
//    public Room updateRoom(long Id, Room room){
//        Room exroom = roomRepo.findById(Id).get();
//        exroom.setName(room.getName());
//        exroom.setStatus(room.getStatus());
//        exroom.setCapacity(room.getCapacity());
//        return roomRepo.save(exroom);
//    }

    public Room updateRoom(long id, Room room) {
        Room existingRoom = roomRepo.findById(id).orElse(null);

        existingRoom.setName(room.getName());
        existingRoom.setStatus(room.getStatus());
        existingRoom.setCapacity(room.getCapacity());

        // Update seats based on the new capacity
        adjustSeats(existingRoom, room.getCapacity());

        return roomRepo.save(existingRoom);
    }

    private void adjustSeats(Room room, int newCapacity) {
        List<Seat> currentSeats = room.getSeats();

        if (newCapacity < currentSeats.size()) {
            for (int i = currentSeats.size() - 1; i >= newCapacity; i--) {
                seatRepo.delete(currentSeats.get(i));
                currentSeats.remove(i);
            }
        }

        for (int i = currentSeats.size() + 1; i <= newCapacity; i++) {
            Seat seat = new Seat();
            seat.setName("Seat" + i);
            seat.setReference("Seat_" + room.getDepartment() + i);
            seat.setRoom(room);
            currentSeats.add(seat);
        }

        seatRepo.saveAll(currentSeats);
    }

    public List<Seat> getseatsByRoomId(long roomId) {
        Room room = roomRepo.findById(roomId).orElse(null);
        if (room == null) return null;
        return room.getSeats();
    }

    public List<Room> getRoomBydepartment(String department) {
        List<Room> rooms = roomRepo.getRoomByDepartment( department);
        if (rooms == null) return null;
        return rooms;
    }
}
