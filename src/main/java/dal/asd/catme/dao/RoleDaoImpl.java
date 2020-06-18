package dal.asd.catme.dao;

import java.sql.*;

import dal.asd.catme.beans.Enrollment;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.util.CatmeUtil;
import static dal.asd.catme.util.DBQueriesUtil.*;
public class RoleDaoImpl implements IRoleDao{

	IUserDao userDao;
	
	ICourseDao courseDao;

	@Override
	public int assignRole(String bannerId, int roleId, Connection con) {

		int rs = 0;
		try {
			PreparedStatement stmt = con.prepareStatement(ASSIGN_ROLE_QUERY);
			stmt.setString(1,bannerId);
			stmt.setInt(2,roleId);

			rs = stmt.executeUpdate();
			return rs;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/*
	 * @Override public int addInstructor(int courseId, int userRoleId, Connection
	 * con) { // TODO Auto-generated method stub String query =
	 * "INSERT IGNORE INTO CourseInstructor (CourseInstructorId, CourseId, UserRoleId) VALUES ( NULL,'"
	 * + courseId + "' , '" + userRoleId +"' );";
	 * 
	 * int rs = 0; try { Statement stmt = con.createStatement();
	 * stmt.executeUpdate(query); } catch (Exception e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } return rs; }
	 */
	@Override
	public int addInstructor(String courseId, int userRoleId, Connection con) {
		// TODO Auto-generated method stub

		int rs = 0;
		try {
			PreparedStatement stmt = con.prepareStatement(INSERT_COURSE_INSTRUCTOR_QUERY);
			stmt.setString(1,courseId);
			stmt.setInt(2,userRoleId);

			rs = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	/*
	 * @Override public int checkCourseInstructor(String bannerId, int courseId,
	 * Connection con) { // TODO Auto-generated method stub int rowCount = 0; //
	 * TODO Auto-generated method stub try { String query =
	 * "SELECT EXISTS(WITH temp AS ( SELECT ci.UserRoleId,ci.CourseId, ur.BannerId FROM CourseInstructor ci INNER JOIN UserRole ur ON ci.UserRoleId = ur.UserRoleId ) SELECT * FROM temp WHERE temp.BannerId = '"
	 * + bannerId +"' AND temp.CourseId = "+courseId+");";
	 * 
	 * Statement stmt = con.createStatement(); ResultSet rs =
	 * stmt.executeQuery(query); rs.next(); rowCount = rs.getInt(1); } catch
	 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return rowCount; }
	 */
	
	@Override
	public int checkCourseInstructor(String bannerId, String courseId, Connection con) {
		// TODO Auto-generated method stub
		int rowCount = 0;
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = con.prepareStatement(CHECK_COURSE_INSTRUCTOR_QUERY);
			stmt.setString(1,bannerId);
			stmt.setString(2,courseId);

			ResultSet rs = stmt.executeQuery();
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
			PreparedStatement stmt = con.prepareStatement(CHECK_USER_ROLE);
			stmt.setString(1,bannerId);
			stmt.setInt(2,roleId);
			ResultSet rs = stmt.executeQuery();
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
			PreparedStatement stmt = con.prepareStatement(GET_USER_ROLEID_QUERY);
			stmt.setString(1,bannerId);
			stmt.setInt(2,roleId);
			ResultSet rs = stmt.executeQuery();
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
			userDao=SystemConfig.instance().getUserDao();
			if(0 != userDao.checkExistingUser(user.bannerId, con)){
				
				//If the course exists
				courseDao=SystemConfig.instance().getCourseDao();
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

