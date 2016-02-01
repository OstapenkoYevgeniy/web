package com.john.action;

import com.john.entity.IssuePaymentCard;
import com.john.service.DaoService;
import com.john.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ManagerHomeDisplay implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        int offset;
        int limit;

        try {
            offset = Integer.valueOf(req.getParameter("offset"));
            if (offset < 1) {
                offset = 1;
            }
        } catch (Exception e) {
            offset = 1;
        }

        try {
            limit = Integer.valueOf(req.getParameter("limit"));
        } catch (Exception e) {
            limit = 8;
        }


        try {
            req.setAttribute("issuePaymentCards", DaoService.getAll(IssuePaymentCard.class, limit, (offset - 1) * limit));
            req.setAttribute("pages", Math.ceil((double) DaoService.getCount(IssuePaymentCard.class) / limit));
            req.setAttribute("limit", limit);
            req.setAttribute("offset", offset);

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ActionException(e);
        }

        return "manager/manager_home";
    }
}
