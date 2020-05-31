package dal.asd.catme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	@Override
	public int addCourse(Course course) {
		Connection connection = db.getConnection();
		return addCourse(connection, CatmeUtil.ADD_COURSE_QUERY, course);
	}

	@Override
	public int deleteCourse(String courseId) {
		int result;
		Connection connection = db.getConnection();
		updateQuery(connection,CatmeUtil.DELETE_ENROLLMENT_QUERY,courseId);
		updateQuery(connection,CatmeUtil.DELETE_COURSE_INSTRUCTOR_QUERY,courseId);
		result=updateQuery(connection, CatmeUtil.DELETE_COURSE_QUERY, courseId);
		
		
		return result;
	}

	@Override
	public ResultSet getAllCourses() {
		Connection connection = db.getConnection();
		ResultSet rs = db.executeQuery(CatmeUtil.SELECT_COURSE);
		return rs;
	}

	@Override
	public ResultSet getUsersNotAssignedForCourse(Course course) {
		Connection connection = db.getConnection();
		return listUsers(connection, CatmeUtil.LIST_USER_QUERY, course);
	}

	@Override
	public int addInstructorToCourse(String user,String course) {
		Connection connection = db.getConnection();
		//rs = db.executeQuery(CatmeUtil.SELECT_TA_ROLE);
		int result = 0;
		//System.out.println("rs****"+rs.getString("roleId"));
		int roleId = selectTARole(connection);
		//Add TA Role to user
		int userRole=insertTARole(connection,user,roleId);
		//Add the user as instructor to course
		result=addAsCourseInstructor(connection,course,userRole);
		
		
		return result;
	}
	
	public int selectTARole(Connection connection) {
		try {
			preparedStatement = connection.prepareStatement(CatmeUtil.SELECT_TA_ROLE);
			preparedStatement.setString(1, CatmeUtil.TA);
			rs=preparedStatement.executeQuery();
			if(rs.next())
			System.out.println(rs.getString("roleId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
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
			System.out.println(result);
			return result;
	}
	
	public int addCourse(Connection connection,String query,Course course) {
		int result = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, course.getCourseId());
			preparedStatement.setString(2, course.getCourseName());
			result = preparedStatement.executeUpdate();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			return result;
	}
	
	public ResultSet listUsers(Connection connection,String query,Course course) {
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, CatmeUtil.STUDENT);
			rs = preparedStatement.executeQuery();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			return rs;
	}
	
	public int insertTARole(Connection connection,String user,int roleId) {
		
		int userRoleId=0;
		//Check if user has TA role
		try {
			preparedStatement = connection.prepareStatement(CatmeUtil.SELECT_USER_ROLE_BY_BANNERID);
			preparedStatement.setString(1, user);
			rs = preparedStatement.executeQuery();
			if(rs==null) {
				preparedStatement=connection.prepareStatement(CatmeUtil.INSERT_INTO_USER_ROLE);
				preparedStatement.setInt(1, roleId);
				preparedStatement.setString(2, user);
				preparedStatement.executeUpdate();
			
				preparedStatement = connection.prepareStatement(CatmeUtil.SELECT_USER_ROLE_BY_BANNERID);
				preparedStatement.setString(1, user);
				rs = preparedStatement.executeQuery();
			
			}
			if(rs.next())
				userRoleId = Integer.parseInt(rs.getString(CatmeUtil.USER_ROLE_ID));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRoleId;
		
	}

}
