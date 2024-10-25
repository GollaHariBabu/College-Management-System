package org.jsp.cms.daoImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.dao.StudentDao;
import org.jsp.cms.entity.Student;
import org.jsp.cms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student saveStudent(Student student) {

		return studentRepository.save(student);
	}

	@Override
	public Optional<Student> findStudentById(int sid) {

		return studentRepository.findById(sid);
	}

	@Override
	public List<Student> findAllStudents() {

		return studentRepository.findAll();
	}

	@Override
	public void deleteStudentById(int id) {
		studentRepository.deleteById(id);

	}

}
