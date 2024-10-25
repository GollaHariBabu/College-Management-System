package org.jsp.cms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.dao.UserDao;
import org.jsp.cms.entity.User;
import org.jsp.cms.exceptionClasses.InvalidCredentialsException;
import org.jsp.cms.exceptionClasses.InvalidOTPException;
import org.jsp.cms.exceptionClasses.InvalidUserIdException;
import org.jsp.cms.exceptionClasses.NoUserFoundException;
import org.jsp.cms.responseStructure.ResponseStructure;
import org.jsp.cms.service.UserService;
import org.jsp.cms.util.AuthOTP;
import org.jsp.cms.util.AuthUser;
import org.jsp.cms.util.Helper;
import org.jsp.cms.util.UserRole;
import org.jsp.cms.util.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private Helper helper;

	@Override
	public ResponseEntity<?> saveUser(User user) {

		user.setStatus(UserStatus.IN_ACTIVE);

		user.setRole(UserRole.STUDENT);

		user.setOtp(helper.generateOTP());

		String email = user.getEmail();
		int otp = user.getOtp();
		user = userDao.saveUser(user);

		helper.sendMail(email, otp);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User saved successfully...").body(user).build());
	}

	@Override
	public ResponseEntity<?> findAllUsers() {
		List<User> users = userDao.findAllUsers();

		if (users.isEmpty())
			throw NoUserFoundException.builder().message("No User present in the database table...").build();

		return ResponseEntity.status(HttpStatus.FOUND).body(ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.message("All Users found successfully...").body(users).build());
	}

	@Override
	public ResponseEntity<?> findUserById(int id) {
		Optional<User> user = userDao.findUserById(id);

		if (user.isEmpty())
			throw InvalidUserIdException.builder().message("User Id is not present in the database table...").build();

		return ResponseEntity.status(HttpStatus.OK.value())
				.body(ResponseStructure.builder().status(HttpStatus.OK.value())
						.message("User found successfully... in the database table...").body(user.get()).build());
	}

	@Override
	public ResponseEntity<?> updateUser(User user) {

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student Updated Successfully...").body(userDao.updateUser(user)).build());
	}

	@Override
	public ResponseEntity<?> deleteUserById(int id) {
		Optional<User> user = userDao.findUserById(id);
		if (user.isEmpty())
			throw InvalidUserIdException.builder().message("No User Id present in the database table to delete...")
					.build();
		userDao.deleteUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User Deleted Successfully...").body(user.get()).build());
	}

	@Override
	public ResponseEntity<?> login(AuthUser authUser) {

		Optional<User> optional = userDao.findByUsernameAndPassword(authUser.getUsername(), authUser.getPassword());

		if (optional.isEmpty())
			throw InvalidCredentialsException.builder().message("Invalid Username or Password").build();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User verified successfully...").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> setStatusToActive(int id) {

		Optional<User> user = userDao.findUserById(id);
		if (user.isEmpty())
			throw InvalidUserIdException.builder().message("No User Id present in the database table to delete...")
					.build();

		User u = user.get();

		u.setStatus(UserStatus.ACTIVE);

		u = userDao.updateUser(u);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Successfully updated User status to ACTIVE...").body(u).build());

	}

	@Override
	public ResponseEntity<?> setStatusToInActive(int id) {
		Optional<User> user = userDao.findUserById(id);
		if (user.isEmpty())
			throw InvalidUserIdException.builder().message("No User Id present in the database table to delete...")
					.build();

		User u = user.get();

		u.setStatus(UserStatus.IN_ACTIVE);

		u = userDao.updateUser(u);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Successfully updated User status to IN_ACTIVE...").body(u).build());
	}

	@Override
	public ResponseEntity<?> setStatusToBlocked(int id) {
		Optional<User> user = userDao.findUserById(id);
		if (user.isEmpty())
			throw InvalidUserIdException.builder().message("No User Id present in the database table to delete...")
					.build();

		User u = user.get();

		u.setStatus(UserStatus.BLOCKED);

		u = userDao.updateUser(u);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Successfully updated User status to BLOCKED...").body(u).build());
	}

	@Override
	public ResponseEntity<?> findAllActiveUsers() {

		List<User> users = userDao.findAllActiveUsers();

		if (users.isEmpty())
			throw NoUserFoundException.builder().message("No Active Users present in the database table...").build();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Active Users found successfully...").body(users).build());
	}

	@Override
	public ResponseEntity<?> findAllInActiveUsers() {

		List<User> users = userDao.findAllInActiveUsers();

		if (users.isEmpty())
			throw NoUserFoundException.builder().message("No IN_Active Users present in the database table...").build();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All IN_Active Users found successfully...").body(users).build());
	}

	@Override
	public ResponseEntity<?> findAllBlockedUsers() {

		List<User> users = userDao.findAllBlockedUsers();

		if (users.isEmpty())
			throw NoUserFoundException.builder().message("No BLOCKED Users present in the database table...").build();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All BLOCKED Users found successfully...").body(users).build());
	}

	@Override
	public ResponseEntity<?> verifyOTP(AuthOTP authOTP) {

		Optional<User> optional = userDao.findUserById(authOTP.getUid());

		if (optional.isEmpty())
			throw InvalidUserIdException.builder().message(null).build();

		User user = optional.get();

		if (user.getOtp() != authOTP.getOtp())
			throw InvalidOTPException.builder().message("Invalid otp ... unable to make user active as verify").build();

		user.setStatus(UserStatus.ACTIVE);

		user = userDao.updateUser(user);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User Verified Successfully...").body(user).build());
	}

}
