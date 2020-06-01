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
import dal.asd.catme.dao.AdminDao;
import dal.asd.catme.dao.IAdminDao;
import dal.asd.catme.dao.IListDetailsDao;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.util.CatmeUtil;

@Component
@Qualifier("adminService")
public class AdminService implements IAdminService,IListDetails{

	@Autowired
	IAdminDao admin;
	
	@Autowired
	IListDetailsDao listDetails;
	
	@Autowired
	DatabaseAccess db; 
	
	@Override
	public int addCourse(Course course) {
		return admin.addCourse(course);
	}

	@Override
	public int deleteCourse(String courseId) {
		return admin.deleteCourse(courseId);
	}

	@Override
	public List<Course> getAllCourses() {
		return listDetails.getAllCourses();
		
	}

	@Override
	public List<User> getUsersNotAssignedForCourse(Course course) {
		
		return listDetails.getUsersNotAssignedForCourse(course);
		
	}

	@Override
	public int addInstructorToCourse(String user,String course) {
		return admin.addInstructorToCourse(user,course);
		
	}

	
}
