package com.main.comic.service.user;

import com.main.comic.dao.user.UserDAO;
import com.main.comic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImplement implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImplement(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUserData(User userData) {
        userDAO.saveUserData(userData);
    }

    @Override
    @Transactional
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    @Transactional
    public void removeUserById(int userId) {
        userDAO.removeUserById(userId);
    }

    @Override
    @Transactional
    public boolean checkEmailIsExist(String email, int userId) {
        return userDAO.checkEmailIsExist(email, userId);
    }
}
