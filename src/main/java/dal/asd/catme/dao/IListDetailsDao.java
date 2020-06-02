package dal.asd.catme.dao;

import java.sql.ResultSet;
import java.util.List;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;

public interface IListDetailsDao {
	List<Course> getAllCourses();
	List<User> getUsers(Course course);
}
