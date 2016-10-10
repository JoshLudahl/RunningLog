package run.makemy.controllers.user_controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CurrentUserControllerAdvice {

    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        if (authentication == null) return null;
        else return (UserDetails) authentication.getPrincipal();
    }

}
