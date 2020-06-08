package dal.asd.catme.service;

import org.springframework.stereotype.Component;

import dal.asd.catme.beans.Course;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.dao.IAdminDao;

@Component
public class AdminService implements IAdminService{

	IAdminDao admin;
	
	
	 
	
	@Override
	public int addCourse(Course course) {
		admin=SystemConfig.instance().getAdminDao();
		return admin.addCourse(course);
	}

	@Override
	public int deleteCourse(String courseId) {
		admin=SystemConfig.instance().getAdminDao();
		return admin.deleteCourse(courseId);
	}

	
	

	@Override
	public int addInstructorToCourse(String user,String course) {
		admin=SystemConfig.instance().getAdminDao();
		return admin.addInstructorToCourse(user,course);
		
	}

	
}
