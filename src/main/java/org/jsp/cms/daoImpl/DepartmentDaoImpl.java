package org.jsp.cms.daoImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.dao.DepartmentDao;
import org.jsp.cms.entity.Department;
import org.jsp.cms.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department saveDepartment(Department department) {

		return departmentRepository.save(department);
	}

	@Override
	public List<Department> findAllDepartments() {

		return departmentRepository.findAll();
	}

	@Override
	public Optional<Department> findDepartmentById(int did) {

		return departmentRepository.findById(did);
	}

	@Override
	public Department updateDepartment(Department department) {

		return departmentRepository.save(department);
	}

	@Override
	public void deleteDepartmentById(int id) {

		departmentRepository.deleteById(id);
	}

}
