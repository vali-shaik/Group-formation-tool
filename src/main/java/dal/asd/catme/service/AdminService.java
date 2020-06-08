package dal.asd.catme.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.dao.AdminDao;
import dal.asd.catme.dao.IAdminDao;
import dal.asd.catme.dao.IListDetailsDao;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.util.CatmeUtil;

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
