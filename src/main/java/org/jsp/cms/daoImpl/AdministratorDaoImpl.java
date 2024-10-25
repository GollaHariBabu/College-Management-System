package org.jsp.cms.daoImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.dao.AdministratorDao;
import org.jsp.cms.entity.Administrator;
import org.jsp.cms.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdministratorDaoImpl implements AdministratorDao {

	@Autowired
	private AdministratorRepository administratorRepository;

	@Override
	public Administrator saveAdministrator(Administrator administrator) {
		return administratorRepository.save(administrator);
	}

	@Override
	public Administrator updateAdministrator(Administrator administrator) {

		return administratorRepository.save(administrator);
	}

	@Override
	public List<Administrator> findAllAdministrators() {

		return administratorRepository.findAll();
	}

	@Override
	public Optional<Administrator> findAdministratorById(int id) {

		return administratorRepository.findById(id);
	}

	@Override
	public void deleteAdministratorById(int id) {

		administratorRepository.deleteById(id);
	}

}
