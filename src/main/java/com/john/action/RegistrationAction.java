package com.john.action;

import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.User;
import com.john.service.PasswordService;
import com.john.validator.BirthdayValidator;
import com.john.validator.IdentifierValidator;
import com.john.validator.NameValidator;
import com.john.validator.PasswordValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class RegistrationAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        if (!isValidData(req)) {
            return "registration";
        }
        String identifier = req.getParameter("identifier");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String patronymic = req.getParameter("patronymic");
        String day = req.getParameter("day");
        String month = req.getParameter("month");
        String year = req.getParameter("year");
        String password = req.getParameter("password");
        String retryPassword = req.getParameter("retry_password");

        User user = null;
        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            daoFactory.setAutoCommit(false);
            Dao<User, Long> userDao = daoFactory.getDao(User.class);
            user = userDao.getByColumn("identifier", identifier);

            if (user == null) {
                req.setAttribute("registrationError", "error.registration.identifier.not.found");
                return "registration";
            }
            if (user.isActive()) {
                req.setAttribute("registrationError", "error.registration.successfully");
                return "registration";
            }
            if (!user.getName().equals(name)) {
                req.setAttribute("registrationError", "error.registration.do.not.coincide");
                return "registration";
            }
            if (!user.getSurname().equals(surname)) {
                req.setAttribute("registrationError", "error.registration.do.not.coincide");
                return "registration";
            }
            if (!user.getPatronymic().equals(patronymic)) {
                req.setAttribute("registrationError", "error.registration.do.not.coincide");
                return "registration";
            }
            Date birthday = Date.valueOf(year + "-" + month + "-" + day);
            if (!user.getBirthday().equals(birthday)) {
                req.setAttribute("registrationError", "error.registration.do.not.coincide");
                return "registration";
            }
            if (!password.equals(retryPassword)) {
                req.setAttribute("registrationError", "error.registration.password.do.not.coincide");
                return "registration";
            }

            user.setPassword(PasswordService.getSha2Password(password, user.getId()));
            user.setActive(true);
            if (userDao.update(user) > 1) {
                daoFactory.rollback();
                req.setAttribute("registrationError", "error.500");
                return "registration";
            } else {
                daoFactory.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("registrationError", "error.500");
            return "registration";
        }

        req.getSession().setAttribute("user", user);
        return "redirect:DisplayPage&location=registration_successfully";
    }

    private boolean isValidData(HttpServletRequest req) {
        boolean isValid = true;
        String day = req.getParameter("day");
        String month = req.getParameter("month");
        String year = req.getParameter("year");
        if (!IdentifierValidator.isValid(req.getParameter("identifier"), req))
            isValid = false;
        if (!NameValidator.isValidName(req.getParameter("name"), req))
            isValid = false;
        if (!NameValidator.isValidSurname(req.getParameter("surname"), req))
            isValid = false;
        if (!NameValidator.isValidPatronymic(req.getParameter("patronymic"), req))
            isValid = false;
        if (!BirthdayValidator.isValid(year + "-" + month + "-" + day, req)) {
            isValid = false;
        } else {
            req.setAttribute("returnDay", day);
            req.setAttribute("returnMonth", month);
            req.setAttribute("returnYear", year);
        }
        if (!PasswordValidator.isValid(req.getParameter("password"), req))
            isValid = false;
        return isValid;
    }
}
