package tech.one.place.services;

import tech.one.place.Dto.SignupRequest;
import tech.one.place.model.User;

public interface AuthService {
        User createUser(SignupRequest signupRequest);
}
