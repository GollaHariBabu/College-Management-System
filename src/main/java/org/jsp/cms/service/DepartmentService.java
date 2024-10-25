package org.jsp.cms.service;

import org.jsp.cms.entity.Department;
import org.springframework.http.ResponseEntity;

public interface DepartmentService {

	ResponseEntity<?> saveDepartment(Department department);

	ResponseEntity<?> findAllDepartments();

	ResponseEntity<?> findDepartmentById(int id);

	ResponseEntity<?> updateDepartment(Department department);

	ResponseEntity<?> deleteDepartmentById(int id);

}
