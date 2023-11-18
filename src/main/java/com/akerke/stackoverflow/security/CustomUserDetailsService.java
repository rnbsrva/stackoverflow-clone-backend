package com.akerke.stackoverflow.security;

import com.akerke.stackoverflow.common.exception.InvalidCredentialsException;
import com.akerke.stackoverflow.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var optional = userRepository.findByEmail(email);

        if (optional.isEmpty()){
            throw new InvalidCredentialsException();
        }

        return optional.get();
    }
}
