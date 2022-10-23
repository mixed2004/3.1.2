package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleDao;
import ru.kata.spring.boot_security.demo.repositories.UserDao;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
	private final UserDao userDao;
	private final RoleDao roleDao;

	public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
	}

	@Override
	public User getUserById(Long id) {
		return userDao.getById(id);
	}

	@Override
	public User getUserByIdWhithRoles(Long id) {
		return userDao.getUserByIdWhithRoles(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public void addUser(User newUser) {
		newUser.setRoles(Set.of(roleDao.getRoleByName("USER")));
		userDao.addUser(newUser);
	}

	@Override
	public void updateUser(User updatedUser) {
		updatedUser.setRoles(Set.copyOf(roleDao.getRoleByUserId(updatedUser.getId())));
		userDao.updateUser(updatedUser);
	}

	@Override
	public void deleteUser(Long id) {
		userDao.deleteUser(id);
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return userDao.getUserByName(login);
	}

	@Override
	public Set<Role> getRoles() {
		return Set.copyOf(roleDao.findAll());
	}
}
