package com.john.service;

import com.john.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SecurityService {
    public static boolean access(HttpServletRequest req, String accessLevel) {
        HttpSession httpSession = req.getSession(false);
        if (httpSession == null) {
            return false;
        }
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            return false;
        }
        return user.getRole().getName().equals(accessLevel);
    }
}
