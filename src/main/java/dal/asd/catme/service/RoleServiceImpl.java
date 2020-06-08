package dal.asd.catme.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import dal.asd.catme.beans.Enrollment;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.dao.IRoleDao;
import dal.asd.catme.database.DatabaseAccess;

@Component
public class RoleServiceImpl implements IRoleService{
	
	IRoleDao roleDao;
	
	DatabaseAccess db;

	@Override
	public String assignTa(Enrollment user) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			db=SystemConfig.instance().getDatabaseAccess();
			con = db.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			roleDao=SystemConfig.instance().getRoleDao();
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
