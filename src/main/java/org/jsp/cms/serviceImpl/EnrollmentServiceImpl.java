package org.jsp.cms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.dao.CourseDao;
import org.jsp.cms.dao.EnrollmentDao;
import org.jsp.cms.dao.StudentDao;
import org.jsp.cms.entity.Course;
import org.jsp.cms.entity.Enrollment;
import org.jsp.cms.entity.Student;
import org.jsp.cms.exceptionClasses.InvalidCourseIdException;
import org.jsp.cms.exceptionClasses.InvalidEnrollmentIdException;
import org.jsp.cms.exceptionClasses.InvalidStudentIdException;
import org.jsp.cms.exceptionClasses.NoEnrollmentFoundException;
import org.jsp.cms.responseStructure.ResponseStructure;
import org.jsp.cms.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	private EnrollmentDao enrollmentDao;

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private CourseDao courseDao;

	@Override
	public ResponseEntity<?> saveEnrollment(int cid, int sid) {

		Optional<Student> optional1 = studentDao.findStudentById(sid);
		Optional<Course> optional2 = courseDao.findCourseById(cid);

		if (optional1.isEmpty())
			throw InvalidStudentIdException.builder().message("Invalid Student Id...").build();

		if (optional2.isEmpty())
			throw InvalidCourseIdException.builder().message("Invalid Course Id...").build();

		Student student = optional1.get();

		Course course = optional2.get();

		Enrollment enrollment = Enrollment.builder().course(course).student(student).build();

		enrollment = enrollmentDao.saveEnrollment(enrollment);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("  Student Enroll to course successfully...").body(enrollment).build());
	}

	@Override
	public ResponseEntity<?> findAllEnrollments() {

		List<Enrollment> enrollments = enrollmentDao.findAllEnrollments();

		if (enrollments.isEmpty())
			throw NoEnrollmentFoundException.builder().message("No Enrollment Found in the database table...").build();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Enrollments successfully Found in the database table...").body(enrollments).build());
	}

	@Override
	public ResponseEntity<?> findEnrollmentById(int id) {
		Optional<Enrollment> optional = enrollmentDao.findEnrollmentById(id);
		if (optional.isEmpty())
			throw InvalidEnrollmentIdException.builder().message("Invalid Enrollment Id...").build();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Enrollment found successfully...").body(optional).build());
	}

	@Override
	public ResponseEntity<?> updateEnrollment(Enrollment enrollment) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseStructure.builder().status(HttpStatus.OK.value())
						.message("Enrollment updated successfully...").body(enrollmentDao.updateEnrollment(enrollment))
						.build());
	}

	@Override
	public ResponseEntity<?> deleteEnrollmentById(int id) {

		Optional<Enrollment> enrollment = enrollmentDao.findEnrollmentById(id);
		if (enrollment.isEmpty())
			throw InvalidEnrollmentIdException.builder().message("Invalid Enrollment Id...Unable to delete enrollment")
					.build();

		enrollmentDao.deleteEnrollmentById(id);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Enrollment deleted successfully...").body(enrollment.get()).build());
	}

}
