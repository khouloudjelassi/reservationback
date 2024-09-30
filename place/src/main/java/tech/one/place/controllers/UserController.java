package tech.one.place.controllers;
import tech.one.place.model.User;
import tech.one.place.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200/")

public class UserController {
    @Autowired
    private  UserService userService;
    @PostMapping
    public ResponseEntity<Object> createUserDetails(@RequestBody @Valid User user){
            User registeredUser = userService.register(user);
            return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/{UserID}")
    public ResponseEntity<tech.one.place.model.User> getUserDetails(@PathVariable("UserID") int userID) {
        tech.one.place.model.User user = userService.getUserInfo(userID);
            System.out.println(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @DeleteMapping ("/{userID}")
    public ResponseEntity<String> deleteUserDetails(@PathVariable("userID") int userID){
            this.userService.DeleteUserInfo(userID);
            return new ResponseEntity<>("User Deleted Successfully", HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUserDetails() {
            List<User> users = userService.getALLUserInfo();
            return ResponseEntity.ok(users);
    }
    @PutMapping("/{userID}")
    public ResponseEntity<Object> updateUser(@PathVariable("userID") int userID, @RequestBody @Valid User user){
        // Update the user
        User updatedUser = userService.updateUserInfo(user, userID);
            return ResponseEntity.ok(updatedUser);
}}

