import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

// TODO Ошибки в логи!

public class DaoFactoryTest {
    @Test
    public void TestCreationDaoFactory() {
        DaoFactory daoFactory = null;
        try {
            daoFactory = AbstractDaoFactory.getDaoFactory();
            assertNotNull(daoFactory);
        } catch (Exception e) {
            fail();
        }

        Dao<User, Long> userDao;
        try {
            userDao = daoFactory.getDao(User.class);
            assertNotNull(userDao);
        } catch (Exception e) {
            fail();
        }

        try {
            daoFactory.getDao(Class.class);
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}
