package dev.yavuztas.boilerplate.springbootwebapp.service;

import dev.yavuztas.boilerplate.springbootwebapp.domain.User;
import dev.yavuztas.boilerplate.springbootwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Default implementation for Spring Security's {@link UserDetailsService}
 *
 * @author Yavuz Tas
 */
@Service
public class UserAuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> hasUser = userRepository.findByUsername(username);
        return hasUser.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

}