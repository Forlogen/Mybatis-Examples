package dao;

import domain.Account;
import domain.AccountUser;

import java.util.List;

public interface IAccountDao {
    List<Account> findAll();

    List<AccountUser> findAllAccount();
}

