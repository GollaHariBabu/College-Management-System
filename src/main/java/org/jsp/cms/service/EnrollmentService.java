package org.jsp.cms.service;

import org.jsp.cms.entity.Enrollment;
import org.springframework.http.ResponseEntity;

public interface EnrollmentService {

	ResponseEntity<?> saveEnrollment(int cid, int sid);

	ResponseEntity<?> findAllEnrollments();

	ResponseEntity<?> findEnrollmentById(int id);

	ResponseEntity<?> updateEnrollment(Enrollment enrollment);

	ResponseEntity<?> deleteEnrollmentById(int id);


	

}
