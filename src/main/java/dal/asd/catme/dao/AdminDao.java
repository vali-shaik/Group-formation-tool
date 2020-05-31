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
	
	@Override
	public int addCourse(Course Course) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public int deleteCourse(String courseId) {
		int result;
		Connection connection = db.getConnection();
		result=updateQuery(connection,CatmeUtil.DELETE_ENROLLMENT_QUERY,courseId);
		if(result>0) {
			result=updateQuery(connection,CatmeUtil.DELETE_COURSE_INSTRUCTOR_QUERY,courseId);
			if(result>0) {
				result=updateQuery(connection, CatmeUtil.DELETE_COURSE_QUERY, courseId);
			}
		}
		
		return result;
	}

	@Override
	public ResultSet getAllCourses() {
		Connection connection = db.getConnection();
		ResultSet rs = db.executeQuery(CatmeUtil.SELECT_COURSE);
		return rs;
	}

	@Override
	public List<User> getUsersNotAssignedForCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addInstructorToCourse(User user) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int updateQuery(Connection connection,String query,String courseId) {
		int result = 0;
		try {
			System.out.println(query);
			System.out.println(courseId);
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

}
