package tech.one.place.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserManipulationToTestLDAPController {
    @GetMapping("/user-info")
    public String getUserInfo() {
        // Get the authenticated user's details from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = "";
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();  // Get the username
            System.out.println("** Authentication WITH ** UserDetails: " + authentication);
        } else {
            // for test only should be remved in the prod and use tests
            assert authentication != null;
            System.out.println("** Authentication without UserDetails: " + authentication);
            username = authentication.getPrincipal().toString();  // If not UserDetails, get the principal directly
        }

        return "Connected user: " + username;
    }
    @GetMapping("/current-user")
    public ResponseEntity<String> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userDetails.getUsername());
    }
}
