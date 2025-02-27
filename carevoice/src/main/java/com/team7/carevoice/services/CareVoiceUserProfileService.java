package com.team7.carevoice.services;

import com.team7.carevoice.dto.request.CreateUserRequest;
import com.team7.carevoice.model.CareVoiceUser;
import com.team7.carevoice.repository.UserRepository;
import com.team7.carevoice.security.AuthUserPrincipalService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer for managing user profile operations such as registration, profile updates,
 * and billing address management.
 */

@Service
public class CareVoiceUserProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUserPrincipalService authUserPrincipalService;

    public CareVoiceUserProfileService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthUserPrincipalService authUserPrincipalService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authUserPrincipalService = authUserPrincipalService;
    }

    @Transactional(readOnly = true)
    public CareVoiceUser getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
    }

    @Transactional
    public CareVoiceUser registerUser(CreateUserRequest registerRequest) {
        validateRegistrationRequest(registerRequest);
        checkIfEmailExists(registerRequest.getEmail());

        CareVoiceUser newUser = new CareVoiceUser(
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                registerRequest.getFirstName(),
                registerRequest.getLastName()
        );

        return userRepository.save(newUser);
    }

    private void validateRegistrationRequest(CreateUserRequest registerRequest) {
        if (registerRequest == null || registerRequest.getEmail() == null || registerRequest.getPassword() == null) {
            throw new IllegalArgumentException("Registration details must not be null.");
        }
    }

    private void checkIfEmailExists(String email) {
        userRepository.findByEmail(email).ifPresent(existing -> {
            throw new IllegalArgumentException("Email already in use: " + email);
        });
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.isBlank();
    }

    /**
     * Saves or updates the user in the database.
     *
     * @param user The user to save or update.
     * @return The saved user entity.
     */
    @Transactional
    public CareVoiceUser save(CareVoiceUser user) {
        return userRepository.save(user);
    }
}
