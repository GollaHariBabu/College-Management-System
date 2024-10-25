package org.jsp.cms.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.entity.Administrator;

public interface AdministratorDao {

	Administrator saveAdministrator(Administrator administrator);

	Administrator updateAdministrator(Administrator administrator);

	List<Administrator> findAllAdministrators();

	Optional<Administrator> findAdministratorById(int id);

	void deleteAdministratorById(int id);
	
}
