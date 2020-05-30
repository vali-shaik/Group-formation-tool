package dal.asd.catme.dao;

import java.sql.Connection;
import java.sql.ResultSet;
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
	
	@Override
	public int addCourse(Course Course) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public int deleteCourse(String courseId) {
		db.getConnection();
		return 0;
	}

	@Override
	public ResultSet getAllCourses() {
		Connection connection = db.getConnection();
		ResultSet rs = db.executeQuery(CatmeUtil.SELECT_COURSE);
		db.closeConnection(connection);
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

}
