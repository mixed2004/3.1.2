package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;
@Controller
public class UserControllerImpl implements UserController {
    private final UserServiceImpl userService;
    private final PasswordEncoder encoder;

    public UserControllerImpl(UserServiceImpl userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public String toLogin() {
        return "redirect:/login";
    }

    @Override
    public String showAllUsers(Model model) {
        model.addAttribute("people", userService.getAllUsers());
        return "users";
    }

    @Override
    public String showUserForAdmin(Long id, Model userModel) {
        userModel.addAttribute("man", userService.getUserById(id));
        return "user";
    }

    @Override
    public String showUserForUser (User user, Model userModel) {
        userModel.addAttribute("man", user);
        return "user";
    }

    @Override
    public String newUserPage(User user, Model model) {
        model.addAttribute("setRoles", userService.getRoles());
        return "new";
    }

    @Override
    public String createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:users";
    }

    @Override
    public String editeUserPage(Long id, Model userModel) {
        userModel.addAttribute("userUpdate", userService.getUserByIdWhithRoles(id));
        return "edit";
    }

    @Override
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:admin/users";
    }

    @Override
    public String deleteUser(Long id) {
        userService.deleteUser(id);
        return "redirect:admin/users";
    }
}
