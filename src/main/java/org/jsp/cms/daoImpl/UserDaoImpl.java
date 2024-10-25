package org.jsp.cms.daoImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.dao.UserDao;
import org.jsp.cms.entity.User;
import org.jsp.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {

		return userRepository.save(user);
	}

	@Override
	public List<User> findAllUsers() {

		return userRepository.findAll();
	}

	@Override
	public Optional<User> findUserById(int id) {

		return userRepository.findById(id);
	}

	@Override
	public User updateUser(User user) {

		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(int id) {

		userRepository.deleteById(id);
		;
	}

	@Override
	public Optional<User> findByUsernameAndPassword(String username, String password) {

		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public List<User> findAllActiveUsers() {

		return userRepository.findAllActiveUsers();
	}

	@Override
	public List<User> findAllInActiveUsers() {

		return userRepository.findAllInActiveUsers();
	}

	@Override
	public List<User> findAllBlockedUsers() {

		return userRepository.findAllBlockedUsers();
	}

}
