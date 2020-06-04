package dal.asd.catme.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dal.asd.catme.beans.Enrollment;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.util.CatmeUtil;
@Component
public class RoleDaoImpl implements IRoleDao{
	
	@Autowired
	DatabaseAccess db;
	
	@Autowired
	IUserDao userDao;
	
	@Autowired
	ICourseDao courseDao;
	
	Connection con = null;
	
		
	@Override
	public int assignRole(String bannerId, int roleId, Connection con) {
		String query = "INSERT IGNORE INTO UserRole (UserRoleId, BannerId, RoleId) VALUES ( NULL, '" +
				bannerId + "' , '" + roleId + "' );";

		int rs = 0;
		try {
			Statement stmt = con.createStatement();
			return stmt.executeUpdate(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	@Override
	public int addInstructor(int courseId, int userRoleId, Connection con) {
		// TODO Auto-generated method stub
		String query = "INSERT IGNORE INTO CourseInstructor (CourseInstructorId, CourseId, UserRoleId) VALUES ( NULL,'" +
				courseId + "' , '" + userRoleId +"' );";

		int rs = 0;
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	@Override
	public int checkCourseInstructor(String bannerId, int courseId, Connection con) {
		// TODO Auto-generated method stub
		int rowCount = 0;
		// TODO Auto-generated method stub
		try {
			String query = "SELECT EXISTS(WITH temp AS ( SELECT ci.UserRoleId,ci.CourseId, ur.BannerId FROM CourseInstructor ci INNER JOIN UserRole ur ON ci.UserRoleId = ur.UserRoleId ) SELECT * FROM temp WHERE temp.BannerId = '"+ bannerId +"' AND temp.CourseId = "+courseId+");";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			rowCount = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowCount;
	}
	
	@Override
	public int checkUserRole(String bannerId, int roleId, Connection con) {
		
		int rowCount = 0;
		// TODO Auto-generated method stub
		try {
			String query = "SELECT EXISTS(SELECT * FROM UserRole WHERE BannerId = '"+ bannerId +"' AND RoleId = "+roleId+");";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			rowCount = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowCount;
		
	}

	@Override
	public int getUserRoleId(String bannerId, int roleId, Connection con) {
		
		int userRoleId = -1;
		// TODO Auto-generated method stub
		try {
			String query = "SELECT UserRoleId FROM UserRole WHERE BannerId = '"+ bannerId +"' AND RoleId = "+roleId+";";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			userRoleId = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userRoleId;
		
	}
	
@Override
	public String assignTa(Enrollment user, Connection con) {
		// TODO Auto-generated method stub
		String isAssigned = "";
		
		try {
			
			//If the user exists
			if(0 != userDao.checkExistingUser(user.bannerId, con)){
				
				//If the course exists
				if (0 != courseDao.checkCourseExists(user.courseId, con)) {
					
					//If the user is not currently taking the course
					if(0 == courseDao.checkCourseRegistration(user.bannerId, user.courseId, con)){
						
						//If the user is not already registered as an instructor for the course
						if(0 == checkCourseInstructor(user.bannerId, user.courseId, con)){
							
							//If the UserRoleId already exists for the role
							if(0 != checkUserRole(user.bannerId, CatmeUtil.TA_ROLE_ID, con)) {
								
								//Get the UserRoleId
								int userRoleId = getUserRoleId(user.bannerId, CatmeUtil.TA_ROLE_ID, con);
								
								//Add the user to the CourseInstructor Table
								addInstructor(user.courseId, userRoleId, con);
								
								
							}
							
							//If the UserRoleId doesn't exist for the role
							else {
								
								//Assign the TA role to the user in UserRole relation
								assignRole(user.bannerId, CatmeUtil.TA_ROLE_ID, con);
								
								//Get the UserRoleId
								int userRoleId = getUserRoleId(user.bannerId, CatmeUtil.TA_ROLE_ID, con);
								
								//Add the user to the CourseInstructor Table
								addInstructor(user.courseId, userRoleId, con);
								
							}
							isAssigned = "The user is successfully assigned as TA.";
							
						} else {
							isAssigned = "This user is already an instructor for this course.";
						}
					} else {
						isAssigned = "This user is currently registered in this course. Failed to assign.";
					}
				} else {
					isAssigned = "No course exists with this Course Id. Failed to assign.";
				}
				
			} else {
				isAssigned = "No user exists with this Banner Id. Failed to assign.";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) { e.printStackTrace(); }
		    }
		}

		
		return isAssigned;
	}
	

}

