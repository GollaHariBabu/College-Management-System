package org.jsp.cms.daoImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.dao.EnrollmentDao;
import org.jsp.cms.entity.Enrollment;
import org.jsp.cms.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentDaoImpl implements EnrollmentDao {

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Override
	public Enrollment saveEnrollment(Enrollment enrollment) {

		return enrollmentRepository.save(enrollment);
	}

	@Override
	public List<Enrollment> findAllEnrollments() {

		return enrollmentRepository.findAll();
	}

	@Override
	public Optional<Enrollment> findEnrollmentById(int id) {
		return enrollmentRepository.findById(id);
	}

	@Override
	public Enrollment updateEnrollment(Enrollment enrollment) {

		return enrollmentRepository.save(enrollment);
	}

	@Override
	public void deleteEnrollmentById(int id) {
		enrollmentRepository.deleteById(id);
	}

}
