package dev.yavuztas.boilerplate.springbootwebapp.service;

import dev.yavuztas.boilerplate.springbootwebapp.domain.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

}
