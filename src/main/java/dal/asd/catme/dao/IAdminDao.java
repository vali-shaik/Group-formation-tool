package dal.asd.catme.dao;

import java.sql.ResultSet;
import java.util.List;
import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;

public interface IAdminDao {

	int addCourse(Course Course);
	int deleteCourse(String courseId);
	ResultSet getAllCourses();
	List<User> getUsersNotAssignedForCourse(Course course);
	int addInstructorToCourse(User user);
}
