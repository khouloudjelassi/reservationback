package tech.one.place.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.one.place.Dto.SignupRequest;
import tech.one.place.model.User;
import tech.one.place.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return null;
        }
        User user = new User();

        BeanUtils.copyProperties(signupRequest, user);

        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(hashPassword);
        User createdUser = userRepository.save(user);
        user.setId(createdUser.getId());
        return user;

    }
}
