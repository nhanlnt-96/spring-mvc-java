package com.main.comic.controller;

import com.main.comic.entity.User;
import com.main.comic.service.user.UserService;
import com.main.comic.utils.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-list")
    public String getAllUser(Model theModel) {
        List<User> theUsers = userService.getAllUsers();

        theModel.addAttribute("users", theUsers);

        return "admin/user-list";
    }

    @GetMapping("/add-user")
    public String showFormForAdd(Model theModel) {
        User theUser = new User();

        theModel.addAttribute("user", theUser);

        return "admin/user-form";
    }

    @PostMapping("/save-user")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            return "admin/user-form";
        } else {
            if (userService.checkEmailIsExist(user.getEmail(), user.getId())) {
                theModel.addAttribute("duplicateEmailError", "Email already exist");

                theModel.addAttribute("userData", user);

                return "admin/user-form";
            } else {
                if (user.getId() == 0 || !user.getPassword().equals(userService.getUserById(user.getId()).getPassword())) {
                    String passwordHash = BCrypt.hashPassword(user.getPassword());

                    user.setPassword(passwordHash);
                }

                userService.saveUserData(user);

                return "redirect:/admin/user/user-list";
            }
        }
    }

    @GetMapping("/update-user")
    public String showFormForUpdate(@RequestParam("userId") int theId, Model theModel) {
        User theUser = userService.getUserById(theId);

        theModel.addAttribute("user", theUser);

        return "admin/user-form";
    }

    @GetMapping("/remove-user")
    public String deleteUser(@RequestParam("userId") int theId) {
        userService.removeUserById(theId);

        return "redirect:/admin/user/user-list";
    }
}
