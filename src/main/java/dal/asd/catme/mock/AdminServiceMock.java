package dal.asd.catme.mock;

import java.util.ArrayList;
import java.util.List;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.service.IAdminService;

public class AdminServiceMock implements IAdminService{

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
		List<Course> courses = new ArrayList<Course>();
		Course course = new Course("5308","ASDC");
		courses.add(course);
		return courses;
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
