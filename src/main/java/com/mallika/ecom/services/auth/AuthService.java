package com.mallika.ecom.services.auth;

import com.mallika.ecom.dto.SignupRequest;
import com.mallika.ecom.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);

    Boolean hasUserWithEmail(String email);
}
