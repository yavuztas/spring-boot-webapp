package dev.yavuztas.boilerplate.springbootwebapp.controller;

import dev.yavuztas.boilerplate.springbootwebapp.domain.RoleType;
import dev.yavuztas.boilerplate.springbootwebapp.domain.User;
import dev.yavuztas.boilerplate.springbootwebapp.service.IUserService;
import dev.yavuztas.boilerplate.springbootwebapp.service.IUserWebserviceClient;
import dev.yavuztas.boilerplate.springbootwebapp.view.UserItemsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserWebserviceClient userWebserviceClient;

    @GetMapping("/user")
    public String userDetails(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        UserItemsView userItems = userWebserviceClient.getUserItems(user.getUsername());
        model.addAttribute("items", userItems.getItems());
        return "user-details";
    }

    @Secured(RoleType.ADMIN)
    @GetMapping("/users")
    public String users(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @Secured(RoleType.ADMIN)
    @GetMapping("/user/{username}")
    public String userItems(@AuthenticationPrincipal User user, @PathVariable String username, Model model){
        model.addAttribute("user", user);
        UserItemsView userItems = userWebserviceClient.getUserItems(username);
        model.addAttribute("itemHolder", userItems.getUsername());
        model.addAttribute("items", userItems.getItems());
        return "user-details";
    }

}
