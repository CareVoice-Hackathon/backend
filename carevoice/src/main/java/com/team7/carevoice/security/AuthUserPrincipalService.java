package com.team7.carevoice.security;

import com.team7.carevoice.model.CareVoiceUser;
import com.team7.carevoice.repository.UserRepository;
import com.team7.carevoice.exceptions.UserNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Utility class to retrieve authentication details for the current user.
 */
@Component
public class AuthUserPrincipalService {

    private final UserRepository userRepository;

    public AuthUserPrincipalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves the current authenticated {@link UserPrincipal}.
     *
     * @return the authenticated {@link UserPrincipal}, or null if no user is authenticated
     */
    public UserPrincipal getCurrentUserPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && isUserPrincipal(auth)) {
            return (UserPrincipal) auth.getPrincipal();
        }
        return null;
    }

    /**
     * Retrieves the current authenticated {@link CareVoiceUser}.
     *
     * @return the authenticated {@link CareVoiceUser} entity from the database
     * @throws UserNotFoundException if the user does not exist in the database
     */
    public CareVoiceUser getAuthenticatedAcmePlexUser() {
        UserPrincipal currentUser = getCurrentUserPrincipal();
        if (currentUser == null) {
            throw new IllegalStateException("No authenticated user found");
        }

        // Use the email from UserPrincipal to fetch AcmePlexUser
        return userRepository.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + currentUser.getUsername()));
    }

    /**
     * Checks if the current user is authenticated.
     *
     * @return true if authenticated, false otherwise
     */
    public boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && isUserPrincipal(auth);
    }

    /**
     * Checks if the provided email matches the currently authenticated user's email.
     *
     * @param email the email to check
     * @return true if the provided email matches the authenticated user's email, false otherwise
     */
    public boolean checkAuthenticationByEmail(String email) {
        UserPrincipal currentUser = getCurrentUserPrincipal();
        if (currentUser == null) {
            return false; // No user is authenticated
        }
        return currentUser.getUsername().equalsIgnoreCase(email);
    }

    /**
     * Helper method to check if the authentication principal is a {@link UserPrincipal}.
     *
     * @param auth the {@link Authentication} object
     * @return true if the principal is a {@link UserPrincipal}, false otherwise
     */
    private boolean isUserPrincipal(Authentication auth) {
        return auth.isAuthenticated() && auth.getPrincipal() instanceof UserPrincipal;
    }
}

