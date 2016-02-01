package com.john.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession httpSession = req.getSession(false);
        if (httpSession != null) httpSession.invalidate();
        return "index";
    }
}
