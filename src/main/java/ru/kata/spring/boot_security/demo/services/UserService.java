package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService  {
	User getUserById(Long id);

	User getUserByIdWhithRoles(Long id);

	List<User> getAllUsers();

	@Transactional
	void addUser(User newUser);

	@Transactional
	void updateUser(User updatedUser);

	@Transactional
	void deleteUser(Long id);

	Set<Role> getRoles();
}
