import dao.IUserDao;
import domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public  void init()throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public  void destroy()throws Exception{
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFindAll(){
        List<User> all = userDao.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUsername("Ball");
        user.setAddress("Shanghai");
        user.setSex("女");

        userDao.saveUser(user);
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setUsername("Amy");
        user.setId(47);
        userDao.updateUser(user);
    }

    @Test
    public void testDeleteUser(){
        userDao.deleteUser(47);
    }

    @Test
    public void testFindById(){
        User user = userDao.findById(41);
        System.out.println(user);
    }

    @Test
    public void testFindTotal(){
        int total = userDao.findTotal();
        System.out.println(total);
    }

    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%a%");
        for (User user : users) {
            System.out.println(user);
        }
    }
}
