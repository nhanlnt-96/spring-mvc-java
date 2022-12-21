package com.main.comic.service.user;

import com.main.comic.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUserData(User userData);

    User getUserById(int userId);

    void removeUserById(int userId);

    boolean checkEmailIsExist(String email, int userId);
}
