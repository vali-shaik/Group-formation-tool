package dal.asd.catme.dao;

import java.util.List;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.exception.CatmeException;

public interface ICourseDao {

	public List<Course> getCourses(String role) throws CatmeException;
	public Course displayCourseById(String courseId) throws CatmeException;
	public String findRoleByCourse(User user,String courseId) throws CatmeException;
}
