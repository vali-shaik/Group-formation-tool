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
import dal.asd.catme.util.CatmeUtil;

@Component
@Qualifier("adminService")
public class AdminService implements IAdminService{

	@Autowired
	IAdminDao admin;
	
	
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
		List<Course> courses = new ArrayList<>();
		ResultSet resultSet  = admin.getAllCourses();
		try {
			
			while(resultSet.next()) {
				Course course = new Course();
				course.setCourseId(resultSet.getString(CatmeUtil.COURSE_ID));
				course.setCourseName(resultSet.getString(CatmeUtil.COURSE_NAME));
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}

	@Override
	public List<User> getUsersNotAssignedForCourse(Course course) {
		List<User> users = new ArrayList<User>();
		ResultSet resultSet = admin.getUsersNotAssignedForCourse(course);
		try {
			while(resultSet.next()){
				User user = new User();
				user.setBannerId(resultSet.getString(CatmeUtil.BANNER_ID));
				user.setFirstName(resultSet.getString(CatmeUtil.FIRST_NAME));
				user.setLastName(resultSet.getString(CatmeUtil.LAST_NAME));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public int addInstructorToCourse(String user,String course) {
		return admin.addInstructorToCourse(user,course);
		
	}

	
}
