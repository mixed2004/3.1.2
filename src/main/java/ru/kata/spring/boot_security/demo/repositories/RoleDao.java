package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleDao {
	List<Role> findAll();
	void save(Role role);
	Role getRoleByName(String name);

	List<Role> getRoleByUserId(Long id);
}
