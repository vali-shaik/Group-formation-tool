package dal.asd.catme.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.dao.AdminDao;
import dal.asd.catme.dao.IAdminDao;
import dal.asd.catme.util.CatmeUtil;

@Component
public class AdminService implements IAdminService{

	@Autowired
	IAdminDao admin;
	
	
	@Override
	public int addCourse(Course Course) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCourse(String courseId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Course> getAllCourses() {
		List<Course> courses = new ArrayList<>();
		ResultSet resultSet  = admin.getAllCourses();
		try {
			Course course = new Course();
			while(resultSet.next()) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addInstructorToCourse(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
