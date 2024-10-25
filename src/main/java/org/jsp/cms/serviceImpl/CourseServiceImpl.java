package org.jsp.cms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.dao.CourseDao;
import org.jsp.cms.dao.DepartmentDao;
import org.jsp.cms.dao.FacultyDao;
import org.jsp.cms.entity.Course;
import org.jsp.cms.entity.Department;
import org.jsp.cms.entity.Faculty;
import org.jsp.cms.exceptionClasses.InvalidCourseIdException;
import org.jsp.cms.exceptionClasses.InvalidDepartmentIdException;
import org.jsp.cms.exceptionClasses.InvalidFacultyIdException;
import org.jsp.cms.exceptionClasses.NoCourseFoundException;
import org.jsp.cms.responseStructure.ResponseStructure;
import org.jsp.cms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private FacultyDao facultyDao;

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public ResponseEntity<?> saveCourse(Course course) {

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Course Created Successfully...").body(courseDao.saveCourse(course)).build());
	}

	@Override
	public ResponseEntity<?> findAllCourses() {
		List<Course> courses = courseDao.findAllCourses();

		if (courses.isEmpty())
			throw NoCourseFoundException.builder().message("No Course Found in the database table...").build();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Courses Found successfully in the database table...").body(courses).build());
	}

	@Override
	public ResponseEntity<?> findCourseById(int id) {
		Optional<Course> course = courseDao.findCourseById(id);

		if (course.isEmpty())
			throw InvalidCourseIdException.builder().message(" Course Id is not present in the database table... ")
					.build();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message(" Course Found successfully in the database table...").body(course).build());
	}

	@Override
	public ResponseEntity<?> updateCourse(Course course) {

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Course Updated Successfully...").body(courseDao.updateCourse(course)).build());
	}

	@Override
	public ResponseEntity<?> deleteCourseById(int id) {

		Optional<Course> course = courseDao.findCourseById(id);

		if (course.isEmpty())
			throw InvalidCourseIdException.builder()
					.message(" Course Id is not present to delete in the database table... ").build();

		courseDao.deleteCourseById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message(" Course Deleted successfully in the database table...").body(course.get()).build());
	}

	@Override
	public ResponseEntity<?> setFacultyToCourse(int cid, int fid) {

		Optional<Faculty> optional1 = facultyDao.findFacultyById(fid);

		Optional<Course> optional2 = courseDao.findCourseById(cid);

		if (optional1.isEmpty())
			throw InvalidFacultyIdException.builder().message("No faculty found in the database table...").build();

		if (optional2.isEmpty())
			throw InvalidCourseIdException.builder().message("No Course found in the database table...").build();

		Faculty faculty = optional1.get();

		Course course = optional2.get();

		course.setFaculty(faculty);

		course = courseDao.saveCourse(course);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("course set to Faculty successfully...").body(course).build());
	}

	@Override
	public ResponseEntity<?> setDepartmentToCourse(int cid, int did) {

		Optional<Department> optional1 = departmentDao.findDepartmentById(did);

		Optional<Course> optional2 = courseDao.findCourseById(cid);

		if (optional1.isEmpty())
			throw InvalidDepartmentIdException.builder().message("No Department found in the database table...")
					.build();

		if (optional2.isEmpty())
			throw InvalidCourseIdException.builder().message(" Course Id is not present in the database table...")
					.build();

		Department department = optional1.get();
		Course course = optional2.get();

		course.setDepartment(department);

		course = courseDao.saveCourse(course);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Department set to Course successfully...").body(course).build());
	}

}
