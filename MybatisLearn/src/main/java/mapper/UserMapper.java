package mapper;

import DAO.Account;

import java.util.List;

public interface UserMapper {
    List<Account> findAllUserInfo();
}
