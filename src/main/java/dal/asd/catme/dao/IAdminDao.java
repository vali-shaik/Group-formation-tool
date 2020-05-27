package dal.asd.catme.dao;

import java.util.List;
import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;

public interface IAdminDao {

	int addCourse(Course Course);
	int deleteCourse(String courseId);
	List<Course> getAllCourses();
	List<User> getUsersNotAssignedForCourse(Course course);
	int addInstructorToCourse(User user);
}
