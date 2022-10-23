package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	public User getById(Long id) {
		return entityManager.find(User.class, id);
	}

	public List<User> findAll() {
		return entityManager.createQuery("From User", User.class).getResultList();
	}

	public void addUser(User user) {
		entityManager.persist(user);
	}

	public void updateUser(User updatedUser) {
		entityManager.merge(updatedUser);
	}

	public void deleteUser(Long id) {
		entityManager.remove(getById(id));
	}

	public User getUserByName(String name) {
		return entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.roles roles where u.login = :name ",
				User.class).setParameter("name", name).getSingleResult();
	}
	public User getUserByIdWhithRoles(Long id) {
		return entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.roles roles where u.id = :id",
				User.class).setParameter("id", id).getSingleResult();
	}


}
