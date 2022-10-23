package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {
	User getById(Long id);
	User getUserByIdWhithRoles(Long id);

	List<User> findAll();

	void addUser(User user);

	void updateUser(User updatedUser);

	void deleteUser(Long id);

	User getUserByName(String name);
}
