package org.jsp.cms.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.entity.Department;

public interface DepartmentDao {

	Department saveDepartment(Department department);

	List<Department> findAllDepartments();

	Optional<Department> findDepartmentById(int id);

	Department updateDepartment(Department department);

	void deleteDepartmentById(int id);

}
