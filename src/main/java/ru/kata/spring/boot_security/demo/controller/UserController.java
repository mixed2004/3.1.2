package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;

public interface UserController {

	@GetMapping("/")
	String toLogin();

	@GetMapping("admin/users")
	String showAllUsers(Model model);

	@GetMapping("admin/user/{id}")
	String showUserForAdmin(@PathVariable("id") Long id, Model userModel);

	@GetMapping("/user")
	String showUserForUser(@AuthenticationPrincipal User user, Model userModel);

	@GetMapping("/new")
	String newUserPage(@ModelAttribute("userBoy") User user, Model model);

	@PostMapping("admin/users")
	String createUser(@ModelAttribute("user") User user);

	@GetMapping("/{id}/edit")
	String editeUserPage(@PathVariable Long id, Model userModel);

	@PatchMapping("/{id}")
	String updateUser(@ModelAttribute("user") User user);

	@DeleteMapping("/{id}")
	String deleteUser(@PathVariable Long id);
}
