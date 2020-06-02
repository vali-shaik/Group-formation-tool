package dal.asd.catme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dal.asd.catme.beans.Enrollment;
import dal.asd.catme.dao.IRoleDao;

@Component
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	IRoleDao roleDao;

	@Override
	public String assignTa(Enrollment user) {
		// TODO Auto-generated method stub
		return (roleDao.assignTa(user));
	}

}
