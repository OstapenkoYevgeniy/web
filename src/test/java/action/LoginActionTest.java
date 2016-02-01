package action;

import com.john.action.ActionException;
import com.john.action.LoginAction;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActionTest {
    @Test
    public void executeTest() {
        HttpServletRequestForTest req = new HttpServletRequestForTest();
        HttpSessionForTest httpSession;

        req.setParameter("identifier", "777");
        req.setParameter("password", "777");

        LoginAction loginAction = new LoginAction();
        try {
            String actionResult = loginAction.execute(req, null);
            httpSession = (HttpSessionForTest) req.getSession(false);

            assertEquals(actionResult, "index");
            assertNotNull(httpSession);
        } catch (ActionException e) {
            e.printStackTrace();
            fail();
        }
    }
}
