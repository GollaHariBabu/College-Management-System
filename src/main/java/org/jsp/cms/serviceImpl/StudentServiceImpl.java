package org.jsp.cms.serviceImpl;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.jsp.cms.dao.DepartmentDao;
import org.jsp.cms.dao.StudentDao;
import org.jsp.cms.dao.UserDao;
import org.jsp.cms.entity.Department;
import org.jsp.cms.entity.Student;
import org.jsp.cms.entity.User;
import org.jsp.cms.exceptionClasses.InvalidDepartmentIdException;
import org.jsp.cms.exceptionClasses.InvalidStudentIdException;
import org.jsp.cms.exceptionClasses.InvalidUserIdException;
import org.jsp.cms.exceptionClasses.NoStudentFoundException;
import org.jsp.cms.responseStructure.ResponseStructure;
import org.jsp.cms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DepartmentDao departmentDao;

	private static final String FolderPath = "C:\\Users\\harib\\OneDrive\\Desktop\\images\\";

	@Override
	public ResponseEntity<?> saveStudent(Student student, int uid) {

		Optional<User> optional = userDao.findUserById(uid);

		if (optional.isEmpty())
			throw InvalidUserIdException.builder().message("Invalid User Id... Unable to saveStudent...").build();

		User user = optional.get();

		student.setUser(user);

		student = studentDao.saveStudent(student);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student saved successfully...").body(student).build());
	}

	@Override
	public ResponseEntity<?> setDepartmentToStudent(int sid, int did) {

		Optional<Student> optional1 = studentDao.findStudentById(sid);

		Optional<Department> optional2 = departmentDao.findDepartmentById(did);

		if (optional1.isEmpty())
			throw InvalidStudentIdException.builder().message("No Student found in the database table...").build();

		if (optional2.isEmpty())
			throw InvalidDepartmentIdException.builder().message("No Department found in the database table...")
					.build();

		Student student = optional1.get();

		Department department = optional2.get();

		student.setDepartment(department);

		student = studentDao.saveStudent(student);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("set department to faculty successfully...").body(student).build());
	}

	@Override
	public ResponseEntity<?> findStudentById(int id) {

		Optional<Student> student = studentDao.findStudentById(id);
		if (student.isEmpty())
			throw InvalidStudentIdException.builder().message("No student is present in the database table...").build();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student found successfully in the database table...").body(student).build());
	}

	@Override
	public ResponseEntity<?> findAllStudents() {

		List<Student> students = studentDao.findAllStudents();

		if (students.isEmpty())
			throw NoStudentFoundException.builder().message("No student found in the database table...").build();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Students found successfully in the database table...").body(students).build());
	}

	@Override
	public ResponseEntity<?> deleteStudentById(int id) {

		Optional<Student> student = studentDao.findStudentById(id);
		if (student.isEmpty())
			throw InvalidStudentIdException.builder().message("No student is present in the database table...").build();

		studentDao.deleteStudentById(id);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student found successfully in the database table...").body(student.get()).build());
	}

	@Override
	public ResponseEntity<?> uploadPhoto(int sid, MultipartFile file) {

		Optional<Student> optional = studentDao.findStudentById(sid);
		if (optional.isEmpty())
			throw InvalidStudentIdException.builder().message("No student is present in the database table...").build();

		Student student = optional.get();

		String photo = FolderPath + file.getOriginalFilename();

		try {
			file.transferTo(new File(photo));
		} catch (Exception e) {
			e.printStackTrace();
		}

		student.setPhoto(photo);

		studentDao.saveStudent(student);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Profile photo uploaded successfully...").body(student).build());
	}

}
