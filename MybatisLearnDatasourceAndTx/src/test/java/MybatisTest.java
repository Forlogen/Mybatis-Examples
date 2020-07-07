import dao.AccountDao;
import domain.Account;
import domain.QueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MybatisTest {

    private InputStream in;
    private SqlSession session;
    private AccountDao mapper;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        mapper = session.getMapper(AccountDao.class);
    }

    @After
    public void close() throws Exception{
        session.commit();
        session.close();
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
    public void getUerByVo(){
        QueryVo vo = new QueryVo();
        Account user = new Account();
        user.setUsername("%o%");
        vo.setUser(user);
        List<Account> userByVo = mapper.getUserByVo(vo);
        for (Account account : userByVo) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindByUser(){
        Account a = new Account();
        a.setUsername("%F%");
        List<Account> byUser = mapper.findByUser(a);
        for (Account account : byUser) {
            System.out.println(account); // Account{id=1, username='Forlogen', password='123456'}
        }
    }


    @Test
    public void testFindByIds(){
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        vo.setIds(list);
        List<Account> byIds = mapper.findByIds(vo);
        for (Account ele : byIds) {
            System.out.println(ele);
        }
        /*
        Account{id=1, username='Forlogen', password='123456'}
        Account{id=2, username='Kobe', password='88824'}
        Account{id=3, username='James', password='232323'}
         */
    }
}
