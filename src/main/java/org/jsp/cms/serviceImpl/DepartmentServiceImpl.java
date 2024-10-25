package org.jsp.cms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.dao.DepartmentDao;
import org.jsp.cms.entity.Department;
import org.jsp.cms.exceptionClasses.InvalidDepartmentIdException;
import org.jsp.cms.exceptionClasses.NoDepartmentFoundException;
import org.jsp.cms.responseStructure.ResponseStructure;
import org.jsp.cms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public ResponseEntity<?> saveDepartment(Department department) {

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Department Saved Successfully...").body(departmentDao.saveDepartment(department)).build());
	}

	@Override
	public ResponseEntity<?> findAllDepartments() {
		List<Department> departments = departmentDao.findAllDepartments();

		if (departments.isEmpty())
			throw NoDepartmentFoundException.builder().message("No Department found in the database table...").build();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Departments Found successfully in the database table...").body(departments).build());
	}

	@Override
	public ResponseEntity<?> findDepartmentById(int id) {

		Optional<Department> department = departmentDao.findDepartmentById(id);

		if (department.isEmpty())
			throw InvalidDepartmentIdException.builder().message("No department Id present in the database table...")
					.build();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Department found successfully in the database table...").body(department).build());
	}

	@Override
	public ResponseEntity<?> updateDepartment(Department department) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseStructure.builder().status(HttpStatus.OK.value())
						.message("Department updated successfully...").body(departmentDao.updateDepartment(department))
						.build());
	}

	@Override
	public ResponseEntity<?> deleteDepartmentById(int id) {

		Optional<Department> department = departmentDao.findDepartmentById(id);

		if (department.isEmpty())
			throw InvalidDepartmentIdException.builder()
					.message("No department Id present to delete in the database table...").build();

		departmentDao.deleteDepartmentById(id);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Department found successfully in the database table...").body(department.get()).build());
	}

}
