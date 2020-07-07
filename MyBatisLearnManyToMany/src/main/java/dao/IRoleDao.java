package dao;

import domain.Role;

import java.util.List;

public interface IRoleDao {
    List<Role> findAll();
}
