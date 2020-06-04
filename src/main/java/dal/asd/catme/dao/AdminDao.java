package dal.asd.catme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.util.CatmeUtil;

@Component
public class AdminDao implements IAdminDao{

	@Autowired
	DatabaseAccess db;
	
	PreparedStatement preparedStatement;
	ResultSet rs;
	Connection connection;
	
	
	@Override
	public int addCourse(Course course) {
		int result = 0;
		try {
		connection = db.getConnection();
		result= addCourse(connection, CatmeUtil.ADD_COURSE_QUERY, course);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public int deleteCourse(String courseId) {
		int result = 0;
		try {
		connection = db.getConnection();
		updateQuery(connection,CatmeUtil.DELETE_ENROLLMENT_QUERY,courseId);
		updateQuery(connection,CatmeUtil.DELETE_COURSE_INSTRUCTOR_QUERY,courseId);
		result=updateQuery(connection, CatmeUtil.DELETE_COURSE_QUERY, courseId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@Override
	public int addInstructorToCourse(String user,String course) {
	
		
		int result = 0;
		try {
			connection = db.getConnection();
		int roleId = selectInstructorRole(connection);
		System.out.println("roleId "+roleId);
		//Add TA Role to user
		int userRole=insertInstructorRole(connection,user,roleId);
		System.out.println("userRole "+userRole);
		//Add the user as instructor to course
		result=addAsCourseInstructor(connection,course,userRole);
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}

	
	

	
	
	public int selectInstructorRole(Connection connection) {
		int result=0;
		try {
			preparedStatement = connection.prepareStatement(CatmeUtil.SELECT_ROLE_BY_ROLENAME);
			preparedStatement.setString(1, CatmeUtil.ROLE_INSTRUCTOR);
			rs=preparedStatement.executeQuery();
			if(rs.next())
				result = Integer.parseInt(rs.getString("roleId"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public int addAsCourseInstructor(Connection connection,String course,int userRole) {
		int result=0;
		try {
			preparedStatement = connection.prepareStatement(CatmeUtil.SELECT_COURSE_INSTRUCTOR_BY_USER_ROLE_COURSEID);
			preparedStatement.setInt(1, userRole);
			preparedStatement.setString(2, course);
			rs = preparedStatement.executeQuery();
			if(!rs.next()) {
				preparedStatement=connection.prepareStatement(CatmeUtil.INSERT_INTO_COURSE_INSTRUCTOR);
				preparedStatement.setInt(2, userRole);
				preparedStatement.setString(1, course);
				result=preparedStatement.executeUpdate();
			}
			else
			{
				
				result = rs.getInt("userRoleId");
				System.out.println("result "+result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public int updateQuery(Connection connection,String query,String courseId) {
		int result = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, courseId);
			result = preparedStatement.executeUpdate();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			return result;
	}
	
	public int addCourse(Connection connection,String query,Course course) {
		int result = CatmeUtil.ZERO;
		try {
			preparedStatement=connection.prepareStatement(CatmeUtil.CHECK_INSTRUCTOR);
			preparedStatement.setString(1, course.getCourseId());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs==null) {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, course.getCourseId());
				preparedStatement.setString(2, course.getCourseName());
				result = preparedStatement.executeUpdate();
			}else
				result=CatmeUtil.TWO;
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			return result;
	}
	
	
	
	public int insertInstructorRole(Connection connection,String user,int roleId) {
		
		int userRoleId=0;
		//Check if user has TA role
		try {
			preparedStatement = connection.prepareStatement(CatmeUtil.SELECT_USER_ROLE_BY_BANNERID);
			preparedStatement.setString(1, user);
			preparedStatement.setInt(2, roleId);
			rs = preparedStatement.executeQuery();
			
			if(!rs.next()) {
				preparedStatement=connection.prepareStatement(CatmeUtil.INSERT_INTO_USER_ROLE);
				preparedStatement.setInt(1, roleId);
				preparedStatement.setString(2, user);
				preparedStatement.executeUpdate();
			
				preparedStatement = connection.prepareStatement(CatmeUtil.SELECT_USER_ROLE_BY_BANNERID);
				preparedStatement.setString(1, user);
				preparedStatement.setInt(2, roleId);
				rs = preparedStatement.executeQuery();
				rs.next();
			
			
			}
				userRoleId = Integer.parseInt(rs.getString(CatmeUtil.USER_ROLE_ID));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRoleId;
		
	}

}
