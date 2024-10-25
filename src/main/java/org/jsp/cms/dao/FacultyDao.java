package org.jsp.cms.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.entity.Faculty;

public interface FacultyDao {

	Faculty saveFaculty(Faculty faculty);

	Optional<Faculty> findFacultyById(int fid);

	List<Faculty> findAllFaculties();

	void deleteFacultyById(int id);

	Faculty updateFaculty(Faculty faculty);

}
