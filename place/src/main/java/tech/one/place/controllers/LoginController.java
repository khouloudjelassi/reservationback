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
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200/")

public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody @Valid User user){
            User registeredUser = userService.login(user);
            return ResponseEntity.ok(registeredUser);
    }
}