package org.jsp.cms.serviceImpl;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.jsp.cms.dao.AdministratorDao;
import org.jsp.cms.dao.DepartmentDao;
import org.jsp.cms.dao.UserDao;
import org.jsp.cms.entity.Administrator;
import org.jsp.cms.entity.Department;
import org.jsp.cms.entity.User;
import org.jsp.cms.exceptionClasses.InvalidAdministratorIdException;
import org.jsp.cms.exceptionClasses.InvalidDepartmentIdException;
import org.jsp.cms.exceptionClasses.InvalidStudentIdException;
import org.jsp.cms.exceptionClasses.InvalidUserIdException;
import org.jsp.cms.exceptionClasses.NoAdministratorFoundException;
import org.jsp.cms.responseStructure.ResponseStructure;
import org.jsp.cms.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	private AdministratorDao administratorDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DepartmentDao departmentDao;

	private static final String FolderPath = "C:\\Users\\harib\\OneDrive\\Desktop\\images\\";

	@Override
	public ResponseEntity<?> saveAdministrator(Administrator administrator, int uid) {
		Optional<User> optional = userDao.findUserById(uid);
		if (optional.isEmpty())
			throw InvalidUserIdException.builder().message("Invalid User Id... Unable to saveAdministrator...").build();
		User user = optional.get();
		administrator.setUser(user);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseStructure.builder().status(HttpStatus.OK.value())
						.message("Administrator saved successfully...")
						.body(administratorDao.saveAdministrator(administrator)).build());
	}

	@Override
	public ResponseEntity<?> setDepartmentToAdministrator(int aid, int did) {
		Optional<Administrator> optional1 = administratorDao.findAdministratorById(aid);
		Optional<Department> optional2 = departmentDao.findDepartmentById(did);
		if (optional1.isEmpty())
			throw InvalidAdministratorIdException.builder()
					.message(" Administrator Id is not present in the database table...").build();

		if (optional2.isEmpty())
			throw InvalidDepartmentIdException.builder().message("No Administrator found in the database table...")
					.build();

		Administrator administrator = optional1.get();
		Department department = optional2.get();
		administrator.setDepartment(department);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseStructure.builder().status(HttpStatus.OK.value())
						.message("set department  to Administrator successfully...")
						.body(administratorDao.saveAdministrator(administrator)).build());
	}

	@Override
	public ResponseEntity<?> findAllAdministrators() {
		List<Administrator> administrators = administratorDao.findAllAdministrators();
		if (administrators.isEmpty())
			throw NoAdministratorFoundException.builder().message("No Administrator Found in the database table...")
					.build();
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseStructure.builder().status(HttpStatus.OK.value())
						.message("All Administrators Found successfully in the database table...").body(administrators)
						.build());
	}

	@Override
	public ResponseEntity<?> findAdministratorById(int id) {
		Optional<Administrator> administrator = administratorDao.findAdministratorById(id);
		if (administrator.isEmpty())
			throw InvalidAdministratorIdException.builder()
					.message("No Administrator Id present in the database table... ").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message(" Administrator Found successfully in the database table...").body(administrator).build());
	}

	@Override
	public ResponseEntity<?> updateAdministrator(Administrator administrator) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseStructure.builder().status(HttpStatus.OK.value())
						.message("Administrator Updated Successfully...")
						.body(administratorDao.updateAdministrator(administrator)).build());
	}

	@Override
	public ResponseEntity<?> deleteAdministratorById(int id) {
		Optional<Administrator> administrator = administratorDao.findAdministratorById(id);
		if (administrator.isEmpty())
			throw InvalidAdministratorIdException.builder()
					.message("No Administrator Id present in the database table... ").build();
		administratorDao.deleteAdministratorById(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseStructure.builder().status(HttpStatus.OK.value())
						.message(" Administrator Deleted successfully in the database table...")
						.body(administrator.get()).build());
	}

	@Override
	public ResponseEntity<?> uploadPhoto(int aid, MultipartFile file) {
		Optional<Administrator> optional = administratorDao.findAdministratorById(aid);
		if (optional.isEmpty())
			throw InvalidStudentIdException.builder().message("No administrator is present in the database table...")
					.build();

		Administrator administrator = optional.get();

		String photo = FolderPath + file.getOriginalFilename();

		try {
			file.transferTo(new File(photo));
		} catch (Exception e) {
			e.printStackTrace();
		}

		administrator.setPhoto(photo);

		administratorDao.saveAdministrator(administrator);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Profile photo uploaded successfully...").body(administrator).build());
	}

}
