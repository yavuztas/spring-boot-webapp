package dev.yavuztas.boilerplate.springbootwebapp.controller;

import dev.yavuztas.boilerplate.springbootwebapp.domain.RoleType;
import dev.yavuztas.boilerplate.springbootwebapp.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DefaultController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public RedirectView home(@AuthenticationPrincipal User user) {
        //Since our business requires, if authenticated user is ADMIN then redirect to user list page
        if (user.hasRole(RoleType.ADMIN)) {
            return new RedirectView("users");
        } else {
            return new RedirectView("user");
        }
    }

}
