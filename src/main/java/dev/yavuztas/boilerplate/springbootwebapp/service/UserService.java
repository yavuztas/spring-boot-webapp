package dev.yavuztas.boilerplate.springbootwebapp.service;

import dev.yavuztas.boilerplate.springbootwebapp.domain.User;
import dev.yavuztas.boilerplate.springbootwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class to retrieve web app users
 *
 * @author Yavuz Tas
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(Sort.by("id"));
    }
}