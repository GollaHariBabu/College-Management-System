package org.jsp.cms.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.entity.Enrollment;

public interface EnrollmentDao {


	Enrollment saveEnrollment(Enrollment enrollment);

	List<Enrollment> findAllEnrollments();

	Optional<Enrollment> findEnrollmentById(int id);

	Enrollment updateEnrollment(Enrollment enrollment);

	void deleteEnrollmentById(int id);

}
