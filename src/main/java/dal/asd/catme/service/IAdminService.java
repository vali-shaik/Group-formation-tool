package dal.asd.catme.service;

import dal.asd.catme.beans.Course;

public interface IAdminService {

	int addCourse(Course Course);
	int deleteCourse(String courseId);
	int addInstructorToCourse(String user,String course);
}

