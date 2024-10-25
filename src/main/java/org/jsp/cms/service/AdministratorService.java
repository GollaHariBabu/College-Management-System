package org.jsp.cms.service;

import org.jsp.cms.entity.Administrator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AdministratorService {

	ResponseEntity<?> findAllAdministrators();

	ResponseEntity<?> findAdministratorById(int id);

	ResponseEntity<?> updateAdministrator(Administrator administrator);

	ResponseEntity<?> deleteAdministratorById(int id);

	ResponseEntity<?> saveAdministrator(Administrator administrator, int uid);

	ResponseEntity<?> setDepartmentToAdministrator(int aid, int did);

	ResponseEntity<?> uploadPhoto(int aid, MultipartFile file);


	
}
