package org.jsp.cms.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.entity.User;

public interface UserDao {

	User saveUser(User user);

	List<User> findAllUsers();

	Optional<User> findUserById(int id);

	Optional<User> findByUsernameAndPassword(String userName, String password);

	User updateUser(User user);

	void deleteUserById(int id);

	List<User> findAllActiveUsers();

	List<User> findAllInActiveUsers();

	List<User> findAllBlockedUsers();

}
