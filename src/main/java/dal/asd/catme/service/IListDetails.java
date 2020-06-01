package dal.asd.catme.service;

import java.util.List;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;

public interface IListDetails {
	List<Course> getAllCourses();
	List<User> getUsersNotAssignedForCourse(Course course);
}
