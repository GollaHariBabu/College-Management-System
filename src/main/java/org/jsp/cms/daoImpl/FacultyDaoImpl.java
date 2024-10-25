package org.jsp.cms.daoImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.dao.FacultyDao;
import org.jsp.cms.entity.Faculty;
import org.jsp.cms.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FacultyDaoImpl implements FacultyDao {

	@Autowired
	private FacultyRepository facultyRepository;

	@Override
	public Faculty saveFaculty(Faculty faculty) {

		return facultyRepository.save(faculty);
	}

	@Override
	public Optional<Faculty> findFacultyById(int fid) {

		return facultyRepository.findById(fid);
	}

	@Override
	public List<Faculty> findAllFaculties() {

		return facultyRepository.findAll();
	}

	@Override
	public void deleteFacultyById(int id) {

		facultyRepository.deleteById(id);
	}

	@Override
	public Faculty updateFaculty(Faculty faculty) {

		return facultyRepository.save(faculty);
	}

}
