package org.jsp.cms.service;

import java.time.LocalTime;

import org.jsp.cms.entity.Faculty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FacultyService {

	ResponseEntity<?> saveFaculty(Faculty faculty, int uid);

	ResponseEntity<?> setDepartmentToFaculty(int fid, int did);

	ResponseEntity<?> findAllFaculties();

	ResponseEntity<?> findFacultyById(int id);

	ResponseEntity<?> deleteFacultyById(int id);

	ResponseEntity<?> updateFaculty(Faculty faculty);

	ResponseEntity<?> setOfficeHoursToFaculty(LocalTime officeHours, int fid);

	ResponseEntity<?> uploadPhoto(int fid, MultipartFile file);


}
