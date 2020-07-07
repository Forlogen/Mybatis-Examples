import DAO.Account;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class Demo {
    public static void main(String[] args) throws Exception {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.使用构建者创建工厂对象
        SqlSessionFactory factory = builder.build(in);
        //4.使用SqlSessionFactory生产SqlSession对象
        SqlSession session = factory.openSession();
        //5.使用SqlSession创建dao接口的代理对象
        UserMapper account = session.getMapper(UserMapper.class);
        //6.使用代理对象执行查询所有方法
        List<Account> users = account.findAllUserInfo();
        for(Account user : users) {
            System.out.println(user);
        }
        //7.释放资源
        session.close();
        in.close();
    }
}
