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
public class AdminDao implements IAdminDao,IListDetailsDao{

	@Autowired
	DatabaseAccess db;
	
	PreparedStatement preparedStatement;
	ResultSet rs;
	Connection connection;
	
	
	@Override
	public int addCourse(Course course) {
		connection = db.getConnection();
		int result= addCourse(connection, CatmeUtil.ADD_COURSE_QUERY, course);
		db.closeConnection();
		return result;
	}

	@Override
	public int deleteCourse(String courseId) {
		int result;
		connection = db.getConnection();
		updateQuery(connection,CatmeUtil.DELETE_ENROLLMENT_QUERY,courseId);
		updateQuery(connection,CatmeUtil.DELETE_COURSE_INSTRUCTOR_QUERY,courseId);
		result=updateQuery(connection, CatmeUtil.DELETE_COURSE_QUERY, courseId);
		db.closeConnection();
		return result;
	}
	
	@Override
	public int addInstructorToCourse(String user,String course) {
		connection = db.getConnection();
		//rs = db.executeQuery(CatmeUtil.SELECT_TA_ROLE);
		int result = 0;
		//System.out.println("rs****"+rs.getString("roleId"));
		int roleId = selectInstructorRole(connection);
		//Add TA Role to user
		int userRole=insertTARole(connection,user,roleId);
		//Add the user as instructor to course
		result=addAsCourseInstructor(connection,course,userRole);
		db.closeConnection();
		
		return result;
	}

	@Override
	public List<Course> getAllCourses() {
		List<Course> courses = new ArrayList<>();
		connection = db.getConnection();
		System.out.println("conn "+connection);
		ResultSet resultSet = db.executeQuery(CatmeUtil.SELECT_COURSE);
		try {
			
			while(resultSet.next()) {
				Course course = new Course();
				course.setCourseId(resultSet.getString(CatmeUtil.COURSE_ID));
				course.setCourseName(resultSet.getString(CatmeUtil.COURSE_NAME));
				courses.add(course);
			}
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}

	@Override
	public List<User> getUsersNotAssignedForCourse(Course course) {
		connection = db.getConnection();
		ResultSet resultSet = listUsers(connection, CatmeUtil.LIST_USER_QUERY, course);
		 List<User> users = new ArrayList<User>();
			try {
				while(resultSet.next()){
					User user = new User();
					user.setBannerId(resultSet.getString(CatmeUtil.BANNER_ID));
					user.setFirstName(resultSet.getString(CatmeUtil.FIRST_NAME));
					user.setLastName(resultSet.getString(CatmeUtil.LAST_NAME));
					users.add(user);
				}
				db.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return users;
	}
	

	
	
	public int selectInstructorRole(Connection connection) {
		int result=0;
		try {
			preparedStatement = connection.prepareStatement(CatmeUtil.SELECT_ROLE_BY_ROLENAME);
			preparedStatement.setString(1, CatmeUtil.ROLE_INSTRUCTOR);
			rs=preparedStatement.executeQuery();
			if(rs.next())
				result = Integer.parseInt(rs.getString("roleId"));
			System.out.println(result);
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
