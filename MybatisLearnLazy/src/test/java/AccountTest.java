import dao.IAccountDao;
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

public class AccountTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IAccountDao accountDao;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        //提交事务
        // sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll(){
        List<Account> accounts = accountDao.findAll();
//        for(Account account : accounts){
//            System.out.println(account + "  "+ account.getUser());
//        }
    }
    /*
    - Opening JDBC Connection
    - Created connection 1946645411.
    - ==>  Preparing: select * from account
    - ==> Parameters:
    - ====>  Preparing: select * from user where id = ?
    - ====> Parameters: 42(Integer)
    - <====      Total: 1
    - ====>  Preparing: select * from user where id = ?
    - ====> Parameters: 43(Integer)
    - <====      Total: 1
    - <==      Total: 3
    - Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@740773a3]
    - Returned connection 1946645411 to pool.
     */

    // 开启延迟加载
    /*
    2020-06-06 21:47:10,330 176    [           main] DEBUG ansaction.jdbc.JdbcTransaction  - Opening JDBC Connection
    2020-06-06 21:47:11,294 1140   [           main] DEBUG source.pooled.PooledDataSource  - Created connection 1706292388.
    2020-06-06 21:47:11,298 1144   [           main] DEBUG        dao.IAccountDao.findAll  - ==>  Preparing: select * from account
    2020-06-06 21:47:11,325 1171   [           main] DEBUG        dao.IAccountDao.findAll  - ==> Parameters:
    2020-06-06 21:47:11,393 1239   [           main] DEBUG        dao.IAccountDao.findAll  - <==      Total: 3
    2020-06-06 21:47:11,395 1241   [           main] DEBUG ansaction.jdbc.JdbcTransaction  - Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@65b3f4a4]
    2020-06-06 21:47:11,395 1241   [           main] DEBUG source.pooled.PooledDataSource  - Returned connection 1706292388 to pool.
     */
}
