package dao;

import domain.Account;
import domain.QueryVo;

import java.util.List;

public interface AccountDao {

    // 查询所有
    List<Account> findAll();

    // 条件查询
    Account getUserById(int id);

    // 模糊查询
    Account getUserByName(String username);

    List<Account> getUserByVo(QueryVo vo);

    List<Account> findByUser(Account account);

    List<Account> findByIds(QueryVo vo);

}
