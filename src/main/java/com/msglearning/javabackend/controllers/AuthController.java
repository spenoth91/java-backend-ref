package com.msglearning.javabackend.controllers;

import com.msglearning.javabackend.entity.User;
import com.msglearning.javabackend.services.PasswordService;
import com.msglearning.javabackend.services.Tokenservice;
import com.msglearning.javabackend.services.UserService;
import com.msglearning.javabackend.to.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Properties;
import java.util.Set;

@RestController
@RequestMapping({ ControllerConstants.API_PATH_AUTH })
public class AuthController {

    private static final String REGISTER_PATH = "/register";
    private static final String LOGIN_PATH = "/login";

    @Autowired
    UserService userService;

    @Autowired
    Tokenservice tokenService;

    @PostMapping(REGISTER_PATH)
    public boolean register(@RequestBody UserTO userTO) {

        try {
            userService.save(userTO);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @PostMapping(LOGIN_PATH)
    public String login(@RequestBody UserTO userTO) {

        if ( !StringUtils.hasLength(userTO.getEmail()) || !StringUtils.hasLength(userTO.getPassword())) {

            //No email and/or password

            return "Forbidden";
        }

        Optional<User> userOpt = this.userService.findByEmail(userTO.getEmail());
        if (userOpt.isPresent() && PasswordService.checkPassword(userTO.getPassword(), userOpt.get().getPassword())) {

            // Create token

            return this.tokenService.createTokenHeader(userOpt.get().getEmail(), "USER"); // can be further extended to other roles
        }

        return "Forbidden";

    }

}
