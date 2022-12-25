package com.main.comic.dao.user;

import com.main.comic.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    void saveUserData(User userData);

    User getUserById(int userId);

    void removeUserById(int userId);

    boolean checkEmailIsExist(String email, int userId);

    User findUserByEmail(String email);
}
