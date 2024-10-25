package org.jsp.cms.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.entity.Student;

public interface StudentDao {

	Student saveStudent(Student student);

	Optional<Student> findStudentById(int sid);

	List<Student> findAllStudents();

	void deleteStudentById(int id);

}
