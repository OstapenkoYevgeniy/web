package com.john.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayPage implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return req.getParameter("location");
    }
}
