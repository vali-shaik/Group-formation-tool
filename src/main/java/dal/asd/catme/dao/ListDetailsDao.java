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
public class ListDetailsDao implements IListDetailsDao{

	@Autowired
	DatabaseAccess db;
	
	PreparedStatement preparedStatement;
	ResultSet rs;
	Connection connection;
	
	
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
	public List<User> getUsers(Course course) {
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
}
