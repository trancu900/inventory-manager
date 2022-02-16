package vn.tvc.inventory.services;

import vn.tvc.inventory.model.User;

import java.util.List;

public interface IUserService {
    User getById(long id);

    List<User> getUsers();

    void add(User newUser);

    boolean existById(long id);

    boolean existByPhone(String phone);

}
