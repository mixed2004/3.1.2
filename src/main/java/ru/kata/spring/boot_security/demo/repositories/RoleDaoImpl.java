package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(Role role) {
		entityManager.persist(role);
	}

	@Override
	public List<Role> findAll() {
		return entityManager.createQuery("From Role", Role.class).getResultList();
	}

	@Override
	public Role getRoleByName(String name) {
		return entityManager.createQuery("FROM Role where role = :name ",
				Role.class).setParameter("name", name).getSingleResult();
	}

	public List<Role> getRoleByUserId(Long id) {
		return entityManager.createQuery("SELECT r FROM Role r JOIN FETCH r.users u where u.id = :id",
				Role.class).setParameter("id", id).getResultList();
	}
}
