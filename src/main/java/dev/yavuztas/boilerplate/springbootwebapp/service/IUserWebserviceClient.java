package dev.yavuztas.boilerplate.springbootwebapp.service;

import dev.yavuztas.boilerplate.springbootwebapp.view.UserItemsView;

public interface IUserWebserviceClient {

    UserItemsView getUserItems(String username);

}
