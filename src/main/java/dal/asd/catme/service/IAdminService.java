package dal.asd.catme.service;

import java.util.List;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.dao.IAdminDao;

public interface IAdminService {

	int addCourse(Course Course);
	int deleteCourse(String courseId);
	int addInstructorToCourse(String user,String course);
}

