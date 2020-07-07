import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    private InputStream in;
    private SqlSession session;
    private AccountDao mapper;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        mapper = new AccountDaoImpl(factory);

    }

    @After
    public void close() throws Exception{
        in.close();
    }

    @Test
    public void testFindAll() throws Exception{
        List<Account> all = mapper.findAll();
        for(Account account: all){
            System.out.println(account);
        }
    }

    @Test
    public void testSaveUser() throws Exception {
        Account account = new Account();
        account.setId(4);
        account.setUsername("Rose");
        account.setPassword("44444");
        System.out.println(account);
        mapper.saveUser(account);
        System.out.println(account);
    }

    @Test
    public void testUpdateUser() throws Exception{
        Account account = new Account();
        account.setId(4);
        account.setUsername("Ball");
        account.setPassword("141214");
        mapper.updateUser(account);
    }

    @Test
    public void testDeleteUser() throws Exception{
        mapper.deleteUser(4);
    }

    @Test
    public void testGetUserById() throws Exception{
        Account user = mapper.getUserById(3);
        System.out.println(user);
    }

    @Test
    public void testGetUserByName() throws Exception{
        Account user = mapper.getUserByName("%F%");
        System.out.println(user);
    }

    @Test
    public void testGetCount(){
        int count = mapper.getCount();
        System.out.println(count);
    }

}
