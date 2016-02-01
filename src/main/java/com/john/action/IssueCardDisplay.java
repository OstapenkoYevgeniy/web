package com.john.action;

import com.john.service.TypePaymentCardService;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IssueCardDisplay implements Action {
    Logger log = org.slf4j.LoggerFactory.getLogger(IssueCardDisplay.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        System.out.println(TypePaymentCardService.getTypesPaymentCards());
        req.setAttribute("typesPaymentCards", TypePaymentCardService.getTypesPaymentCards());
        return "issue_card";
    }
}
