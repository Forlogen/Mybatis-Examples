package dao;

import domain.Account;
import domain.QueryVo;

import java.util.List;

public interface AccountDao {

    // 查询所有
    List<Account> findAll();

    // 保存方法
    void saveUser(Account account);

    // 更新操作
    void updateUser(Account account);

    // 删除操作
    void deleteUser(int id);

    // 条件查询
    Account getUserById(int id);

    // 模糊查询
    Account getUserByName(String username);

    //
    int getCount();

    List<Account> getUserByVo(QueryVo vo);
}
