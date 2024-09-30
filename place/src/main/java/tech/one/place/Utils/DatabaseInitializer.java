package tech.one.place.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import tech.one.place.model.Room;
import tech.one.place.model.Seat;
import tech.one.place.model.User;
import tech.one.place.repositories.RoomRepository;
import tech.one.place.repositories.SeatRepository;
import tech.one.place.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;
@Component
public class DatabaseInitializer implements CommandLineRunner  {
    private final UserRepository userRepo;
    private final RoomRepository roomRepo;
    private final SeatRepository seatRepo;

    public DatabaseInitializer(UserRepository userRepo , RoomRepository roomRepo, SeatRepository seatRepo){
        this.userRepo = userRepo;
        this.roomRepo = roomRepo;
        this.seatRepo = seatRepo;
    }
    @Override
    public void run(String... args) throws Exception {
        if(userRepo.count() == 0){
            this.userSetup();
        }

//        if(roomRepo.count() == 0){
//            this.roomSetup();
//        }

        if(seatRepo.count() > 0 && seatRepo.count() < 18){
            this.seatRepo.deleteAll();
        }

//        if(seatRepo.count() == 0){
//            this.seatSetup();
//        }



    }

    public void userSetup(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setEmail("admin@admin.com");
        user.setPassword(encoder.encode("password"));
        user.setType("admin");
        user.setVerified(true);
        userRepo.save(user);
    }

//    public void roomSetup(){
//        Room room = new Room();
//        room.setStatus("available");
//        room.setCapacity(18);
//        roomRepo.save(room);
//    }

//    public void seatSetup(){
//        Room room = roomRepo.findById(1L).get();
//        for(int i = 0; i < room.getCapacity(); i++){
//            Seat seat = new Seat();
//            seat.setRoom(room);
//            seat.setName("Seat " + i);
//            seat.setReference("R" + 1 + "S" + i);
//            seatRepo.save(seat);
//        }
//
//    }
}
