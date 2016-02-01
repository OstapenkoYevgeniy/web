package com.john.action;

import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.Role;
import com.john.entity.User;
import com.john.service.DataProcessing;
import com.john.service.PasswordService;
import com.john.validator.IdentifierValidator;
import com.john.validator.PasswordValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String identifier = DataProcessing.process(req.getParameter("identifier")); // TODO RENAME
        String password = req.getParameter("password");

        boolean isValidData = true;

        if (!IdentifierValidator.isValid(identifier, req)) {
            isValidData = false;
        }

        if (!PasswordValidator.isValid(password, req)) {
            isValidData = false;
        }

        if (!isValidData) {
            return "index";
        }

        User user;

        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            Dao<User, Long> userDao = daoFactory.getDao(User.class);
            user = userDao.getByColumn("identifier", identifier);
        } catch (Exception e) {
            throw new ActionException(e);
        }

        if (user == null) {

            req.setAttribute("error", "error.user.not.found"); // TODO
            return "index";
        }

        password = PasswordService.getSha2Password(password, user.getId());

        if (!user.getPassword().equals(password)) {
            req.setAttribute("passwordError", "error.wrong.password"); // TODO
            return "index";
        }

        Role role = user.getRole();


        HttpSession httpSession = req.getSession();
        System.out.println(httpSession);
        httpSession.setAttribute("user", user);

        httpSession.setMaxInactiveInterval(60 * role.getSessionLifeTime()); // TODO проверить роли на нулл

        return "index";
    }
}