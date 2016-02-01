//package com.john.action;
//
//import com.john.dao.rdb.RdbDaoFactory;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
////import com.john.dao.rdb.RoleRdbDao;
//
//// TODO сделать валибдацию
//public class ClientRegistrationAction implements Action {
//    @Override
//    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
//        // TODO обрезать пробелы
//        String iin = req.getParameter("iin");
//        String name = req.getParameter("name");
//        String surname = req.getParameter("surname");
//        String patronymic = req.getParameter("patronymic");
//        String day = req.getParameter("day");
//        String month = req.getParameter("month");
//        String year = req.getParameter("year");
//        String role = req.getParameter("role");
//
//        RdbDaoFactory rdbDaoFactory = null;
//        try {
////            rdbDaoFactory = AbstractDaoFactory.getDaoFactory(RdbDaoFactory.class, ConnectionPool.getInstance());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
////        UserRdbDao userRdbDao = null;
////        RoleRdbDao roleRdbDao = null;
////        try {
////            userRdbDao = rdbDaoFactory.getDao(User.class);
////            roleRdbDao = rdbDaoFactory.getDao(Role.class);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        User user = new User();
////
////
////        user.setIin(Long.valueOf(iin));
////        user.setName(name);
////        user.setSurname(surname);
////        user.setPatronymic(patronymic);
////        user.setBirthday(Date.valueOf(year + "-" + month + "-" + day));
////
////        try {
////            user.setRole(roleRdbDao.getById(Short.valueOf(role)));
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        long index = 0;
////        try {
////            index = userRdbDao.add(user);
////            if (index == 0) {
////                return "index"; // TODO ошибка в реквест
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////            return "index"; // TODO ошибка в реквест
////        }
////
////        try {
////            user = userRdbDao.getById(index);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        req.setAttribute("newUser", user);
//
//        return "admin/user_registration_successfully";
//    }
//}
