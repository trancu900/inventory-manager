package vn.tvc.inventory.services;


import vn.tvc.inventory.model.User;
import vn.tvc.inventory.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    private static final String PATH = "data\\users.csv";

    @Override
    public User getById(long id) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        List<User> newUsers = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH);
        for (String record : records) {
            newUsers.add(User.parseUser(record));
        }
        return newUsers;
    }

    @Override
    public void add(User newUser) {
        List<User> users = getUsers();
        users.add(newUser);
        CSVUtils.write(PATH, users);
    }

    @Override
    public boolean existById(long id) {
        return getById(id) != null;
    }

    @Override
    public boolean existByPhone(String phone) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getPhone().equals(phone))
                return true;
        }
        return false;
    }
}
