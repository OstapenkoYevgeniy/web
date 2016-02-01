package com.john;

import com.john.action.Action;
import com.john.action.ActionException;
import com.john.action.ActionFactory;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet", urlPatterns = "/controller")
public class MainServlet extends HttpServlet {
    Logger log = org.slf4j.LoggerFactory.getLogger(MainServlet.class);
    public static final String ACTION_PARAMETER_NAME = "action";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.debug("Start service.");
        String param = req.getParameter(ACTION_PARAMETER_NAME);
        log.debug("param: " + param);
        if (param == null) {
            resp.sendRedirect("/controller?action=DisplayPage&location=index");
            return;
        }
        Action action;
        try {
            action = ActionFactory.getAction(param);
            log.debug("Action: " + action);
        } catch (ActionException e) {
            throw new InternalServerError(e);
        }
        String view;
        try {
            view = action.execute(req, resp);
            log.debug("view: " + view);
        } catch (ActionException e) {
            throw new InternalServerError(e);
        }
        req.setAttribute("title", view);// TODO бывает redirect: в начале
        if (view.startsWith("redirect:")) {
            resp.sendRedirect("/controller?action=" + view.substring(9));
            return;
        }
        req.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(req, resp);
    }
}

