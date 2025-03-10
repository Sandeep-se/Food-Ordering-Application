package com.project.controller;

import java.sql.ResultSet;
import com.project.service.UserService;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean register(Object []values) {
        return userService.register(values);
    }

    public ResultSet login(Object values[]) {
        return userService.login(values);
    }
    public boolean updateUser(Object values[]) {
    	boolean response=userService.updateUser(values);
    	return response;
    }
}


