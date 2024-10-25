package org.jsp.cms.service;

import org.jsp.cms.entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {

	ResponseEntity<?> saveStudent(Student student, int uid);

	ResponseEntity<?> setDepartmentToStudent(int sid, int did);

	ResponseEntity<?> findStudentById(int id);

	ResponseEntity<?> findAllStudents();

	ResponseEntity<?> deleteStudentById(int id);

	ResponseEntity<?> uploadPhoto(int sid, MultipartFile file);

}
