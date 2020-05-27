package dal.asd.catme.dao;

import java.sql.ResultSet;
import java.util.List;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;

public class AdminDao implements IAdminDao{

	@Override
	public int addCourse(Course Course) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public int deleteCourse(String courseId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		System.out.println("inside add course dao");
		DatabaseAccess db = new DatabaseAccess();
		db.connectToDatabase();
		String query="Select * from courses;";
		ResultSet rs = db.executeQuery(query);
		System.out.println("rs "+rs);
		return null;
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
