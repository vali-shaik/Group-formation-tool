package dal.asd.catme.dao;

import dal.asd.catme.beans.Course;

public interface IAdminDao {

	int addCourse(Course Course);
	int deleteCourse(String courseId);
	int addInstructorToCourse(String user,String course);
	
}
