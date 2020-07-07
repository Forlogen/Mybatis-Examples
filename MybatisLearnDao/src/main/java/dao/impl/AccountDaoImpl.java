package dao.impl;

import dao.AccountDao;
import domain.Account;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private SqlSessionFactory factory;

    public AccountDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public List<Account> findAll() {
        SqlSession session = factory.openSession();
        List<Account> user = session.selectList("dao.AccountDao.findAll");
        session.close();
        return user;
    }

    public void saveUser(Account account) {
        SqlSession session = factory.openSession();
        session.insert("dao.AccountDao.saveUser", account);
        session.commit();
        session.close();
    }

    public void updateUser(Account account) {
        SqlSession session = factory.openSession();
        session.update("dao.AccountDao.updateUser", account);
        session.commit();
        session.close();

    }

    public void deleteUser(int id) {
        SqlSession session = factory.openSession();
        session.update("dao.AccountDao.deleteUser", id);
        session.commit();
        session.close();

    }

    public Account getUserById(int id) {
        SqlSession session = factory.openSession();
        Account user = session.selectOne("dao.AccountDao.getUserById", id);
        session.close();
        return user;
    }

    public Account getUserByName(String username) {
        SqlSession session = factory.openSession();
        Account user = session.selectOne("dao.AccountDao.getUserByName", username);
        session.close();
        return user;
    }

    public int getCount() {
        SqlSession session = factory.openSession();
        int count = session.selectOne("dao.AccountDao.getCount");
        session.close();
        return count;
    }
}
