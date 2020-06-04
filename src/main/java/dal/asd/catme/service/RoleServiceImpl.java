package dal.asd.catme.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dal.asd.catme.beans.Enrollment;
import dal.asd.catme.dao.IRoleDao;
import dal.asd.catme.database.DatabaseAccess;

@Component
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	IRoleDao roleDao;
	
	@Autowired
	DatabaseAccess db;

	@Override
	public String assignTa(Enrollment user) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = db.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			return (roleDao.assignTa(user, con));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "";
				
	}

}
