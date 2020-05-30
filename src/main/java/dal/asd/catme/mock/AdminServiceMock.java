package dal.asd.catme.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map courses=createCourseList();
		courses.remove(courseId);
		if(courses.get(courseId) == null)
			return 1;
		else
			return 0;
		
		
	}

	@Override
	public List<Course> getAllCourses() {
		return createList();
	}
	
	public Map createCourseList(){
		Map courses = new HashMap<>();
		courses.put("5308", "ASDC");
		return courses;
	}
	
	public List<Course> createList(){
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
