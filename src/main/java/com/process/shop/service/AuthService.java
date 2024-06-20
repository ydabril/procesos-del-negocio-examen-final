package com.process.shop.service;

import com.process.shop.exceptions.AuthenticationFailedException;
import com.process.shop.model.User;
import com.process.shop.model.dto.AuthRequest;
import com.process.shop.model.dto.AuthResponse;
import com.process.shop.model.enums.ErrorMessages;
import com.process.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(AuthRequest authRequest){

        Optional<User> user = userRepository.findByEmail(authRequest.getEmail());
        if(user.isEmpty()){
            throw new AuthenticationFailedException(ErrorMessages.CREDENTIAL_INVALID.getMessage());
        }
        user = userRepository.findByPassword(authRequest.getPassword());
        if(user.isEmpty()){
            throw new AuthenticationFailedException(ErrorMessages.CREDENTIAL_INVALID.getMessage());
        }
        UserDetails userDetails = user.get();
        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}











