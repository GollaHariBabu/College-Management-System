package org.jsp.cms.serviceImpl;

import java.io.File;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.jsp.cms.dao.CourseDao;
import org.jsp.cms.dao.DepartmentDao;
import org.jsp.cms.dao.FacultyDao;
import org.jsp.cms.dao.UserDao;
import org.jsp.cms.entity.Course;
import org.jsp.cms.entity.Department;
import org.jsp.cms.entity.Faculty;
import org.jsp.cms.entity.User;
import org.jsp.cms.exceptionClasses.InvalidDepartmentIdException;
import org.jsp.cms.exceptionClasses.InvalidFacultyIdException;
import org.jsp.cms.exceptionClasses.InvalidUserIdException;
import org.jsp.cms.exceptionClasses.NoFacultyFoundException;
import org.jsp.cms.responseStructure.ResponseStructure;
import org.jsp.cms.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FacultyServiceImpl implements FacultyService {

	@Autowired
	private FacultyDao facultyDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private CourseDao courseDao;

	private static final String FolderPath = "C:\\Users\\harib\\OneDrive\\Desktop\\images\\";

	@Override
	public ResponseEntity<?> saveFaculty(Faculty faculty, int uid) {

		Optional<User> optional = userDao.findUserById(uid);

		if (optional.isEmpty())
			throw InvalidUserIdException.builder().message("Invalid User Id... Unable to saveFaculty...").build();

		User user = optional.get();

		faculty.setUser(user);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty saved successfully...").body(facultyDao.saveFaculty(faculty)).build());
	}

	@Override
	public ResponseEntity<?> setDepartmentToFaculty(int fid, int did) {

		Optional<Faculty> optional1 = facultyDao.findFacultyById(fid);

		Optional<Department> optional2 = departmentDao.findDepartmentById(did);

		if (optional1.isEmpty())
			throw InvalidFacultyIdException.builder().message("No faculty found in the database table...").build();

		if (optional2.isEmpty())
			throw InvalidDepartmentIdException.builder().message("No Department found in the database table...")
					.build();

		Faculty faculty = optional1.get();

		Department department = optional2.get();

		faculty.setDepartment(department);

		faculty = facultyDao.saveFaculty(faculty);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("department saved to faculty successfully...").body(faculty).build());
	}

	@Override
	public ResponseEntity<?> findAllFaculties() {

		List<Faculty> faculties = facultyDao.findAllFaculties();

		if (faculties.isEmpty())
			throw NoFacultyFoundException.builder().message("No Faculty Found in the database table...").build();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Faculties found successfully...").body(faculties).build());
	}

	@Override
	public ResponseEntity<?> findFacultyById(int id) {

		Optional<Faculty> faculty = facultyDao.findFacultyById(id);

		if (faculty.isEmpty())
			throw InvalidFacultyIdException.builder().message("Faculty Id is not present in the database table...")
					.build();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty Found successfully...").body(faculty).build());
	}

	@Override
	public ResponseEntity<?> deleteFacultyById(int id) {

		Optional<Faculty> optional = facultyDao.findFacultyById(id);

		if (optional.isEmpty())
			throw InvalidFacultyIdException.builder().message("Invalid Faculty Id... Unable to delete faculty Id...")
					.build();

		List<Course> cl = courseDao.findAllCourses();

		for (Course c : cl) {
			if (c.getFaculty().getId() == id) {
				c.setFaculty(null);
				courseDao.saveCourse(c);
			}
		}

		facultyDao.deleteFacultyById(id);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty Found successfully...").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> updateFaculty(Faculty faculty) {

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty updated successfully...").body(facultyDao.updateFaculty(faculty)).build());
	}

	@Override
	public ResponseEntity<?> setOfficeHoursToFaculty(LocalTime officeHours, int fid) {

		Optional<Faculty> optional = facultyDao.findFacultyById(fid);

		if (optional.isEmpty())
			throw InvalidFacultyIdException.builder().message("Invalid Faculty Id... Unable to set officeHours...")
					.build();

		Faculty faculty = optional.get();

		faculty.setOfficeHours(officeHours);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("set officeHours to faculty successfully...").body(facultyDao.saveFaculty(faculty)).build());
	}

	@Override
	public ResponseEntity<?> uploadPhoto(int fid, MultipartFile file) {
		Optional<Faculty> optional = facultyDao.findFacultyById(fid);
		if (optional.isEmpty())
			throw InvalidFacultyIdException.builder().message("No faculty is present in the database table...").build();

		Faculty faculty = optional.get();

		String photo = FolderPath + file.getOriginalFilename();

		try {
			file.transferTo(new File(photo));
		} catch (Exception e) {
			e.printStackTrace();
		}

		faculty.setPhoto(photo);

		facultyDao.saveFaculty(faculty);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Profile photo uploaded successfully...").body(faculty).build());
	}
}
