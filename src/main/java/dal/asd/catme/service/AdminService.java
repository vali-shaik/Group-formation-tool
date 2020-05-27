package dal.asd.catme.service;

import java.util.List;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.dao.AdminDao;
import dal.asd.catme.dao.IAdminDao;

public class AdminService implements IAdminService{

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
		System.out.println("inside service");
		// TODO Auto-generated method stub
		IAdminDao admin = new AdminDao();
		return admin.getAllCourses();
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
